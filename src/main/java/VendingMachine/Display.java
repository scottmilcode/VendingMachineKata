///////////////////////
// @author Scott Miller
// @file Display.java
///////////////////////
package VendingMachine;

/////////////////////////////////////////////////////////////////////////////
// @class Display
// @brief Class that tracks the state of the display message and provides it
//        as a string
/////////////////////////////////////////////////////////////////////////////
public class Display {

    boolean ChangeAvailable; // @var boolean indicating if return change is available

    /////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params changeAvailable - boolean to track availability of return change
    //                           true if change is available, false if not
    /////////////////////////////////////////////////////////////////////////////
    public Display(boolean changeAvailable){
        ChangeAvailable = changeAvailable;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Returns string containing the message to display to customer
    /////////////////////////////////////////////////////////////////////////////
    public String getMessage(){
        if(ChangeAvailable){
            return("INSERT COIN");
        }
        else
        {
            return("EXACT CHANGE ONLY");
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Sets the state of the change available flag
    // @params changeAvailable - boolean to track availability of return change
    //                           true if change is available, false if not
    /////////////////////////////////////////////////////////////////////////////
    public void setChangeAvailable(boolean changeAvailable){
        ChangeAvailable = changeAvailable;
    }

}
