### Home-Automation: Thinking Differently
With the emergence of the IOT [(Internet of Things)](https://en.wikipedia.org/wiki/Internet_of_things) , automation has become a part and parcel of our daily lives. Starting from lights and fans, people have started to automate almost everything around them. After software giants like Amazon and Google started providing cloud services, people came to use the free resources provided by them to store/log their data and manage them from a distance even without having direct physical contact with the devices.
After this was pretty well-exploited, people started combining the power of machine learning with the existing technology. With rapid improvement in hardware and GPU's becoming cheap, it has become quite easy for an individual to learn, test out and contribute to the advancement of machine learning. Powerful methods like use of [deep learning](https://en.wikipedia.org/wiki/Deep_learning) in visual image processing, natural langugage processing etc has made this existing technology even more smarter. Popular home assistants like Amazon [Alexa](https://developer.amazon.com/alexa) etc, by making their API's open sourced have encouraged development in home automation even more.
### Why re-invent the wheel then ?
You might ask, well a lot of people have spent years writing and perfecting their techniques, why not build our application atop them ? Why do we even need to think about something from scratch where we have already progressed so far ? The answer is very simple, in fact this the reason for which we took up this project. We couldn't agree well with the existing idea of automation. I know, there are some brilliant projects out there at [Instructables](https://www.instructables.com/), [Hackaday](https://hackaday.com/) and [Hackster](https://www.hackster.io/) etc where people have put a lot of effort making a very neat and appealing project and I appreciate their effort. But controlling lights, fans, switches and other power ports using bluetooth, WiFi and other technologies through a custom designed Android/iOS application may be excellent but they are over exploited already. Yeah, you can switch on your Television and washing machine, turn off the radio, feed your pet, monitor the weather while sitting on your couch while watching your favourite series on Netflix but this has already been done, right ? We are already living those days, but what if your TV knew when do you watch it mostly ? What if your lights and fans knew when to turn on and when not to so they could save the maximum power when you are not in in your room ? That is much better than controlling your lights, fans, television etc through a Bluetooth controlled Arduino or telling Alexa to switch it on/off for you. My point is, the devices which we use everyday should develop some kind of intelligence so that they can know when they are used mostly and thus turn themselves on during those hours without you, manually switching it on. This is exactly what we try to do here.
### The Idea
Keeping aside all big talks, our aim is simple, designing a system that learns to map user behaviour. Citing a small example here, Suppose, you live alone in small apartment with only three appliances in your house, two lights and a fan, lets call them **LIGHT 1**, **LIGHT 2** and **Fan**. Now, consider the following case:
* You wake up at 07:00 (7:00 AM) hours and do your daily chores.
* You leave for office at 08:00 Hours (8:00 AM).
* You return home at 19:00 Hours (7:00 PM).

You follow the above routine on most working days. After waking up (i.e is after 07:00) hrs and before leaving for your office at 08:00 hrs suppose you use light 1 and the fan. After returning from office, you use all three of the appliances. Since, you dont have many switches in the room, you don't have much room for exploring more options, as a result you follow a similar routine everyday.
If we use our prototype here, what our system will do is, it will learn when and how much you used the appliance and from the very next day try to follow the same routine as followed by you the previous days. 
### The Brief Technicalities
Its time for us now, to dive into brief technical details. Our application is actually a room automation module which is used to automate your room, this module provides a drop in replacement for your switchboard so that you do not need to touch your switch board again. The form factor of the module will be so made that it retro-fits behind your switchboard. You can then control the functionalities of the physical switches of your switchboard through a smartphone application, just like any other home automation device. But Wait! There is much more to it. Apart, from controlling them at your own will from the smartphone application you can very well control them by the physical switches if you like. We are not taking away your rights over your switchboard! This, comes in handy if the module or the smartphone application goes haywire, you can anyway use your switches without any worry. Wait, There is even more! Like I said before, the system takes into account your choices of switching appliances on and off both through the physical switches and through the smartphone device. After it has gathered enough data and after you allow it to, it will predict what appliance you might switch on at a certain time of the day and switches on that appliance. Its like, you come home everyday at 19:00 hrs (7:00 PM) and switch on the fan hanging over your sofa, but one day you find out as soon as you come home at or after 19:00 hrs (7:00 PM) the fan switches on by itself!
### Some Additional features
1. A lot of features have been mentioned already but what I would like to add here is, such a system works by mapping user action throughout the day and reproducing them later. Well, you might say, OK, I agree that someone can maintain some coherence in his/her routine for a day or two, but someone cannot follow the same pattern for days after days, for Heavens's sake we are not machines! We, too thought about this, how you will deal with your home appliances tomorrow cannot be exactly predicted by observing how you dealt with them today, we can only get a rough idea. If you observe someone for a day, the conclusion you will reach will be an abstract idea, like:
  > "Ohh, so he likes to leave all lights on during the night",

  or maybe something like this:

  > "Ohh, he switches on more tubelights in the night than in the day",

  or, more likely something like this:

  > "I bet, he uses only half of the appliances he has, in his house".

These are all rough estimates which might be true for a day or two but cannot be guaranteed over a longer period of time. So, How do you get make good estimtes ? Well you keep a watch more frequently and try to understand how much the behaviour of the user differs each day and update your observation accordingly. Our system exactly does that, it keeps a note of your usage behaviour very frequently and tries to draw and inference from that, after a day is over it updates its observations.

2. Since, the system follows the user's behaviour and updates itself as soon as the user's behaviour changes, the device isnt affected by the change of seasons, timezones, geographic locations etc. It all depends on how you use it. 
### How is it being done ?
Before starting, let me tell you quite frankly, that the idea is still in the developmental stage and a lot of community help is required to further polish it. Only a working prototype is being developed here. Our goal is to make a small, easy-to-interface  embedded module made from commonly available parts which will be having enough intelligence to provide descisions based on observing a particular user.
* Lets, talk about the hardware first, We are using [ESP8266-ESP12](https://www.elecrow.com/download/ESP-12S_User_Manual.pdf) [Wemos D1 Mini](https://wiki.wemos.cc/products:retired:d1_mini_v2.2.0) here. 
  * Three pins are configured as outputs for controlling a relay through a BJT (Bipolar Junction Transistor) or probably a MOSFET in the future, for switching high voltage appliances. 
  * A PIR (Passive Infrared Sensor) is used to detect the occupancy of a person in the room, the output of the PIR sensor is fed to a pin configured as input. 
  * For storing the data permanently an i2c [FRAM](http://www.cypress.com/file/136491/download) will be used, FRAM being chosen particularly because of its fast write time and very large read/write cycles in comparison to any eeprom or flash memory.
* The software used to program the Esp is micropython. They are seriously brilliant guys who made micropython possible. I would suggest you to take a look at ther [repository](https://github.com/micropython/micropython) for more information. The software will manage all the afore mentioned hardware. A small single neural network with a single hidden layer will be employed to predict the appliance which needs to be turned on/off, given a particular time and a state (i.e the person is in the house or not). Please note that we are not doing a time series prediction anyway, we are not trying to model the user's behaviour as time series beacuse such a series would be very stochastic, it would more and more difficult to find a pattern in the user's behaviour if the observations are taken for a very long period of time. Our model is based on the assumption that the chance of the user,  using our system, to repeat his/her routine is maximum the very next day, i.e if today is Tuesday, there is more probability of the user to follow a similar routine for Wednesday, but would be very difficult to say in advance that the user will follow a similar routine on Saturday or Sunday. Obviously, hard coding such an assumption is a very naive choice indeed because there can be many cases which might simply outrun the assumption, but for the sake of simplicity such an assumption is made. But some other methods are also employed to alleviate the issue. 
* A smartphone application is being developed too for user access through a smartphone.
* A server side script which the ESp8266 and the smartphone application will ping which will enable the user to acces the home appliances from anywhere in the world.The server side script will be written in CPython. We intend to buy a domain for this purpose too.

We will try to follow the below mentioned tree:

    ----root
        |----Hardware
                |----Schematics, datasheet and pcb design files
        |----Micropython
                |----Micropython code for the Esp
        |----Server
                |----Server side scripts
        |----Smartphone_Application
                |----All documentation related to the smartphone application


### Support
We would appreciate any help provided from your side in designing and developing this idea. We also welcome all kinds of comments and suggestions provided to us.
