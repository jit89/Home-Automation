import machine
import utime


class Timed(object):
    """
    Decorator class for executing a function after certain intervals
    """

    def __init__(self, interval):
        
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
    """Driver class for FM24CL16B-G 2k x 8b FRAM
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


class GPIO(object):
    def __init__(self):
        self.D0 = const(16)
        self.D1 = const(05)
        self.D2 = const(04)
        self.D3 = const(00)
        self.D4 = const(02)
        self.D5 = const(14)
        self.D6 = const(12)
        self.D7 = const(13)
        self.D8 = const(15)


def rtc_init(ssid, password):
    """Initialises the rtc from ntp and sets it to IST (Indian Standard Time)

    :param ssid: String
    :param password: String
    :return: None
    """

    if not isinstance(ssid, str) and not isinstance(password, str):
        raise TypeError("Expected arguments to be of type str but got type {} {} ".format(type(ssid)), type(password))

    import network, ntptime
    num_trials = 0
    rtc_set = False

    sta_if = network.WLAN(network.STA_IF)
    if not sta_if.isconnected():
        sta_if.active(True)
        sta_if.connect(ssid, password)
        while not sta_if.isconnected():
            utime.sleep(1)

    while not rtc_set:

        try:
            ntptime.settime()
        except:
            num_trials += 1
        else:
            rtc_set = True

        if num_trials == 5:
            raise Exception("Failed to set time! ")
            break

    rtc = machine.RTC()
    GmtTime = utime.time()
    IST = GmtTime + 3600 * 5 + 60 * 30
    (year, month, mday, hour, minute, second, weekday, yearday) = utime.localtime(IST)
    rtc.datetime((year, month, mday, hour, minute, second, weekday, yearday))
