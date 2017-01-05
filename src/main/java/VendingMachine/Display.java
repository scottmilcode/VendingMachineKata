///////////////////////
// @author Scott Miller
// @file Display.java
///////////////////////
package VendingMachine;
import java.text.DecimalFormat;

/////////////////////////////////////////////////////////////////////////////
// @class Display
// @brief Class that tracks the state of the display message and provides it
//        as a string.  The intended use is for getMessage() to be called
//        on a periodic schedule to update the display, allowing the message
//        to be reset to one of the two defaults using straight forward logic
//        and minimal interaction.
/////////////////////////////////////////////////////////////////////////////
public class Display{

    //With appropriate use, only one of these flags should be true at a time
    //may want to add a check for this and change void setters to bool indicating
    //an error case

    boolean ChangeAvailable, // @var indicates if return change is available
            ProductVended,   // @var indicates that a product was vended
            ProductSoldOut,  // @var indicates that a selected product is sold out
            ProductSelected; // @var indicates that an available product was selected

    int ProductPrice;       // @var price of selected product
    double AmountAvailable; // @var total amount of currency available to use for purchase

    /////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params changeAvailable - boolean to track availability of return change
    //                           true if change is available, false if not
    /////////////////////////////////////////////////////////////////////////////
    public Display(boolean changeAvailable){
        ChangeAvailable = changeAvailable;
        ProductVended = ProductSoldOut = ProductSelected = false;
        ProductPrice = 0;
        AmountAvailable = 0.0;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Used to attain message the should be placed on customer display
    // @returns Returns string containing the message to display to customer
    /////////////////////////////////////////////////////////////////////////////
    public String getMessage(){
        if(ProductSelected){
            ProductSelected = false;
            double displayPrice = ProductPrice;

            displayPrice/=100; //convert to decimal/dollars double for display
            DecimalFormat currencyFormater = new DecimalFormat("0.00");
            return("PRICE $" + currencyFormater.format(displayPrice));
        }
        else if(ProductVended){
            ProductVended = false; //reset flag once message has been displayed
            return("THANK YOU");
        }
        else if(ProductSoldOut){
            ProductSoldOut = false; //reset flag once message has been displayed
            return("SOLD OUT");
        }
        else if(0 < AmountAvailable) //Display total valid amount inserted so far
        {
            DecimalFormat currencyFormater = new DecimalFormat("0.00");
            return("$" + currencyFormater.format(AmountAvailable));
        }
        else if(ChangeAvailable){
            return("INSERT COIN");
        }
        else //no return change available
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

    /////////////////////////////////////////////////////////////////////////////
    // @brief Sets product vended flag to true
    /////////////////////////////////////////////////////////////////////////////
    public void setProductVended(){
        ProductVended = true;
        AmountAvailable = 0.0; //reset amount available
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Sets product sold out flag to true
    /////////////////////////////////////////////////////////////////////////////
    public void setProductSoldOut(){
        ProductSoldOut = true;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Sets product selected flag to true
    // @params productPrice price to display
    /////////////////////////////////////////////////////////////////////////////
    public void setProductSelected(int productPrice){
        ProductSelected = true;
        ProductPrice = productPrice;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Updates the amount of available currency that is displayed
    //        NOTE: This assumes currency amount was validated by caller
    // @params amount Amount of currency added
    /////////////////////////////////////////////////////////////////////////////
    public void coinInserted(double amount){
        AmountAvailable += amount/100; //convert from cents to dollars and save as a double for display formatting
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Resets the tracked amount available for purchase
    /////////////////////////////////////////////////////////////////////////////
    public void resetAmountAvailableForPurchase(){
        AmountAvailable = 0;
    }

}