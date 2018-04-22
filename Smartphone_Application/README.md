<h1>HOME APP</h1>

## Xml Formatting
In designing xml of this app navigation drawer layout is used
* All mainscreen elements are inside a frame layout.
* A linear Layout is used after frame Layout to support elements
* Several layers of layouts were used to hold various elements
A rough map of the xml layout  is given below:
    
    ----drawer_layout
                
             |--Frame_layout
                   |---Relative_layout
                           
                           |---Linear Layout(H)
                                        |--ImageButton
                                        |--TextView
                                        |--Imagebutton
                           |---Linear Layout(H)
                                        |--Linear Layout(v)
                                                    |--ImageButton
                                                    |--TextView
                                                    |--Seekbar
                                        |--Linear Layout(v)
                                                    |--ImageButton
                                                    |--TextView
                                                    |--Seekbar
                           |---Linear Layout(H)
                                        |--Linear Layout(v)
                                                    |--ImageButton
                                                    |--TextView
                                                    |--Seekbar
                                        |--Linear Layout(v)
                                                    |--ImageButton
                                                    |--TextView
                                                    |--Seekbar
                           |---TextView
                           |---Linear Layout(H)
                                        |--ImageButton
                                        |--ImageButton
                                        |--ImageButton
        
        |--Navigation_View
                        |---Header
                        |---Drawer Views
        
        
## MainActivity
* Separate boolean variables are assigned for every individual appliances of every room.
* On appliance button click it checks the concerned variable state and change the state accordingly 
and also change the text view.
* On Button or seekbar change it calls a method which sends a json to a hardcoded url.
* On room Button click it shows the status of the appliances of that room.
        

## Screenshots

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
