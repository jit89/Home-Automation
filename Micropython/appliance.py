import machine
import utime
from utils import Nointerrupts


# noinspection SpellCheckingInspection
class Device(object):
    """
    Device class for the appliances
    """

    def __init__(self, _input_pin, _output_pin):

        if isinstance(_input_pin, machine.Pin):
            self._input_pin = _input_pin
            self._input_pin.init(mode=machine.Pin.IN)
        else:
            raise TypeError("Expected argument 1 to be of type machine.Pin but got type {}".format(type(_input_pin)))

        if isinstance(_output_pin, machine.Pin):
            self._output_pin = _output_pin
            self._output_pin.init(mode=machine.Pin.OUT)
        else:
            raise TypeError("Expected argument 2 to be of type machine.Pin but got type {}".format(type(_output_pin)))

        self._data = bytearray(24 * 7)  # Represent 24 hours * 7 days as a contiguous block of bytes

        self.on_time = 0
        self.oldHour = utime.localtime()[3]

    def get_state(self):
        """
        Returns the state of the _input_pin
        :param: None
        :return: Boolean
        """
        # noinspection PyArgumentList
        return self._input_pin.value()

    def set_state(self, value):
        """
        Sets the state of the _output_pin
        :param: value
        :type: Boolean
        :return: None
        """
        self._output_pin.value(value)

    def toggle_state(self):
        """
        Toggles the state of the _output_pin
        :param: None
        :return: None
        """
        self._output_pin.value(not self._output_pin.value())

    def get_data(self):
        # noinspection SpellCheckingInspection
        """
        Returns the data as bytearray
        :return: bytearray
        """
        return self._data

    def set_data(self, day, pos, value):
        if not isinstance(value, int):
            raise TypeError("Expected argument type int but got type {}".format(type(value)))

        if not isinstance(day, int):
            raise TypeError("Expected argument type int but got type {}".format(type(day)))

        if day > 6:
            raise IndexError("bytearray index out of range")

        self._data[24 * day + pos] = value

    def calibrate_stagnancy(self):
        """
        Determine the time for which the device was on and set _data according to a threshold
        :return: None
        """

        if utime.localtime()[3] != self.oldHour:
            divfactor = 60

            self.set_data(utime.localtime()[2], self.oldHour, int(self.on_time / divfactor) > 0.3)
            self.oldHour = utime.localtime()[3]
            self.on_time = 0

        if self.get_state() == 1:
            self.on_time += 1

    def save_to_mem(self, i2c, memaddr):
        """
        Save _data to FRAM memory
        :param i2c: machine.I2C object
        :param memaddr: starting memory location for writing _data
        :return: length of bytes written
        """
        if not isinstance(i2c, machine.I2C):
            raise TypeError("Expected argument 1 to be of type machine.I2C but got type {}".format(type(i2c)))

        if not isinstance(memaddr, int):
            raise TypeError(" Expected argument 2 to be of type machine.I2C but got type {}".format((type(memaddr))))

        with Nointerrupts:
            i2c.writeto_mem(80, memaddr, self._data)

        return len(self._data)

    def load_from_mem(self, i2c, memaddr):
        """
        Load _data from FRAM memory location
        :param i2c: machine.I2C object
        :param memaddr: FRAM memory location to read from
        :return:
        """

        if not isinstance(i2c, machine.I2C):
            raise TypeError("Expected argument 1 to be of type machine.I2C but got type {}".format(type(i2c)))

        if not isinstance(memaddr, int):
            raise TypeError(" Expected argument 2 to be of type machine.I2C but got type {}".format((type(memaddr))))

        with Nointerrupts:
            i2c.readfrom_mem_into(80, memaddr, self._data)
