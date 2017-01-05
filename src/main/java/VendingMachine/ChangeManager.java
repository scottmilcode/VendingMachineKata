///////////////////////////////
// @author Scott Miller
// @file ChangeManager.java
///////////////////////////////
package VendingMachine;
import java.util.ArrayList;
import java.util.Collections;

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

    private int NumNickels, //@var number of Nickels available
                NumDimes, //@var number of Dimes available
                NumQuarters; //@var number of Quarters available

    private final int maxDenomination = 25; //@var maximum denomination currently allowed
    private final int minDenomination = 5; //@var minimum denomination currently allowed

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
        return(makeChange(productPrice, amountInserted, returnChange, false));
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Based on item price determines what coins should be issued for change
    // @params productPrice - price of product being purchased
    //         amountInserted - total value of coins inserted
    //         returnChange - Integer list of coins to be returned, information
    //                        stored as denomination.
    //         testingForChange - boolean to indicate if we are just testing for
    //                            change availability.  If true, we do not remove
    //                            change that would have been returned from inventory.
    //@returns true is change can be made, false otherwise
    /////////////////////////////////////////////////////////////////////////////
    public boolean makeChange(int productPrice, int amountInserted, ArrayList<Integer> returnChange, boolean testingForChange){
        //ArrayList<Double> returnChange = new ArrayList<Double>();
        int returnChangeTotal = 0;
        boolean changeAvailable = true;
        returnChange.clear(); // should already be empty, but just in case

        while(productPrice + returnChangeTotal < amountInserted && changeAvailable){
            if(amountInserted - productPrice - returnChangeTotal >= 25 && NumQuarters > 0){ //see if we should/can return a quarter
                returnChange.add(25); //indicate that a quarter should be returned
                returnChangeTotal += 25;
                NumQuarters--; //take a quarter out of the inventory
            }
            else if(amountInserted - productPrice - returnChangeTotal >= 10 && NumDimes > 0){ //see if we should/can return a dime
                returnChange.add(10); //indicate that a dine should be returned
                returnChangeTotal += 10;
                NumDimes--; //take a dime out of the inventory
            }
            else if(amountInserted - productPrice - returnChangeTotal >= 05 && NumNickels > 0){ //see if we should/can return a nickel
                returnChange.add(05); //indicate that a nickel should be returned
                returnChangeTotal += 05;
                NumNickels--; //take a nickel out of the inventory
            }
            else //this is the trouble case, here we needed a nickel and didn't have one.  That means we can't make change
            {
                changeAvailable = false;
            }
        }
        //At this point we've left the while loop because we made change or because we can't
        //If we are testing, or we couldn't make change place coins back in inventory and return
        //technically if the class is used properly we should never try to make change when it isn't available
        if(testingForChange || !changeAvailable){
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
        }
        return(changeAvailable);
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Determines if change can be made based on available products
    //        assumes that the machine does not allow a customer to enter
    //        additional coins once a maximum purchase price has been attained
    // @params productPrices - prices of all available products
    // @returns true is change can be made, false otherwise
    /////////////////////////////////////////////////////////////////////////////
    public boolean isChangeAvailable(ArrayList<Integer> productPrices){
        //Algorithm is as such:
        // -System design limits user from placing additional
        //coins in machine once maximum price is reached.
        //
        // -As such the largest value that could be inserted will be the largest
        //item price - the smallest coin denomination + the largest coin denomination.
        //The user scenarios is that they have inserted up to the largest amount
        //possible short one of the smallest coins possible and then inserts
        //the largest coin possible.
        //(e.g.) Highest product cost is 1.00.  User has inserted 0.95 so far, machine
        //       allows more coins to be inserted and user places a quarter in.  At which
        //       point the amount entered is equal to or greater than the largest amount
        //       possible and further coins to straight to the return.  This way we don't
        //       allow someone to eat up change.
        //
        //-Similarly, the smallest amount possible requiring change would the the lowest
        //product price plus one additional coin of the smallest denomination.
        //
        //-Any amount of currency inserted between these two values need to be checked to
        //to see if change can be made with every product because we don't no a priori
        //what the customer will purchase
        //
        // -The increment between test values will be in the smallest denomination available.
        //which conveniently is a common denominator of the other two denominations as well.

        //Determine lowest and highest product price
        int lowestPrice = productPrices.get(productPrices.indexOf(Collections.min(productPrices)));
        int highestPrice = productPrices.get(productPrices.indexOf(Collections.max(productPrices)));

        int startTestAmount = lowestPrice + minDenomination; //again hard coded 5 cent denomination
        int stopTestAmount = highestPrice - minDenomination + maxDenomination; //again hard coded denominations

        for(Integer productPrice: productPrices){ //loop through products
            for (int testAmount = startTestAmount; testAmount <= stopTestAmount; testAmount += 5){ //loop through possible inserted amounts
               if(! makeChange(productPrice, testAmount, new ArrayList<Integer>(), true)){
                   return(false);
               }
            }
        }
        return(true); //made it through all the checks no problem
    }
}