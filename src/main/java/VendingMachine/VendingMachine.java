///////////////////////////////////////////////////////////////////
// @author Scott Miller
// @file VendingMachine.java
// @brief This file exists as a use sample, not as executable code
//        or code that is meant to be evaluated.
////////////////////////////////////////////////////////////////////
package VendingMachine;
import java.util.ArrayList;
import java.util.Collections;

public class VendingMachine {
    Display display;
    InsertedCoins insertedCoins;
    ProductManager productManager;
    ChangeManager changeManager;

    public void initializeMachine(){
        int numNickels, numDimes, numQuarters;
        int numColas, numChips, numCandy;
        numNickels = numDimes = numQuarters = 0;
        numColas = numChips = numCandy = 0;

        insertedCoins = new InsertedCoins(); //setup the coin reading code

        //MECHANICAL ACTION getAvailableChangeFromMachine(numNickels, numDimes, numQuarters);  //Detect how many of each coin we have from the machine
        changeManager = new ChangeManager(numNickels, numDimes , numQuarters); //setup the change manager

        //MECHANICAL ACTION getAvailableProductInventory(numColas, numChips, numCandy); //Detect how many of each product are available from machine
        productManager = new ProductManager(numColas, numChips, numCandy); //setup the product manager

        display = new Display(changeManager.isChangeAvailable(productManager.getPricesOfAvailableProducts())); //initialize the display
    }

    public void startDisplayUpdateLoop(){
        String message = display.getMessage();
       //MECHANICAL ACTION writeToMachineDisplay(message);

        if("THANK YOU" == message){//we sold something we need to check if there is still change available
            display.setChangeAvailable(changeManager.isChangeAvailable(productManager.getPricesOfAvailableProducts())); //determine if we can still make change and update display
        }
    }

    public void productVendHelper(int productPrice, String productName){
        ArrayList<Integer> returnChange = new ArrayList<Integer>();

        productManager.removeProduct(productName);
        changeManager.makeChange(productPrice, insertedCoins.getAmountAvailableForPurchase(), returnChange);

        //MECHANICAL ACTION send returnChange list to machine mechanics that issue change
        //MECHANICAL ACTION tell machine mechanism to deposit inserted coins into collection tray
        insertedCoins.resetAmountAvailableForPurchase();
        display.setProductVended();

        //MECHANICAL ACTION send productName to machine so machine can vend proper product
    }

    //event driven code that is called when the cola button is pressed
    public void colaButtonPressed(){
        int price = productManager.getQuantityOrPrice("COLA");

        if(0 == price){//product is sold out
            display.setProductSoldOut();
        }
        else if(price < insertedCoins.getAmountAvailableForPurchase()){//not enough money inserted
            display.setProductSelected(price);
        }
        else{//lets buy some cola!
            productVendHelper(price, "COLA");
        }
    }

    //event driven code that is called when the chips button is pressed
    public void chipsButtonPressed(){
        int price = productManager.getQuantityOrPrice("CHIPS");

        if(0 == price){//product is sold out
            display.setProductSoldOut();
        }
        else if(price < insertedCoins.getAmountAvailableForPurchase()){//not enough money inserted
            display.setProductSelected(price);
        }
        else{//lets buy some chips!
            productVendHelper(price, "CHIPS");
        }
    }

    //event driven code that is called when the candy button is pressed
    public void candyButtonPressed(){
        int price = productManager.getQuantityOrPrice("CANDY");

        if(0 == price){//product is sold out
            display.setProductSoldOut();
        }
        else if(price < insertedCoins.getAmountAvailableForPurchase()){//not enough money inserted
            display.setProductSelected(price);
        }
        else{//lets buy some candy!
            productVendHelper(price, "CANDY");
        }
    }

    //event driven code that is called when a coin is inserted
    public void coinInserted(){
        double weight, diameter;
        weight = diameter = 0.0;

        //determine what the most expensive product is
        ArrayList<Integer> productPrices = productManager.getPricesOfAvailableProducts();
        int maxProductPrice = productPrices.get(productPrices.indexOf(Collections.max(productPrices)));

        if(insertedCoins.getAmountAvailableForPurchase() >= maxProductPrice){//don't need any more money don't even process the coin
            //MECHANICAL ACTION send inserted coin directly to return
        }
        else{ //we can use more money
            //MECHANICAL ACTION getCoinWeightDiameterFromMachine(double weight, double diameter); //detect the weight and diameter of the coin
            int coinValue = insertedCoins.insertCoin(weight, diameter);

            if(0 == coinValue){//coin was invalid
                //MECHANICAL ACTION send inserted coin directly to return slot
            }
            else{//we added a valid coin, lets tell the display
                display.coinInserted(coinValue);
            }
        }
    }

    //event driven code that is called when the return button is pressed
    public void returnButtonPressed(){
        //MECHANICAL ACTION send all coins in holding tray to return slot (not the collection tray)
        insertedCoins.resetAmountAvailableForPurchase(); //reset the coin total
        display.resetAmountAvailableForPurchase(); //rest the display's total
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.initializeMachine();
        vendingMachine.startDisplayUpdateLoop(); //this guy would actually run on a timed loop, something like every 1s or 1.5s

        //Connect Coin insertion Slot to coinInserted()
        //Connect Return Coin Button to returnButtonPressed()
        //Connect each product button to appropriate function
    }
}
