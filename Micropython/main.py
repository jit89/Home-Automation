import utime
from appliance import Device
from machine import Pin
from utils import rtc_init

ssid = "your-ssid-here"
password = "your-password-here"


def init():
    """All initialisations here"""

    rtc_init(ssid, password)
    print(utime.localtime())

    output_pins = (00, 14, 13)  # Define your output pins here
    input_pins = (15, 16, 12)  # Define your input pins here

    devices = [Device(Pin(In), Pin(Out)) for In, Out in zip(input_pins, output_pins)]

    for device in devices:
        try:
            device.vtimer_init()
        except Exception as e:
            print(e)
            print("Failed to start Timer")
            device.vtimer_deinit()

def main():
    init()

    while True:
        """all routines that are repeated"""
        pass
