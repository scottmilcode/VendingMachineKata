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
    public void setUp(){
        display = new Display(true);
    }

    @Test
    public void afterInitializationWithTrueGetMessageReturnsInsertCoin(){
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test
    public void whenChangeAvailableFlagSetFalseGetMessageReturnsExactChangeOnly(){
        display.setChangeAvailable(false);
        assertEquals("EXACT CHANGE ONLY", display.getMessage());
    }

    @Test
    public void whenChangeAvailableFlagSetTrueGetMessageReturnsExactChangeOnly(){
        display.setChangeAvailable(true);
        assertEquals("INSERT COIN", display.getMessage());
    }
}