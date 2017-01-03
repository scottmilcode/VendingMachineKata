///////////////////////
// @author Scott Miller
// @file InsertedCoinsTest.java
///////////////////////

package VendingMachine;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/////////////////////////////////////////////////////////////////////////////
// Coin Data
//  Coins are evaluated based on weight and size (diameter).  It is assumed
//  that the vending machine's mechanical system supports provided this
//  information using SI units.  We also assume the mechanism is extremely
//  if not perfectly accurate, providing one digit of significance.
//  If not, the tolerance of the measuring  mechanism would need to be
//  considered in the below calculations as well.
//
//
//  As coins are made to rather exacting specifications we expect that no
//  coin will be heavier or larger than its initial size.  Coins are
//  expected to wear with use.  As such, coins with slightly lower weights
//  and smaller diameters should be accepted.  In an idea world this data
//  would be driven by a measured distribution of coin weights/sizes, but for
//  our purposes we will accept coins up to -5% of original weight/size.
//  Basic calculations show that this value appears to be very reasonable.
//
//          Weight - grams  Size(Diameter) - mm
//          maxW  maxD  minW  minD
//  penny	19.0   2.5  18.0  2.4
//  nickel	21.2   5.0	20.1  4.8
//  dime	17.9   2.3	17.0  2.2
//  quarter	24.3   5.7	23.0  5.4
/////////////////////////////////////////////////////////////////////////////

public class InsertedCoinsTest {
    InsertedCoins insertedCoins; //InsertedCoins object to use for testing
    Display display; //Display object used for testing

    //Coin Insertion Tests

    @Test
    public void insertingANickelOfMaxWeightAndSizeShouldReturnFiveCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.05, insertedCoins.insertCoin(21.2, 5));
        assertEquals("$0.05", display.getMessage());
    }

    @Test
    public void insertingANickelOfMinWeightAndSizeShouldReturnFiveCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.05, insertedCoins.insertCoin(20.1, 4.8));
        assertEquals("$0.05", display.getMessage());
    }

    @Test
    public void insertingADimeOfMaxWeightAndSizeShouldReturnTenCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.10, insertedCoins.insertCoin(17.9, 2.3));
        assertEquals("$0.10", display.getMessage());
    }

    @Test
    public void insertingADimeOfMinWeightAndSizeShouldReturnTenCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.10, insertedCoins.insertCoin(17, 2.2));
        assertEquals("$0.10", display.getMessage());
    }

    @Test
    public void insertingAQuarterOfMaxWeightAndSizeShouldReturnTwentyFiveCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.25, insertedCoins.insertCoin(24.3,5.7));  //insert a dime
        assertEquals("$0.25", display.getMessage());
    }

    @Test
    public void insertingAQuarterOfMinWeightAndSizeShouldReturnTwentyFiveCentValueAndDisplayShouldUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.25, insertedCoins.insertCoin(23, 5.4));
        assertEquals("$0.25", display.getMessage());
    }


    @Test //Test to make sure a small penny isn't confused for a large dime
    public void insertingAPennyOfMinWeightAndSizeShouldReturnZeroCentValueAndDisplayShouldNotUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.0, insertedCoins.insertCoin(18.0,  2.4));
        assertEquals("INSERT COIN", display.getMessage());
    }

    @Test //Test to make sure a small penny isn't confused for a large dime
    public void insertingARandomInvalidCoinShouldReturnZeroCentValueAndDisplayShouldNotUpdate() {
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.0, insertedCoins.insertCoin(12.0,  1.8));
        assertEquals("INSERT COIN", display.getMessage());
    }

    //Coin Storage/Return/Use Tests

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsWhenNoCoinsInserted(){
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.0, insertedCoins.getAmountAvailableForPurchase());
    }


    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterOneCoinInserted(){
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.10, insertedCoins.insertCoin(17.9, 2.3)); //insert a dime
        assertEquals(0.10, insertedCoins.getAmountAvailableForPurchase());
    }

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterMultipleCoinsInserted(){
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.10, insertedCoins.insertCoin(17.9, 2.3));  //insert a dime
        assertEquals(0.25, insertedCoins.insertCoin(23, 5.4)); //insert a quarter
        assertEquals(0.35, insertedCoins.getAmountAvailableForPurchase());
    }

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterMultipleCoinsInsertedAndInvalidCoinInserted(){
        display = new Display(true); //setup display with return change available
        insertedCoins = new InsertedCoins(display); //passing display object to update
        assertEquals(0.10, insertedCoins.insertCoin(17.9, 2.3)); //insert a dime
        assertEquals(0.0, insertedCoins.insertCoin(12.0,  1.8)); //insert invalid coin
        assertEquals(0.10, insertedCoins.getAmountAvailableForPurchase());
    }
}


