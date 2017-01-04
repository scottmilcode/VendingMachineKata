///////////////////////////////
// @author Scott Miller
// @file ProductManagerTest.java
///////////////////////////////

package VendingMachine;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class ProductManagerTest {
    @Test
    public void atInitializationProductManagerAcceptsInitialInventoryAndGetQuantityOrPriceConfirms(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(1.00, productManager.getQuantityOrPrice("COLA"));
        assertEquals(0.50, productManager.getQuantityOrPrice("CHIPS"));
        assertEquals(0.65, productManager.getQuantityOrPrice("CANDY"));
    }

    @Test
    public void atInitializationProductManagerAcceptsEmptyInitialInventoryAndGetQuantityOrPriceConfirmsNoPriceForSoldOut(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(0.0, productManager.getQuantityOrPrice("COLA"));
        assertEquals(0.0, productManager.getQuantityOrPrice("CHIPS"));
        assertEquals(0.0, productManager.getQuantityOrPrice("CANDY"));
    }

    @Test
    public void getQuantityOrPriceReturnsNegativeOneForInvalidProduct(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(-1.0, productManager.getQuantityOrPrice("BADPRODCUT"));
    }

    @Test
    public void afterASingleProductIsRemovedGetCountOrPriceReturnsNoPriceForSoldOut(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(true, productManager.removeProduct("COLA"));
        assertEquals(0.0, productManager.getQuantityOrPrice("COLA"));
    }

    @Test
    public void removeProductReturnsFalseForAnInvalidProduct(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(false, productManager.removeProduct("BADPRODUCT"));
    }

    @Test
    public void afterASingleProductIsAddedGetCountOrPriceReturnsPriceForItem(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(true, productManager.addProduct("COLA"));
        assertEquals(1.00, productManager.getQuantityOrPrice("COLA"));
    }

    @Test
    public void addProductReturnsFalseForAnInvalidProduct(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(false, productManager.addProduct("BADPRODUCT"));
    }

    @Test
    public void getPricesOfAvailableProductsReturnsEmptyListWhenSoldOutOfAllProducts(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(0, productManager.getPricesOfAvailableProducts().size());
    }

    @Test
    public void getPricesOfAvailableProductsReturnsPartialListOfPricesWhenOnlySomeProductsAreAvailable(){
        ArrayList<Double> prices;
        ProductManager productManager = new ProductManager(1, 0, 0);
        prices = productManager.getPricesOfAvailableProducts();

        assertEquals(1, prices.size()); //should be three items
        assertEquals(true, prices.contains(1.0)); //should contain a $1 price
    }

    @Test
    public void getPricesOfAvailableProductsReturnsListOfThreePricesWhenAllProductsAreAvailable(){
        ArrayList<Double> prices;
        ProductManager productManager = new ProductManager(1, 1, 1);
        prices = productManager.getPricesOfAvailableProducts();

        assertEquals(3, prices.size()); //should be three items
        assertEquals(true, prices.contains(1.0)); //should contain a $1 price
        assertEquals(true, prices.contains(0.5)); //should contain a $0.5 price
        assertEquals(true, prices.contains(0.65)); //should contain a $0.65 price
    }
}