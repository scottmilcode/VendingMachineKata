///////////////////////
// @author Scott Miller
// @file DisplayTest.java
///////////////////////

package VendingMachine;
import org.junit.Test;
import org.junit.Before;
import static junit.framework.TestCase.assertEquals;


public class DisplayTest {
    Display display; //Display object used for testing

    @Before
    public void setUp() {
        display = new Display(true);
    }

    //Default message test cases
    @Test
    public void afterInitializationWithTrueGetMessageReturnsInsertCoin() {
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenChangeNotAvailableGetMessageReturnsExactChangeOnly() {
        display.setChangeAvailable(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void whenChangeAvailableGetMessageReturnsExactChangeOnly() {
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenProductVendedAndChangeStillAvailableGetMessageReturnsThankYouFollowedByInsertCoin() {
        display.setProductVended();
        assertEquals("THANK YOU", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage()); //this check to ensure that the message returns to appropriate default
    }

    @Test
    public void whenProductSoldOutAndChangeStillAvailableGetMessageReturnsProductSoldOutFollowedByInsertCoin() {
        display.setProductSoldOut();
        assertEquals("SOLD OUT", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }
}