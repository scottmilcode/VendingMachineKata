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
    //change will be returned. Other options would have

    @Test
    public void whenNoChangeIsRequiredMakeChangeReturnsNoChange(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        assertEquals(0, changeManager.makeChange(0.50, 0.50).size());
    }

    @Test
    public void whenOneQuarterShouldBeReturnedMakeChangeReturnsOneQuarter(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.25);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.75));
    }

    @Test
    public void whenOneDimeShouldBeReturnedMakeChangeReturnsOneDime(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.10);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.65));
    }

    @Test
    public void whenOneNickelShouldBeReturnedMakeChangeReturnsOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 1, 1);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.05);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.55));
    }

    //Test that check for for proper coins being returned when the optimal coin is not available
    //e.g.) two dimes and one nickel when quarters are not available

    @Test
    public void whenTwentyFiveCentsShouldBeReturnedAndNoQuarterAvailableMakeChangeReturnsTwoDimesAndOneNickel(){
        ChangeManager changeManager = new ChangeManager(1, 2, 0);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.10);
        returnedChange.add(0.10);
        returnedChange.add(0.05);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.75));
    }

    @Test
    public void whenTwentyFiveCentsShouldBeReturnedAndNoQuarterOrDimeAvailableMakeChangeReturnsFiveNickels(){
        ChangeManager changeManager = new ChangeManager(5, 0, 0);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.10);
        returnedChange.add(0.10);
        returnedChange.add(0.05);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.75));
    }

    @Test
    public void whenTenCentsShouldBeReturnedAndNoDimeAvailableMakeChangeReturnsTwoNickels(){
        ChangeManager changeManager = new ChangeManager(2, 0, 1);
        ArrayList<Double> returnedChange = new ArrayList<Double>();
        returnedChange.add(0.05);
        returnedChange.add(0.05);

        assertEquals(returnedChange, changeManager.makeChange(0.50, 0.60));
    }
}
