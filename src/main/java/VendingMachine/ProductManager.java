///////////////////////////////
// @author Scott Miller
// @file ProductManager.java
///////////////////////////////
package VendingMachine;
import java.util.ArrayList;

/////////////////////////////////////////////////////////////////////////////
// @class ProductManager
// @brief Tracks inventory and price of products available for sale
//        also allows items to be manually added to machine.  Currently
//        does not account for any maximum limit on inventory.
/////////////////////////////////////////////////////////////////////////////
public class ProductManager{

    /////////////////////////////////////////////////////////////////////////////
    // @class Product
    // @params Declared product information as a private enum for simplicity sake.
    //         Ideally this data would be loaded from an outside source like an
    //         xml file or database.  This was the system could be easily updated
    //         without modifying code if products were to be changed.
    /////////////////////////////////////////////////////////////////////////////
    private enum Product{
        COLA  (100),
        CHIPS (50),
        CANDY (65);

        private int Price; //@var price in dollars
        private int Quantity; //@var how many are available for sale

        ////////////////////////////////////////////////////////////////////////////
        // @brief Default Constructor
        /////////////////////////////////////////////////////////////////////////////
        Product(int price){
            Price = price;
        }

        //Getters and Setters
        public int getQuantity(){ return(Quantity); }
        public void setQuantity(int quantity){ Quantity = quantity; }
        public void removeProduct(){ Quantity--; }
        public void addProduct(){ Quantity++; }
        public int getPrice(){ return(Price); }
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params numCola - number of Colas in machine at start up
    //         numChips - number of Chips in machine at start up
    //         numCandy - number of Candies in machine at start up
    /////////////////////////////////////////////////////////////////////////////
    public ProductManager(int numCola, int numChips, int numCandy){
        //I'm not a big fan of how this is hard coded, but the kata
        // clearly states only three products will be used
        for (Product p : Product.values())
        {
            if("COLA" == p.name()){
                p.setQuantity(numCola);
            }
            else if("CHIPS" == p.name()){
                p.setQuantity(numChips);
            }
            else if("CANDY" == p.name()){
                p.setQuantity(numCandy);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Provides useful information about product price and availability
    // @params productName - name of prodcut being queried
    // @returns If a product is available it's price, else 0 indicating the
    //          product is sold out. If an invalid productName is provided -1
    /////////////////////////////////////////////////////////////////////////////
    public int getQuantityOrPrice(String productName){
        for (Product p : Product.values()){
            if(p.name() == productName){
                if(p.getQuantity() > 0){
                    return (p.getPrice());
                }
                else{
                    return (0); //if none in stock return a zero price indicated sold out!
                }
            }
        }
        return(-1); //case of product not found (error case)
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Removes one product from inventory
    // @params productName - name of product being queried
    // @returns If a product is available it's quanitity is reduced by 1 and true
    //          is returned. If a product is sold out or doesn't exists (invalid name)
    //          false is returned
    /////////////////////////////////////////////////////////////////////////////
    public boolean removeProduct(String productName){
        for (Product p : Product.values()){
            if(p.name() == productName){
                if(p.getQuantity() > 0){
                    p.removeProduct();
                    return(true);
                }
                else{
                    return (false); //if none in stock return a false for error case
                }
            }
        }
        return(false); //case of product not found (error case)
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Adds one product to inventory  THIS FUNCTION NO LONGER NEEDED
    // @params productName - name of product being queried
    // @returns If a product is available it's quantity is increased by 1 and true
    //          is returned. If a product doesn't exists (invalid name)
    //          false is returned
    /////////////////////////////////////////////////////////////////////////////
    public boolean addProduct(String productName){
        for (Product p : Product.values()){
            if(p.name() == productName){
                p.addProduct();
                return(true);
            }
        }
        return(false); //case of product not found (error case)
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Returns list of prices for products that are available
    // @returns ArrayList of Doubles containing prices of products
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> getPricesOfAvailableProducts(){
        ArrayList<Integer> prices = new ArrayList<Integer>();

        for (Product p : Product.values()){
            if (p.getQuantity() > 0){
                prices.add(p.getPrice());
            }
        }
        return(prices);
    }
}