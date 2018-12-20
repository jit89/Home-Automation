import machine
import network
import urequests
import utime
from machine import Pin

from Micropython.appliance import Device
from Micropython.utils import rtc_init, station, timed_function

rtc = machine.RTC()
i2c = machine.I2C(sda=Pin(4), scl=Pin(5), freq=400000)

sta = network.WLAN(network.STA_IF)
ap = network.WLAN(network.AP_IF)

old_req = None


def test_device_stagnancy(device_list):
    for device in device_list:
        device.calibrate_stagnancy()


@timed_function(500)
def parse_request(device_list):
    global old_req

    try:
        req = urequests.get('http://phpexampl.000webhostapp.com/test.php?opt=x')
    except:
        req = urequests.get('http://phpexampl.000webhostapp.com/test.php?opt=x')

    req = req.json()

    if req != old_req and old_req is not None:

        if req['app11'] != old_req['app11']:
            device_list[0].toggle_state()

        if req['app21'] != old_req['app21']:
            device_list[1].toggle_state()

        if req['app31'] != old_req['app31']:
            device_list[2].toggle_state()

    old_req = req


@timed_function(1000)
def print_debug_info(device_list):
    for device in device_list:
        print(device._output_pin.value())


def init():
    """All initialisations here"""

    # device_timer = machine.Timer(-1)

    output_pins = (00, 14, 13)  # Define your output pins here
    input_pins = (15, 16, 12)  # Define your input pins here

    devices = [Device(Pin(In), Pin(Out)) for In, Out in zip(input_pins, output_pins)]

    #   Set all the devices to zero state at first
    for device in devices:
        device.set_state(0)

    # ssid, password = access_point(sta, ap)
    station(sta, ap, 'D-Link', 'ik1224kln')

    rtc_init(rtc, sta)
    print(utime.localtime())

    # device_timer.init(period=60000, mode=machine.Timer.PERIODIC, callback=lambda _: test_device_stagnancy(devices))

    return devices


def main():
    # init()

    device_list = init()

    while True:
        """All routines that are repeated"""
        parse_request(device_list)
        print_debug_info(device_list)
        print('#')
