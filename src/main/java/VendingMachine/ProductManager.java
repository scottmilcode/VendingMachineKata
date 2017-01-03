///////////////////////////////
// @author Scott Miller
// @file ProductManager.java
///////////////////////////////
package VendingMachine;

/////////////////////////////////////////////////////////////////////////////
// @class ProductManager
// @brief Tracks inventory and price of products available for sale
//        also allows items to be manually added to machine.  Currently
//        does not account for any maximum limit on inventory.
/////////////////////////////////////////////////////////////////////////////
public class ProductManager {

    /////////////////////////////////////////////////////////////////////////////
    // @class Product
    // @params Declared product information as a private enum for simplicity sake.
    //         Ideally this data would be loaded from an outside source like an
    //         xml file or database.  This was the system could be easily updated
    //         without modifying code if products were to be changed.
    /////////////////////////////////////////////////////////////////////////////
    private enum Product {
        COLA  (1.0),
        CHIPS (0.5),
        CANDY (0.65);

        private double Price; // in dollars
        private int Count; // how many are available for sale

        ////////////////////////////////////////////////////////////////////////////
        // @brief Default Constructor
        /////////////////////////////////////////////////////////////////////////////
        Product(double price){
            Price = price;
        }

        //Getters and Setters
        public int getCount(){ return(Count); }
        public void setCount(int count){ Count = count; }
        public void removeProduct(){ Count--; }
        public void addProduct(){ Count++; }
        public double getPrice(){ return(Price); }
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
            if("COLA" == p.name()) {
                p.setCount(numCola);
            }
            else if("CHIPS" == p.name()) {
                p.setCount(numChips);
            }
            else if("CANDY" == p.name()) {
                p.setCount(numCandy);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Provides useful information about product price and availability
    // @params productName - name of prodcut being queried
    // @returns If a product is available it's price, else 0 indicating the
    //          product is sold out. If an invalid productName is provided -1
    /////////////////////////////////////////////////////////////////////////////
    public double getCountOrPrice(String productName){
        for (Product p : Product.values()) {
            if(productName == p.name()) {
                if(p.getCount() > 0) {
                    return (p.getPrice());
                }
                else {
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
        for (Product p : Product.values()) {
            if(productName == p.name()) {
                if(p.getCount() > 0) {
                    p.removeProduct();
                    return(true);
                }
                else {
                    return (false); //if none in stock return a false for error case
                }
            }
        }
        return(false); //case of product not found (error case)
    }

    ////////////////////////////////////////////////////////////////////////////
    // @brief Adds one product from inventory
    // @params productName - name of product being queried
    // @returns If a product is available it's quanitity is increased by 1 and true
    //          is returned. If a product is sold out or doesn't exists (invalid name)
    //          false is returned
    /////////////////////////////////////////////////////////////////////////////
    public boolean addProduct(String productName){
        for (Product p : Product.values()) {
            if(productName == p.name()) {
                p.addProduct();
                return(true);
            }
        }
        return(false); //case of product not found (error case)
    }
}
