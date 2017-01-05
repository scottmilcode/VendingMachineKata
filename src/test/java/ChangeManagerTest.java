///////////////////////////////
// @author Scott Miller
// @file ChangeManagerTest.java
///////////////////////////////
package VendingMachine;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;


public class ChangeManagerTest{
    ArrayList<Integer> productPrices; //@var productPrice array list to use for testing

    @Before
    public void setup() {
        productPrices = new ArrayList<Integer>();
        productPrices.add(100);
        productPrices.add(65);
        productPrices.add(50);
    }

    //All tests for correct change assume that the lowest number of coins based on available
    //change will be returned.

    @Test
    public void whenNoChangeIsRequiredMakeChangeReturnsNoChange(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();

        assertEquals(true, changeManager.makeChange(50, 50, actualReturnedChange));
        assertEquals(0, actualReturnedChange.size());
    }

    @Test
    public void whenOneQuarterShouldBeReturnedMakeChangeReturnsOneQuarter(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(25);

        assertEquals(true, changeManager.makeChange(50, 75, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenOneDimeShouldBeReturnedMakeChangeReturnsOneDime(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(10);

        assertEquals(true, changeManager.makeChange(50, 60, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenOneNickelShouldBeReturnedMakeChangeReturnsOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(5);

        assertEquals(true, changeManager.makeChange(50, 55, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    //Tests that check for for proper coins being returned when the optimal coin is not available
    //e.g.) two dimes and one nickel when quarters are not available

    @Test
    public void whenTwentyFiveCentsShouldBeReturnedAndNoQuarterAvailableMakeChangeReturnsTwoDimesAndOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 2, 0);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(10);
        properReturnedChange.add(10);
        properReturnedChange.add(5);

        assertEquals(true, changeManager.makeChange(50, 75, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenTwentyFiveCentsShouldBeReturnedAndNoQuarterOrDimeAvailableMakeChangeReturnsFiveNickels(){
        ChangeManager changeManager = new ChangeManager(5, 0, 0);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(5);
        properReturnedChange.add(5);
        properReturnedChange.add(5);
        properReturnedChange.add(5);
        properReturnedChange.add(5);

        assertEquals(true, changeManager.makeChange(50, 75, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenTenCentsShouldBeReturnedAndNoDimeAvailableMakeChangeReturnsTwoNickels(){
        ChangeManager changeManager = new ChangeManager(2, 0, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(5);
        properReturnedChange.add(5);

        assertEquals(true, changeManager.makeChange(50, 60, actualReturnedChange));
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    //Tests for change being available

    @Test
    public void whenNoChangeCoinsInMachineIsChangeAvailableReturnsFalse(){
        ChangeManager changeManager = new ChangeManager(0, 0, 0);
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenLotsOfChangeCoinsInMachineIsChangeAvailableReturnsTrue(){
        ChangeManager changeManager = new ChangeManager(10, 10, 10);
        assertEquals(true, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenEnoughNickelsAsChangeCoinsInMachineAndNoOthersIsChangeAvailableReturnsTrue(){
        ChangeManager changeManager = new ChangeManager(14, 0, 0);
        assertEquals(true, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenOnlyQuatersAsChangeCoinsInMachineAndNoOthersIsChangeAvailableReturnsFalse(){
        ChangeManager changeManager = new ChangeManager(0, 0, 10);
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenOnlyDimesAsChangeCoinsInMachineAndNoOthersIsChangeAvailableReturnsFalse(){
        ChangeManager changeManager = new ChangeManager(0, 10, 0);
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenOnlyDimesAndQuartersAsChangeCoinsInMachineAndNoOthersIsChangeAvailableReturnsFalse(){
        ChangeManager changeManager = new ChangeManager(0, 10, 10);
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenOnlyOneOfEachCoinAsChangeCoinsInMachineIsChangeAvailableReturnsFalse(){ //corner case here is needing 20 cents
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenChangeIsMadeCoinsAvailableShouldReduceMakingIsChangeAvailableReturnsFalse(){ //corner case here is needing 20 cents
        ChangeManager changeManager = new ChangeManager(2, 2, 2);
        assertEquals(true, changeManager.isChangeAvailable(productPrices));
        assertEquals(true, changeManager.makeChange(100, 120, new ArrayList<Integer>())); //should remove two dimes
        assertEquals(false, changeManager.isChangeAvailable(productPrices));
    }

    @Test
    public void whenChangeIsAvailableCheckingForChangeRepeatedlyShouldAlwaysReturnTrue(){ //corner case here is needing 20 cents
        ChangeManager changeManager = new ChangeManager(2, 2, 2);

        assertEquals(true, changeManager.isChangeAvailable(productPrices));
        assertEquals(true, changeManager.isChangeAvailable(productPrices));
    }
}