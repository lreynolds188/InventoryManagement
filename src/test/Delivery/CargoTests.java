package test.Delivery;

import Delivery.Cargo;

import Stock.Item;

import org.junit.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

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
        // test Cargo class creation passing it a boolean variable relative to whether the Cargo is refrigerated or not
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
        assertThat(ordCargo.getItemList, instanceOf(HashMap.class));

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
        ordCargo.addItem(new Item("rice", new BigDecimal(2),new BigDecimal(3),225,300), 1);
        ordCargo.addItem(new Item("beans", new BigDecimal(4),new BigDecimal(6),450,525), 1);
        ordCargo.addItem(new Item("pasta", new BigDecimal(3),new BigDecimal(4),125,250), 1);
        ordCargo.addItem(new Item("biscuits",new BigDecimal(2),new BigDecimal(5),450,575), 1);
        ordCargo.addItem(new Item("nuts",new BigDecimal(5),new BigDecimal(9),125,250), 1);
        ordCargo.addItem(new Item("chips",new BigDecimal(2),new BigDecimal(4),125,200), 1);
        ordCargo.addItem(new Item("chocolate",new BigDecimal(5),new BigDecimal(8),250,375), 1);
        ordCargo.addItem(new Item("bread",new BigDecimal(2),new BigDecimal(3),125,200), 1);
        ordCargo.addItem(new Item("mushrooms",new BigDecimal(2),new BigDecimal(4),200,325,10), 1);
        ordCargo.addItem(new Item("tomatoes",new BigDecimal(1),new BigDecimal(2),325,400,10), 1);
        ordCargo.addItem(new Item("lettuce",new BigDecimal(1),new BigDecimal(2),250,350,10), 1);
        ordCargo.addItem(new Item("grapes",new BigDecimal(4),new BigDecimal(6),125,225,9), 1);
        ordCargo.addItem(new Item("asparagus",new BigDecimal(2),new BigDecimal(4),175,275,8), 1);
        ordCargo.addItem(new Item("celery",new BigDecimal(2),new BigDecimal(3),225,350,8), 1);
        ordCargo.addItem(new Item("chicken",new BigDecimal(10),new BigDecimal(14),325,425,4), 1);
        ordCargo.addItem(new Item("beef",new BigDecimal(12),new BigDecimal(17),425,550,3), 1);
        ordCargo.addItem(new Item("fish",new BigDecimal(13),new BigDecimal(16),375,475,2), 1);
        ordCargo.addItem(new Item("yoghurt",new BigDecimal(10),new BigDecimal(12),200,325,3), 1);
        ordCargo.addItem(new Item("milk",new BigDecimal(2),new BigDecimal(3),300,425,3), 1);
        ordCargo.addItem(new Item("cheese",new BigDecimal(4),new BigDecimal(7),375,450,3), 1);
        ordCargo.addItem(new Item("ice cream",new BigDecimal(8),new BigDecimal(14),175,250,-20), 1);
        ordCargo.addItem(new Item("ice",new BigDecimal(2),new BigDecimal(5),225,325,-10), 1);
        ordCargo.addItem(new Item("frozen meat",new BigDecimal(10),new BigDecimal(14),450,575,-14), 1);
        ordCargo.addItem(new Item("frozen vegetable mix",new BigDecimal(5),new BigDecimal(8),225,325,-12), 1);

        // test that only non refrigerated food was able to be entered into the ordinary cargo
        assertEquals("Refrigerated food was able to be stored in the ordinary trucks cargo", ordItemList, ordCargo.getItemList());
    }

    /**
     * Tests that all items can be stored in the refrigerated trucks cargo
     */
    @Test
    public void testRefrigeratedCargo(){
        // assert that the item list is of the correct data type
        assertThat(refCargo.getItemList, instanceOf(HashMap.class));

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
        refCargo.addItem(new Item("rice", new BigDecimal(2),new BigDecimal(3),225,300), 1);
        refCargo.addItem(new Item("beans", new BigDecimal(4),new BigDecimal(6),450,525), 1);
        refCargo.addItem(new Item("pasta", new BigDecimal(3),new BigDecimal(4),125,250), 1);
        refCargo.addItem(new Item("biscuits",new BigDecimal(2),new BigDecimal(5),450,575), 1);
        refCargo.addItem(new Item("nuts",new BigDecimal(5),new BigDecimal(9),125,250), 1);
        refCargo.addItem(new Item("chips",new BigDecimal(2),new BigDecimal(4),125,200), 1);
        refCargo.addItem(new Item("chocolate",new BigDecimal(5),new BigDecimal(8),250,375), 1);
        refCargo.addItem(new Item("bread",new BigDecimal(2),new BigDecimal(3),125,200), 1);
        refCargo.addItem(new Item("mushrooms",new BigDecimal(2),new BigDecimal(4),200,325,10), 1);
        refCargo.addItem(new Item("tomatoes",new BigDecimal(1),new BigDecimal(2),325,400,10), 1);
        refCargo.addItem(new Item("lettuce",new BigDecimal(1),new BigDecimal(2),250,350,10), 1);
        refCargo.addItem(new Item("grapes",new BigDecimal(4),new BigDecimal(6),125,225,9), 1);
        refCargo.addItem(new Item("asparagus",new BigDecimal(2),new BigDecimal(4),175,275,8), 1);
        refCargo.addItem(new Item("celery",new BigDecimal(2),new BigDecimal(3),225,350,8), 1);
        refCargo.addItem(new Item("chicken",new BigDecimal(10),new BigDecimal(14),325,425,4), 1);
        refCargo.addItem(new Item("beef",new BigDecimal(12),new BigDecimal(17),425,550,3), 1);
        refCargo.addItem(new Item("fish",new BigDecimal(13),new BigDecimal(16),375,475,2), 1);
        refCargo.addItem(new Item("yoghurt",new BigDecimal(10),new BigDecimal(12),200,325,3), 1);
        refCargo.addItem(new Item("milk",new BigDecimal(2),new BigDecimal(3),300,425,3), 1);
        refCargo.addItem(new Item("cheese",new BigDecimal(4),new BigDecimal(7),375,450,3), 1);
        refCargo.addItem(new Item("ice cream",new BigDecimal(8),new BigDecimal(14),175,250,-20), 1);
        refCargo.addItem(new Item("ice",new BigDecimal(2),new BigDecimal(5),225,325,-10), 1);
        refCargo.addItem(new Item("frozen meat",new BigDecimal(10),new BigDecimal(14),450,575,-14), 1);
        refCargo.addItem(new Item("frozen vegetable mix",new BigDecimal(5),new BigDecimal(8),225,325,-12), 1);

        // test that all food was able to be entered into the refrigerated cargo
        assertEquals("Not all items were able to be stored into the refrigerated trucks cargo" ,refItemList, refCargo.getItemList());
    }
}
