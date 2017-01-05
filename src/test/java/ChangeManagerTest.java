///////////////////////////////
// @author Scott Miller
// @file ChangeManagerTest.java
///////////////////////////////
package VendingMachine;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;


public class ChangeManagerTest {

    //All tests for correct change assume that the lowest number of coins based on available
    //change will be returned.

    @Test
    public void whenNoChangeIsRequiredMakeChangeReturnsNoChange(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();

        changeManager.makeChange(50, 50, actualReturnedChange);
        assertEquals(0, actualReturnedChange.size());
    }

    @Test
    public void whenOneQuarterShouldBeReturnedMakeChangeReturnsOneQuarter(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(25);

        changeManager.makeChange(50, 75, actualReturnedChange);
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenOneDimeShouldBeReturnedMakeChangeReturnsOneDime(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(10);

        changeManager.makeChange(50, 60, actualReturnedChange);
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenOneNickelShouldBeReturnedMakeChangeReturnsOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(5);

        changeManager.makeChange(50, 55, actualReturnedChange);
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    //Test that check for for proper coins being returned when the optimal coin is not available
    //e.g.) two dimes and one nickel when quarters are not available

    @Test
    public void whenTwentyFiveCentsShouldBeReturnedAndNoQuarterAvailableMakeChangeReturnsTwoDimesAndOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 2, 0);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(10);
        properReturnedChange.add(10);
        properReturnedChange.add(5);

        changeManager.makeChange(50, 75, actualReturnedChange);
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

        changeManager.makeChange(50, 75, actualReturnedChange);
        assertEquals(properReturnedChange, actualReturnedChange);
    }

    @Test
    public void whenTenCentsShouldBeReturnedAndNoDimeAvailableMakeChangeReturnsTwoNickels(){
        ChangeManager changeManager = new ChangeManager(2, 0, 1);
        ArrayList<Integer> actualReturnedChange = new ArrayList<Integer>();
        ArrayList<Integer> properReturnedChange = new ArrayList<Integer>();
        properReturnedChange.add(05);
        properReturnedChange.add(05);

        changeManager.makeChange(50, 60, actualReturnedChange);
        assertEquals(properReturnedChange, actualReturnedChange);
    }
}
