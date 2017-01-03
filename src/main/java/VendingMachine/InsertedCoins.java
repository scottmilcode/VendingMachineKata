///////////////////////////////
// @author Scott Miller
// @file InsertedCoinsTest.java
///////////////////////////////
package VendingMachine;

/////////////////////////////////////////////////////////////////////////////
// @class InsertedCoins
// @brief Class handles coins being inserted into the machine.  In a
//        physical implementation some mechanical event would cause class
//        functions to be called.  Similarly functions like sending
//        coins to the return slot would activate mechanical actions.
//
//        At current all classes directly interface with the display
//        when state changes that need to be communicated to the user occur.
//
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
public class InsertedCoins {

    /////////////////////////////////////////////////////////////////////////////
    // @class Coin
    // @params Declared Coin information as a private enum for simplicity sake.
    //         Ideally this data would be loaded from an outside source like an
    //         xml file or database.  This was the system could be easily updated
    //         without modifying code if coins were to be changed.
    /////////////////////////////////////////////////////////////////////////////
    private enum Coin {
        NICKEL  (0.05, 21.2, 5.0, 20.1, 4.8),
        DIME    (0.10, 17.9, 2.3, 17.0, 2.2),
        QUARTER (0.25, 24.3, 5.7, 23.0, 5.4);

        private final double Value; // in dollars
        private final double MinWeight; // in grams
        private final double MaxWeight; // in grams
        private final double MinDiameter; // in millimeters
        private final double MaxDiameter; // in millimeters

        /////////////////////////////////////////////////////////////////////////////
        // @brief Default Constructor
        /////////////////////////////////////////////////////////////////////////////
        Coin(double value, double maxWeight, double maxDiameter, double minWeight, double minDiameter){
            Value = value;
            MaxWeight = maxWeight;
            MinWeight = minWeight;
            MaxDiameter = maxDiameter;
            MinDiameter = minDiameter;
        }

        //Getters
        private double getValue(){ return Value; }
        private double getMaxWeight(){ return MaxWeight; }
        private double getMinWeight(){ return MinWeight; }
        private double getMaxDiameter(){ return MaxDiameter; }
        private double getMinDiameter(){ return MinDiameter; }
    }


    Display TheDisplay; // @var Display object being interfaced with
    private double TotalAmountAvailable; // @var Stores the total value of valid coins that can be used for a purchase

    /////////////////////////////////////////////////////////////////////////////
    // @brief Default Constructor
    // @params display - Vending machines display that needs updated about
    //                   state changes.
    /////////////////////////////////////////////////////////////////////////////
    public InsertedCoins(Display display){
        TheDisplay = display;
    }

    /////////////////////////////////////////////////////////////////////////////
    // @brief Verifies valid coins based on detected weight and diameter
    // @params weight - Measured weight of coin in grams
    //         diameter - Measured diameter of coin in mm
    // @returns value of coin, 0.0 is coin is invalid.
    /////////////////////////////////////////////////////////////////////////////
    public double insertCoin(double weight, double diameter){
        double returnValue = 0.0; //default to 0 in the case of an invalid coin

        //Loop over accepted coins and see if we have a match
        for (Coin c : Coin.values()) {
            if( (weight >= c.getMinWeight() && weight <= c.getMaxWeight()) && (diameter >= c.getMinDiameter() && diameter <= c.getMaxDiameter()) ){
                returnValue = c.getValue();
            }
        }

        if(0 < returnValue){ //This check technically isn't necessary with the design of Dispaly.coinInserted() but an extra safety.
            TheDisplay.coinInserted(returnValue); //Send information about state change to display class
        }

        TotalAmountAvailable += returnValue;
        return(returnValue);
    }

    public double getAmountAvailableForPurchase(){
        return TotalAmountAvailable;
    }
}
