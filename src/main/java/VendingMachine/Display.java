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
public class Display {

    //With appropriate use, only one of these flags should be true at a time
    //may want to add a check for this and change void setters to bool indicating
    //an error case

    boolean ChangeAvailable, // @var indicates if return change is available
            ProductVended,   // @var indicates that a product was vended
            ProductSoldOut,  // @var indicates that a selected product is sold out
            ProductSelected; // @var indicates that an available product was selected

    double ProductPrice; // @var price of selected product

    /////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params changeAvailable - boolean to track availability of return change
    //                           true if change is available, false if not
    /////////////////////////////////////////////////////////////////////////////
    public Display(boolean changeAvailable){
        ChangeAvailable = changeAvailable;
        ProductVended = ProductSoldOut = ProductSelected = false;
        ProductPrice = 0.0;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Returns string containing the message to display to customer
    /////////////////////////////////////////////////////////////////////////////
    public String getMessage(){
        if(ProductSelected){
            ProductSelected = false;
            DecimalFormat currencyFormater = new DecimalFormat("#.##");
            return("PRICE $" + currencyFormater.format(ProductPrice));
        }
        else if(ProductVended){
            ProductVended = false; //reset flag once message has been displayed
            return("THANK YOU");
        }
        else if(ProductSoldOut){
            ProductSoldOut = false; //reset flag once message has been displayed
            return("SOLD OUT");
        }
        else if(ChangeAvailable){
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

    /////////////////////////////////////////////////////////////////////////////
    // @brief Sets product vended flag to true
    /////////////////////////////////////////////////////////////////////////////
    public void setProductVended(){
        ProductVended = true;
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
    public void setProductSelected(double productPrice)
    {
        ProductSelected = true;
        ProductPrice = productPrice;
    }

}
