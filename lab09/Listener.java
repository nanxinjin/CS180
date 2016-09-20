package edu.purdue.jinn.lab;

import android.view.View.*;
import android.view.View;
import java.util.*;
import android.os.*;
import android.widget.*;

public class Listener implements OnClickListener
{
    /**
     * Handle "call backs" when the user presses one of the on-screen buttons.
     */
    @Override
    public void onClick(View arg) {

        Button b = (Button)arg;//Create a Button obect and assign it to arg using TYPE CASTING
        CharSequence c = b.getText();
        // use getText() method in Button to fetch the text content of the button as a CharSequence
        if(c.equals("Reset")) {
          StartActivity.logIt("Your screen will be clear");
        }
        else if(c.equals("Time")) {
          StartActivity.logIt("I don't knoe the time");
        }
        else if(c.equals("Serial")){
          StartActivity.logIt("What is Serial?");
        }
        // Figure out which button was pressed; take an action...
        //Compare the obtained text to print the appropriate response using StartActivity.logIt() method;

 // if Reset: clear screen using the following two commands - 
 //StartActivity.strings.clear();
 //StartActivity.logView.setText("");
 //then print: "Reset requested"

 // if Time   print: the present time using:
 //StartActivity.logit(String.format("Current date and time:  %s",Calendar.getInstance().getTime()));

 // if Serial print: serial number using Build.SERIAL using a similar command given above 
  
        
    }
}
