package Tests.Delivery;

import Delivery.Cargo;
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
        // Tests Cargo class creation passing it a boolean variable relative to whether the Cargo is refrigerated or not
        ordCargo = new Cargo(false);
        refCargo = new Cargo(true);
    }

    /**
     * Tests that all non refrigerated items can be added to the ordinary trucks cargo, and none of the refrigerated items can
     */
    @Test
    public void testOrdinaryCargo()
    {
        // assert that the item list is of the correct data type
        assertThat(ordCargo.getItemList(), instanceOf(HashMap.class));

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
        ordCargo.addItem("rice", 1);
        ordCargo.addItem("beans", 1);
        ordCargo.addItem("pasta", 1);
        ordCargo.addItem("biscuits", 1);
        ordCargo.addItem("nuts", 1);
        ordCargo.addItem("chips", 1);
        ordCargo.addItem("chocolate", 1);
        ordCargo.addItem("bread", 1);
        ordCargo.addItem("mushrooms", 1);
        ordCargo.addItem("tomatoes", 1);
        ordCargo.addItem("lettuce", 1);
        ordCargo.addItem("grapes", 1);
        ordCargo.addItem("asparagus", 1);
        ordCargo.addItem("celery", 1);
        ordCargo.addItem("chicken", 1);
        ordCargo.addItem("beef", 1);
        ordCargo.addItem("fish", 1);
        ordCargo.addItem("yoghurt", 1);
        ordCargo.addItem("milk", 1);
        ordCargo.addItem("cheese", 1);
        ordCargo.addItem("ice cream", 1);
        ordCargo.addItem("ice", 1);
        ordCargo.addItem("frozen meat", 1);
        ordCargo.addItem("frozen vegetable mix", 1);

        // Tests that only non refrigerated food was able to be entered into the ordinary cargo
        assertEquals("Refrigerated food was able to be stored in the ordinary trucks cargo", ordItemList, ordCargo.getItemList());
    }

    /**
     * Tests that all items can be stored in the refrigerated trucks cargo
     */
    @Test
    public void testRefrigeratedCargo(){
        // assert that the item list is of the correct data type
        assertThat(refCargo.getItemList(), instanceOf(HashMap.class));

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
        refCargo.addItem("rice", 1);
        refCargo.addItem("beans", 1);
        refCargo.addItem("pasta", 1);
        refCargo.addItem("biscuits", 1);
        refCargo.addItem("nuts", 1);
        refCargo.addItem("chips", 1);
        refCargo.addItem("chocolate", 1);
        refCargo.addItem("bread", 1);
        refCargo.addItem("mushrooms", 1);
        refCargo.addItem("tomatoes", 1);
        refCargo.addItem("lettuce", 1);
        refCargo.addItem("grapes", 1);
        refCargo.addItem("asparagus", 1);
        refCargo.addItem("celery", 1);
        refCargo.addItem("chicken", 1);
        refCargo.addItem("beef", 1);
        refCargo.addItem("fish", 1);
        refCargo.addItem("yoghurt", 1);
        refCargo.addItem("milk", 1);
        refCargo.addItem("cheese", 1);
        refCargo.addItem("ice cream", 1);
        refCargo.addItem("ice", 1);
        refCargo.addItem("frozen meat", 1);
        refCargo.addItem("frozen vegetable mix", 1);

        // Tests that all food was able to be entered into the refrigerated cargo
        assertEquals("Not all items were able to be stored into the refrigerated trucks cargo" ,refItemList, refCargo.getItemList());
    }
}
