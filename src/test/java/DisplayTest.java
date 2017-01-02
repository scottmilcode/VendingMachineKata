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

   //Default message test cases
    @Test
    public void afterInitializationWithTrueGetMessageReturnsInsertCoin() {
        display = new Display(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void afterInitializationWithFalseGetMessageReturnsInsertCoin() {
        display = new Display(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void afterInitializationWithFalseAndSetChangeToTrueGetMessageReturnsInsertCoin() {
        display = new Display(false);
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void afterInitializationWithTrueAndSetChangeToFalseGetMessageReturnsInsertCoin() {
        display = new Display(true);
        display.setChangeAvailable(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void whenChangeAvailableGetMessageReturnsExactChangeOnly() {
        display = new Display(false);
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenProductVendedAndChangeStillAvailableGetMessageReturnsThankYouFollowedByInsertCoin() {
        display = new Display(true);
        display.setProductVended();
        assertEquals("THANK YOU", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage()); //this check to ensure that the message returns to appropriate default
    }

    @Test
    public void whenProductSoldOutAndChangeStillAvailableGetMessageReturnsProductSoldOutFollowedByInsertCoin() {
        display = new Display(true);
        display.setProductSoldOut();
        assertEquals("SOLD OUT", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenProductSelectedGetMessageReturnsPriceAndProductPriceFollowedByInsertCoin(){
        display = new Display(true);
        display.setProductSelected(0.65);
        assertEquals("PRICE $0.65", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenChangeInsertedGetMessageReturnsTotalAmountAvailableForPurchase(){
        display = new Display(true);
        display.coinInserted(0.05);
        assertEquals("$0.05", display.getMessage());
        display.coinInserted(0.10);
        assertEquals("$0.15", display.getMessage());
    }

    @Test
    public void whenChangeInsertedGetMessageReturnsTotalAmountAvailableForPurchaseThenAfterPurchaseThankYouThenInsertCoin() {
        display = new Display(true);
        display.coinInserted(0.05);
        assertEquals("$0.05", display.getMessage());
        display.coinInserted(0.10);
        assertEquals("$0.15", display.getMessage());
        display.setProductVended();
        assertEquals("THANK YOU", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }
}