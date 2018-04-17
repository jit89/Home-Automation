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
        self.start = utime.ticks_ms()

    def __call__(self, func, *args, **kwargs):
        def decorated_func(*args, **kwargs):
            if utime.ticks_diff(utime.ticks_ms(), self.start) > self.interval:
                self.start = utime.ticks_ms()
                return func(*args, **kwargs)

        return decorated_func


class FM24CL16B(object):
    """
    Driver class for FM24CL16B-G 2 x 8kb RRAM
    """

    def __init__(self):
        pass
