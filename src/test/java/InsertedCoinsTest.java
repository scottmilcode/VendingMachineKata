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

public class InsertedCoinsTest{
    InsertedCoins insertedCoins; //InsertedCoins object to use for testing

    //Coin Insertion Tests

    @Test
    public void insertingANickelOfMaxWeightAndSizeShouldReturnFiveCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(5, insertedCoins.insertCoin(21.2, 5));
    }

    @Test
    public void insertingANickelOfMinWeightAndSizeShouldReturnFiveCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(5, insertedCoins.insertCoin(20.1, 4.8));
    }

    @Test
    public void insertingADimeOfMaxWeightAndSizeShouldReturnTenCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17.9, 2.3));
    }

    @Test
    public void insertingADimeOfMinWeightAndSizeShouldReturnTenCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17, 2.2));
    }

    @Test
    public void insertingAQuarterOfMaxWeightAndSizeShouldReturnTwentyFiveCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(25, insertedCoins.insertCoin(24.3,5.7));  //insert a dime
    }

    @Test
    public void insertingAQuarterOfMinWeightAndSizeShouldReturnTwentyFiveCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(25, insertedCoins.insertCoin(23, 5.4));
    }


    @Test //Test to make sure a small penny isn't confused for a large dime
    public void insertingAPennyOfMinWeightAndSizeShouldReturnZeroCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(0, insertedCoins.insertCoin(18.0,  2.4));
    }

    @Test //Test to make sure a small penny isn't confused for a large dime
    public void insertingARandomInvalidCoinShouldReturnZeroCentValue(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(0, insertedCoins.insertCoin(12.0,  1.8));
    }

    //Coin Storage/Return/Use Tests

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsWhenNoCoinsInserted(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(0, insertedCoins.getAmountAvailableForPurchase());
    }


    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterOneCoinInserted(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17.9, 2.3)); //insert a dime
        assertEquals(10, insertedCoins.getAmountAvailableForPurchase());
    }

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterMultipleCoinsInserted(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17.9, 2.3));  //insert a dime
        assertEquals(25, insertedCoins.insertCoin(23, 5.4)); //insert a quarter
        assertEquals(35, insertedCoins.getAmountAvailableForPurchase());
    }

    @Test
    public void getTotalAmountReturnsTotalValueOfValidCoinsAfterMultipleCoinsInsertedAndInvalidCoinInserted(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17.9, 2.3)); //insert a dime
        assertEquals(0, insertedCoins.insertCoin(12.0,  1.8)); //insert invalid coin
        assertEquals(10, insertedCoins.getAmountAvailableForPurchase());
    }

    @Test
    public void resetCoinsClearedClearsAmountAvailableForPurchase(){
        insertedCoins = new InsertedCoins(); //passing display object to update
        assertEquals(10, insertedCoins.insertCoin(17.9, 2.3)); //insert a dime
        insertedCoins.resetAmountAvailableForPurchase();
        assertEquals(0, insertedCoins.getAmountAvailableForPurchase());
    }
}