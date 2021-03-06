///////////////////////
// @author Scott Miller
// @file DisplayTest.java
///////////////////////
package VendingMachine;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class DisplayTest{
    Display display; //Display object used for testing

    @Test
    public void afterInitializationWithTrueGetMessageReturnsInsertCoin(){
        display = new Display(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void afterInitializationWithFalseGetMessageReturnsInsertCoin(){
        display = new Display(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void afterInitializationWithFalseAndSetChangeToTrueGetMessageReturnsInsertCoin(){
        display = new Display(false);
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void afterInitializationWithTrueAndSetChangeToFalseGetMessageReturnsInsertCoin(){
        display = new Display(true);
        display.setChangeAvailable(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void whenChangeAvailableGetMessageReturnsInsertCoin(){
        display = new Display(false);
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenProductVendedAndChangeStillAvailableGetMessageReturnsThankYouFollowedByInsertCoin(){
        display = new Display(true);
        display.setProductVended();
        assertEquals("THANK YOU", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage()); //this check to ensure that the message returns to appropriate default
    }

    @Test
    public void whenProductSoldOutAndChangeStillAvailableGetMessageReturnsProductSoldOutFollowedByInsertCoin(){
        display = new Display(true);
        display.setProductSoldOut();
        assertEquals("SOLD OUT", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenProductSelectedGetMessageReturnsPriceAndProductPriceFollowedByInsertCoin(){
        display = new Display(true);
        display.setProductSelected(65);
        assertEquals("PRICE $0.65", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenDimeInsertedGetMessageReturnsTotalAmountAvailableForPurchaseWithProperNumberOfDecimalPlaces(){
        display = new Display(true);
        display.coinInserted(10);
        assertEquals("$0.10", display.getMessage());
    }

    @Test
    public void whenChangeInsertedGetMessageReturnsTotalAmountAvailableForPurchase(){
        display = new Display(true);
        display.coinInserted(5);
        assertEquals("$0.05", display.getMessage());
        display.coinInserted(10);
        assertEquals("$0.15", display.getMessage());
    }

    @Test
    public void whenChangeInsertedGetMessageReturnsTotalAmountAvailableForPurchaseThenAfterPurchaseThankYouThenInsertCoin(){
        display = new Display(true);
        display.coinInserted(5);
        assertEquals("$0.05", display.getMessage());
        display.coinInserted(10);
        assertEquals("$0.15", display.getMessage());
        display.setProductVended();
        assertEquals("THANK YOU", display.getMessage());
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void resetAmountAvailableResetsDisplayAmountToZero(){
        display = new Display(true);
        display.coinInserted(5);
        assertEquals("$0.05", display.getMessage());
        display.resetAmountAvailableForPurchase();
        assertEquals("INSERT COIN", display.getMessage());
    }
}