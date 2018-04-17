import machine


class Device(object):
    """
    Device class for the appliances
    """

    def __init__(self, _input_pin, _output_pin):

        if isinstance(_input_pin, machine.Pin):
            self._input_pin = _input_pin
            self._input_pin.init(mode=machine.Pin.IN)
        else:
            raise Exception("Expected param 1 to be of type machine.Pin but got type {}".format(type(_input_pin)))

        if isinstance(_output_pin, machine.Pin):
            self._output_pin = _output_pin
            self._output_pin.init(mode=machine.Pin.OUT)
        else:
            raise Exception("Expected param 2 to be of type machine.Pin but got type {}".format(type(_output_pin)))

        self._data = bytearray(24)
        self._current_pos = 0

    @property
    def get_state(self):
        """Returns the state of the _input_pin
        :param: None
        :return: Boolean
        """
        # noinspection PyArgumentList
        return self._input_pin.value()

    def set_state(self, value):
        """Sets the state of the _output_pin

        :param: value
        :type: Boolean
        :return: None
        """
        self._output_pin.value(value)

    def get_data(self):
        # noinspection SpellCheckingInspection
        """Returns the data as bytearray
        :param: None
        :return: bytearray
        """
        return self._data

    def set_data(self, position, value):
        if not isinstance(value, int):
            raise TypeError("Expected argument ")

        if position > 24:
            # noinspection SpellCheckingInspection
            raise IndexError("Argument position exceeded bytearray range")

        self._data[position] = value

    def update_index(self):
        self._current_pos += 1
