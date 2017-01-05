///////////////////////////////
// @author Scott Miller
// @file ChangeManager.java
///////////////////////////////
package VendingMachine;
import java.util.ArrayList;

/////////////////////////////////////////////////////////////////////////////
// @class ChangeManager
// @brief Manages coins that are available for use to make change.
//        These coins are kept separate from coins used to make purchases.
//        This is the way most machines operate to protect against
//        the use of fraudulent monies and not require a coin separating
//        mechanism.
//        This class also handles calculating how much and what coins
//        should be issued as change.  It also determines if change can
//        be made based on potential product prices and coins available
//
//        NOTE:  This too would be better if it were not hard coded for just
//        three coins that way future/different machine models could use half
//        dollar or dollar coins by simply providing that they are available
//        to be loaded into the machine as specified by some outside
//        configuration file
/////////////////////////////////////////////////////////////////////////////
public class ChangeManager {

    private int NumNickels,
                NumDimes,
                NumQuarters;

    ////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params numNickels - number of nickels in machine at start up
    //         numDimes - number of dimes in machine at start up
    //         numQuarter - number of quarters in machine at start up
    /////////////////////////////////////////////////////////////////////////////
    public ChangeManager(int numNickels, int numDimes, int numQuarters){
        NumNickels = numNickels;
        NumDimes = numDimes;
        NumQuarters = numQuarters;
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Based on item price determines what coins should be issued for change
    // @params productPrice - price of product being purchased
    //         amountInserted - total value of coins inserted
    //         returnChange - Integer list of coins to be returned, information
    //                        stored as denomination.
    //@returns true is change can be made, false otherwise
    /////////////////////////////////////////////////////////////////////////////
    public boolean makeChange(int productPrice, int amountInserted, ArrayList<Integer> returnChange){
        //ArrayList<Double> returnChange = new ArrayList<Double>();
        int returnChangeTotal = 0;
        boolean changeAvailable = true;
        returnChange.clear(); // should already be empty, but just incase

        while(productPrice + returnChangeTotal < amountInserted && changeAvailable) {
            System.out.println(amountInserted);
            System.out.println(productPrice);
            System.out.println(returnChangeTotal);
            System.out.println();
            if(amountInserted - productPrice - returnChangeTotal >= 25 && NumQuarters > 0){ //see if we should/can return a quarter
                returnChange.add(25); //indicate that a quarter should be returned
                returnChangeTotal += 25;
                NumQuarters--; //take a quarter out of the queue
            }
            else if(amountInserted - productPrice - returnChangeTotal >= 10 && NumDimes > 0){ //see if we should/can return a dime
                returnChange.add(10); //indicate that a quarter should be returned
                returnChangeTotal += 10;
                NumDimes--; //take a quarter out of the queue
            }
            else if(amountInserted - productPrice - returnChangeTotal >= 05 && NumNickels > 0){ //see if we should/can return a nickel
                returnChange.add(05); //indicate that a quarter should be returned
                returnChangeTotal += 05;
                NumNickels--; //take a quarter out of the queue
            }
            else //this is the trouble case, here we needed a nickel and didn't have one.  That means we can't make change
            {
                changeAvailable = false;
            }
        }
        //At this point we've left the while loop because we made change or because we can't
        if(changeAvailable)
        {
            return(true);
        }
        else //We need to add the change we were going to distribute back to the counts, empty the list and return false
        {
            for (Integer coin: returnChange){
                if(25 == coin){
                    NumQuarters++;
                }
                else if(10 == coin){
                    NumDimes++;
                }
                else if(05 == coin){
                    NumNickels++;
                }
                else{ //Should never get here, but just in case
                    System.out.println("ERROR in ChangeManager.makeChange()");
                }
            }

            returnChange.clear();
            return(false);
        }
    }
}
