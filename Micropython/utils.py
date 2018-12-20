import socket

import machine
import utime


def load_data(device_list):
    for i, device in enumerate(device_list):
        device.load_from_mem(i2c, 24 * 7 * i)


def save_data(device_list):
    for i, device in enumerate(device_list):
        device.save_to_mem(i2c, 24 * 7 * i)


def station(sta, ap, ssid, password):
    if ap.active():
        ap.active(False)

    if not sta.isconnected():
        sta.active(True)
        sta.connect(ssid, password)
        while not sta.isconnected():
            utime.sleep(1)

    print('Connected to acces point')


def access_point(sta, ap):
    ssid, password = None, None
    if sta.active():
        sta.active(False)

    ap.active(True)

    ap.config(essid='Home-Automation', password='12345678')
    print(ap.ifconfig())

    utime.sleep(12)

    addr = socket.getaddrinfo('192.168.4.1', 80)[0][-1]

    s = socket.socket()
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind(addr)
    s.listen(5)

    while True:
        cl, addr = s.accept()
        print('Client connected from', addr)

        req = cl.recv(128)

        req = req.decode().split()[1][1:]

        if len(req) > 1:
            index = req.find('?')
            ssid, password = req[:index], req[index + 1:]
            print(ssid, password)

        # cl.send('HTTP/1.1 200 OK\r\n\r\n'.encode())
        # cl.send(html.encode())

        if ssid is not None and password is not None:
            cl.close()
            return ssid, password


class timed_function(object):
    """
    Decorator class for executing a function after certain intervals
    """

    def __init__(self, interval):
        
        self.interval = interval
        if not isinstance(interval, int):
            raise TypeError("Expected argument to be of type int but got type {}".format(type(interval)))
        self.start = utime.ticks_ms()

    def __call__(self, func, *args, **kwargs):
        def decorated_func(*args, **kwargs):
            if utime.ticks_diff(utime.ticks_ms(), self.start) > self.interval:
                self.start = utime.ticks_ms()
                return func(*args, **kwargs)

        return decorated_func


class NoInterrupts(object):
    """Context manager class for writing time critical code

    """

    def __enter__(self, *args, **kwargs):
        self.state = machine.disable_irq()

    def __exit__(self, *args, **kwargs):
        machine.enable_irq(self.state)


class GPIO(object):
    def __init__(self):
        self.D0 = const(16)
        self.D1 = const(5)
        self.D2 = const(4)
        self.D3 = const(0)
        self.D4 = const(2)
        self.D5 = const(14)
        self.D6 = const(12)
        self.D7 = const(13)
        self.D8 = const(15)


def rtc_init(rtc, sta_if):
    """Initialises the rtc from ntp and sets it to IST (Indian Standard Time)
    """

    if not isinstance(rtc, machine.RTC):
        raise TypeError("Expected argument 3 to be of type machine.RTC but got type {}".format(type(rtc)))

    import ntptime
    num_trials = 0
    rtc_set = False

    # sta_if = network.WLAN(network.STA_IF)

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

    GmtTime = utime.time()
    IST = GmtTime + 19800
    (year, month, mday, hour, minute, second, weekday, yearday) = utime.localtime(IST)
    rtc.datetime((year, month, mday, 0, hour, minute, second, 0))
