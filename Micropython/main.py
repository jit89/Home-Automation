import machine
import utime
from appliance import Device
from machine import Pin
from utils import rtc_init

ssid = "D-Link"
password = "ik1224kln"
rtc = machine.RTC()


def test_stagnancy(deviceList):
    for device in deviceList:
        device.calibrate_stagnancy()


def init():
    """All initialisations here"""

    rtc_init(ssid, password, rtc)
    print(utime.localtime())

    deviceTimer = machine.Timer(-1)

    output_pins = (00, 14, 13)  # Define your output pins here
    input_pins = (15, 16, 12)  # Define your input pins here

    devices = [Device(Pin(In), Pin(Out)) for In, Out in zip(input_pins, output_pins)]
    deviceTimer.init(period=60000, mode=machine.Timer.PERIODIC, callback=lambda _: test_stagnancy(devices))


def main():
    init()

    while True:
        """All routines that are repeated"""
        pass
