package Tests.Delivery;

import Delivery.Cargo.Cargo;
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

    // Tests the ability to add an item into an ordinary trucks cargo
    @Before
    @Test
    public void test3AddItemOrdTruck(){
        ordinaryTruck.addItem(new Item("rice", new BigDecimal(2), new BigDecimal(3), 225, 300), 1);
    }

    // Tests the ability to add an item into a refrigerated trucks cargo
    @Before
    @Test
    public void test4AddItemRefTruck(){
        refrigeratedTruck.addItem(new Item("ice", new BigDecimal(2), new BigDecimal(5), 225, 325, -20), 1);
    }

    // Tests the ordinary trucks class type
    @Test
    public void testOrdTruckClassType(){
        assertThat(ordinaryTruck, instanceOf(OrdinaryTruck.class));
    }

    // Tests the refrigerated trucks class type
    @Test
    public void testRefTruckClassType(){
        assertThat(refrigeratedTruck, instanceOf(RefrigeratedTruck.class));
    }

    // Tests the ordinary trucks cargo type
    @Test
    public void testOrdTruckCargoType(){
        assertThat(ordinaryTruck.getCargo(), instanceOf(Cargo.class));
    }

    // Tests the refrigerated trucks cargo type
    @Test
    public void testRefTruckCargoType(){
        assertThat(refrigeratedTruck.getCargo(), instanceOf(Cargo.class));
    }

    // Tests the ordinary trucks cost type
    @Test
    public void testOrdTruckCostType(){
        assertThat(ordinaryTruck.getCost(), instanceOf(BigDecimal.class));
    }

    // Tests the refrigerated trucks cost type
    @Test
    public void testRefTruckCostType(){
        assertThat(refrigeratedTruck.getCost(), instanceOf(BigDecimal.class));
    }

    // Tests the ordinary trucks capacity
    @Test
    public void testOrdTruckCapacity(){
        assertEquals(1000, ordinaryTruck.getCapacity());
    }

    // Tests the refrigerated trucks capacity
    @Test
    public void testRefTruckCapacity(){
        assertEquals(800, refrigeratedTruck.getCapacity());
    }
}
