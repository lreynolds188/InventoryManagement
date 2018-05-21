package Tests.Delivery;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class CargoTests {

    Cargo ordCargo, refCargo;

    /**
     * Tests the existence and ability to create an instance of required objects and their functions.
     */
    @Before@Test
    public void testInit (){
        // Instantiate cargo factory object
        CargoFactory cargoFactory = new CargoFactory();

        // Tests Cargo class creation passing it a boolean variable relative to whether the Cargo is refrigerated or not
        ordCargo = cargoFactory.getCargo(true);
        refCargo = cargoFactory.getCargo(false);
        System.out.print(refCargo.getClass());
    }

    /**
     * Tests that all non refrigerated items can be added to the ordinary trucks cargo, and none of the refrigerated items can
     */
    @Test
    public void testOrdinaryCargo()
    {
        // assert that the item list is of the correct data type
        assertThat(ordCargo.getCargo(), instanceOf(HashMap.class));

        // add items that can be added to the ordinary cargo
        HashMap<String, Integer> ordItemList = new HashMap<>();
        ordItemList.put("mushrooms", 1);
        ordItemList.put("tomatoes", 1);
        ordItemList.put("lettuce", 1);
        ordItemList.put("grapes", 1);
        ordItemList.put("asparagus", 1);
        ordItemList.put("celery", 1);
        ordItemList.put("chicken", 1);
        ordItemList.put("beef", 1);
        ordItemList.put("fish", 1);
        ordItemList.put("yoghurt", 1);
        ordItemList.put("milk", 1);
        ordItemList.put("cheese", 1);
        ordItemList.put("ice cream", 1);
        ordItemList.put("ice", 1);
        ordItemList.put("frozen meat", 1);
        ordItemList.put("frozen vegetable mix", 1);

        // attempt to add all items to the ordinary cargo object


        // Tests that only non refrigerated food was able to be entered into the ordinary cargo
        assertEquals("Refrigerated food was able to be stored in the ordinary trucks cargo", ordItemList, ordCargo.getCargo());
    }

    /**
     * Tests that all items can be stored in the refrigerated trucks cargo
     */
    @Test
    public void testRefrigeratedCargo(){
        // assert that the item list is of the correct data type
        assertThat(refCargo.getCargo(), instanceOf(HashMap.class));

        // attempt to add all items to the ordinary cargo object
        HashMap<String, Integer> refItemList = new HashMap<>();
        refItemList.put("rice", 1);
        refItemList.put("beans", 1);
        refItemList.put("pasta", 1);
        refItemList.put("biscuits", 1);
        refItemList.put("nuts", 1);
        refItemList.put("chips", 1);
        refItemList.put("chocolate", 1);
        refItemList.put("bread", 1);
        refItemList.put("mushrooms", 1);
        refItemList.put("tomatoes", 1);
        refItemList.put("lettuce", 1);
        refItemList.put("grapes", 1);
        refItemList.put("asparagus", 1);
        refItemList.put("celery", 1);
        refItemList.put("chicken", 1);
        refItemList.put("beef", 1);
        refItemList.put("fish", 1);
        refItemList.put("yoghurt", 1);
        refItemList.put("milk", 1);
        refItemList.put("cheese", 1);
        refItemList.put("ice cream", 1);
        refItemList.put("ice", 1);
        refItemList.put("frozen meat", 1);
        refItemList.put("frozen vegetable mix", 1);

        // attempt to add all items to the ordinary cargo object

        // Tests that all food was able to be entered into the refrigerated cargo
        assertEquals("Not all items were able to be stored into the refrigerated trucks cargo" ,refItemList, refCargo.getCargo());
    }
}
