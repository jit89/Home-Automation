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
            raise TypeError("Expected argument 1 to be of type machine.Pin but got type {}".format(type(_input_pin)))

        if isinstance(_output_pin, machine.Pin):
            self._output_pin = _output_pin
            self._output_pin.init(mode=machine.Pin.OUT)
        else:
            raise TypeError("Expected argument 2 to be of type machine.Pin but got type {}".format(type(_output_pin)))

        self._data = bytearray(24 * 7)  # Represent 24 hours * 7 days as a contigous block of bytes
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

    def toggle_state(self):
        """Toggles the state of the _output_pin
        :param: None
        :return: None
        """
        self._output_pin.value(not self._output_pin.value())
        
    
    def get_data(self):
        # noinspection SpellCheckingInspection
        """Returns the data as bytearray
        :param: None
        :return: bytearray
        """
        return self._data

    def set_data(self, day, value):
        if not isinstance(value, int):
            raise TypeError("Expected argument type int but got type {}".format(type(value)))
        
        if not isinstance(day, int):
            raise TypeError("Expected argument type int but got type {}".format(type(day)))
            
        if day > 7:
            raise IndexError("list index out of range")
            
        self._data[24 * day + self._current_pos] = self.get_state
        self.update_index()

    def update_index(self):
        """Updates _current_pos by 1
        :param: None
        :return: None
        """
        if self._current_pos > 24:
            self._current_pos = 1
        self._current_pos += 1
        
        
