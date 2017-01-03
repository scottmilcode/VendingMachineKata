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
    public void atInitializationProductManagerAcceptsInitialInventoryAndConfirms(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(1.00, productManager.getCountOrPrice("COLA"));
        assertEquals(0.50, productManager.getCountOrPrice("CHIPS"));
        assertEquals(0.65, productManager.getCountOrPrice("CANDY"));
    }

    @Test
    public void atInitializationProductManagerAcceptsEmptyInitialInventoryAndConfirmsNoPriceForSoldOut(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(0.0, productManager.getCountOrPrice("COLA"));
        assertEquals(0.0, productManager.getCountOrPrice("CHIPS"));
        assertEquals(0.0, productManager.getCountOrPrice("CANDY"));
    }

    @Test
    public void getCountOrPriceReturnsNegativeOneForInvalidProduct(){
        ProductManager productManager = new ProductManager(0, 0, 0);
        assertEquals(-1.0, productManager.getCountOrPrice("BADPRODCUT"));
    }

    @Test
    public void afterASingleProductIsRemovedGetCountOfPriceReturnsNoPriceForSoldOut(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(true, productManager.removeProduct("COLA"));
        assertEquals(0.0, productManager.getCountOrPrice("COLA"));
    }

    @Test
    public void removeProductReturnsFalseForAnInvalidProduct(){
        ProductManager productManager = new ProductManager(1, 1, 1);
        assertEquals(false, productManager.removeProduct("BADPRODUCT"));
    }

    @Test
    public void removeProductReturnsFalseForAProductThatIsOutOfStock(){
        ProductManager productManager = new ProductManager(0, 1, 1);
        assertEquals(false, productManager.removeProduct("COLA"));
    }
}
