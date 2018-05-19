package Tests.Delivery;

import Delivery.TruckAbst.OrdinaryTruck;
import Delivery.TruckAbst.Refrigerated_Truck;
import Delivery.TruckAbst.Truck;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class TruckTests {

    Truck ordinaryTruck, refrigeratedTruck;

    /**
     * Tests the existence and ability to create an instance of required objects and their functions.
     */
    @Before
    @Test
    public void testInit (){
        ordinaryTruck = new OrdinaryTruck();
        refrigeratedTruck = new Refrigerated_Truck();
    }

    /**
     * Tests the ordinary and refrigerated trucks cost
     */
    @Test
    public void testTruckCost(){
        assertEquals("Ordinary trucks cost inaccurate", BigDecimal.valueOf(750.50).setScale(2, BigDecimal.ROUND_CEILING) ,ordinaryTruck.getCost());
        assertEquals("Refrigerated trucks cost inaccurate", BigDecimal.valueOf(1732.99).setScale(2, BigDecimal.ROUND_CEILING) ,refrigeratedTruck.getCost());
    }

    /**
     * Tests the ordinary and refrigerated trucks capacity
     */
    @Test
    public void testTruckCapacity(){
        assertEquals("Ordinary trucks capacity inaccurate", 1000, ordinaryTruck.get_capacity());
        assertEquals("Refrigerated trucks capacity inaccurate", 800, refrigeratedTruck.get_capacity());
    }

    /**
     * Tests the refrigerated trucks temperature
     */
    @Test
    public void testTruckTemp(){
        assertEquals("Refrigerated trucks temperature inaccurate", -20 ,refrigeratedTruck.get_temperature());
    }
}
