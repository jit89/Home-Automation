from machine import Timer
from appliance import device
from machine import Pin

output_pins = (0, 14, 13)   #d3, d5, d7
input_pins = (15, 16, 12)   #d8, d0, d6

devices = [device(Pin(In, Pin.IN), Pin(Out, Pin.OUT)) for In, Out in zip(input_pins, output_pins)]


def main():
    for device in devices:
        device.set_state(1)

    for device in devices:
        print(device.get_state())

# if __name__ == '__main__':
#    main()
