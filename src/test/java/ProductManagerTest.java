///////////////////////////////
// @author Scott Miller
// @file ProductManagerTest.java
///////////////////////////////

package VendingMachine;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/////////////////////////////////////////////////////////////////////////////
// @class ProductManager
// @brief This class handles product inventory including prices and how
//        many of each product are available.
/////////////////////////////////////////////////////////////////////////////
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

}
