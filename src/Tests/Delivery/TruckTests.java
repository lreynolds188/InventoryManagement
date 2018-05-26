package Tests.Delivery;

import Delivery.Trucks.OrdinaryTruck;
import Delivery.Trucks.RefrigeratedTruck;
import Delivery.Trucks.Truck;
import Delivery.Trucks.TruckFactory;
import Stock.Item;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class TruckTests {

    Truck ordinaryTruck, refrigeratedTruck;

    // Tests the existence and ability to create an instance of required objects and their functions.
    @Before
    @Test
    public void test1OrdTruckInit (){
        ordinaryTruck = TruckFactory.generateTruck(false);
    }

    // Tests the existence and ability to create an instance of required objects and their functions.
    @Before
    @Test
    public void test2RefTruckInit (){
        refrigeratedTruck = TruckFactory.generateTruck(true);
    }

    //
    @Before
    @Test
    public void test3AddItemOrdTruck(){
        ordinaryTruck.addItem(new Item("rice", new BigDecimal(2), new BigDecimal(3), 225, 300), 1);
    }

    //
    @Before
    @Test
    public void test4AddItemRefTruck(){
        refrigeratedTruck.addItem(new Item("ice", new BigDecimal(2), new BigDecimal(5), 225, 325, -20), 1);
    }

    //
    @Test
    public void testOrdTruckType(){
        assertThat(ordinaryTruck, instanceOf(OrdinaryTruck.class));
    }

    //
    @Test
    public void testRefTruckType(){
        assertThat(refrigeratedTruck, instanceOf(RefrigeratedTruck.class));
    }

    //
    @Test
    public void testOrdTruckCostType(){
        assertThat(ordinaryTruck.getCost(), instanceOf(BigDecimal.class));
    }

    //
    @Test
    public void testRefTruckCostType(){
        assertThat(refrigeratedTruck.getCost(), instanceOf(BigDecimal.class));
    }

    // Tests the ordinary and refrigerated trucks capacity
    @Test
    public void testOrdTruckCapacity(){
        assertEquals("Ordinary trucks capacity inaccurate", 1000, ordinaryTruck.getCapacity());
    }

    //
    @Test
    public void testRefTruckCapacity(){
        assertEquals("Refrigerated trucks capacity inaccurate", 800, refrigeratedTruck.getCapacity());
    }
}
