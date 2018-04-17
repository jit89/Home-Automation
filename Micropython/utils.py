import machine
import utime


class timed(object):
    """
    Decorator class for executing a function after certain intervals
    """

    def __init__(self, interval):
        """

        :type interval: time.time() object
        """
        self.interval = interval
        if not isinstance(interval, float) or not isinstance(interval, int):
            raise TypeError("Expected argument to be of type int or float but got type {}".format(type(interval)))
        self.start = utime.ticks_ms()

    def __call__(self, func, *args, **kwargs):
        def decorated_func(*args, **kwargs):
            if utime.ticks_diff(utime.ticks_ms(), self.start) > self.interval:
                self.start = utime.ticks_ms()
                return func(*args, **kwargs)

        return decorated_func


class FM24CL16B(object):
    """Driver class for FM24CL16B-G 2 x 8kb RRAM
    """

    def __init__(self):
        pass


class Nointerrupts(object):
    """Context manager class for writing time critical code

    """

    def __enter__(self):
        self.state = machine.disable_irq()

    def __exit__(self, *args):
        machine.enable_irq(self.state)


class Gpio(object):
    def __init__(self):
        self.D3 = const(0)
        self.D5 = const(14)
        self.D7 = const(13)
        self.D8 = const(15)
        self.D0 = const(16)
        self.D6 = const(12)
