import machine

class device(object):
    """
    Device class for the appliances
    """
    def __init__(self, _input_pin, _output_pin):

        if isinstance(_input_pin, machine.Pin):
            self._input_pin = _input_pin
        else:
            raise Exception("Expected param 1 to be of type machine.Pin but got type {}".format(type(_input_pin)))

        if isinstance(_output_pin, machine.Pin):
            self._output_pin = _output_pin
        else:
            raise Exception("Expected param 2 to be of type machine.Pin but got type {}".format(type(_output_pin)))

        self._data = bytearray(24)
        self._current_pos = 0

    def get_state(self):
        """
        Returns the state of the _input_pin
        :return: Boolean
        """
        return self._input_pin.value()

    def set_state(self, value):
        """
        Sets the state of the _output_pin
        :param: value
        :type: Boolean
        :return: None
        """
        self._output_pin.value(value)

    @staticmethod
    def to_bytearray():
        pass

    def update_index(self):
        self._current_pos += 1