import org.junit.*;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class Tests {

    Truck ordinaryTruck, refrigeratedTruck;
    Cargo ordCargo, refCargo;

    /**
     * Tests the existence and the ability to create an instance of required objects and their functions.
     */
    @Before@Test
    public void testInit (){
        testTruckInit();
        testCargoInit();
    }

    @Test
    public void testTruckInit(){
        ordinaryTruck = new OrdinaryTruck();
        refrigeratedTruck = new RefrigeratedTruck();
    }

    @Test
    public void testCargoInit(){
        ordCargo = new Cargo(false);
        refCargo = new Cargo(true);
    }

    @Test
    public void testTruckCost(){
        assertEquals(BigDecimal.valueOf(750.50).setScale(2, BigDecimal.ROUND_CEILING) ,ordinaryTruck.getCost());
        assertEquals(BigDecimal.valueOf(1732.99).setScale(2, BigDecimal.ROUND_CEILING) ,refrigeratedTruck.getCost());
    }

    @Test
    public void testTruckCapacity(){
        assertEquals(1000, ordinaryTruck.getCapacity());
        assertEquals(800, refrigeratedTruck.getCapacity());
    }

    @Test
    public void testCargo()
    {
        // test ordinary truck cargo
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
        ordCargo.addItem(new Item("rice",2,3,225,300), 1);
        ordCargo.addItem(new Item("beans",4,6,450,525), 1);
        ordCargo.addItem(new Item("pasta",3,4,125,250), 1);
        ordCargo.addItem(new Item("biscuits",2,5,450,575), 1);
        ordCargo.addItem(new Item("nuts",5,9,125,250), 1);
        ordCargo.addItem(new Item("chips",2,4,125,200), 1);
        ordCargo.addItem(new Item("chocolate",5,8,250,375), 1);
        ordCargo.addItem(new Item("bread",2,3,125,200), 1);
        ordCargo.addItem(new Item("mushrooms",2,4,200,325,10), 1);
        ordCargo.addItem(new Item("tomatoes",1,2,325,400,10), 1);
        ordCargo.addItem(new Item("lettuce",1,2,250,350,10), 1);
        ordCargo.addItem(new Item("grapes",4,6,125,225,9), 1);
        ordCargo.addItem(new Item("asparagus",2,4,175,275,8), 1);
        ordCargo.addItem(new Item("celery",2,3,225,350,8), 1);
        ordCargo.addItem(new Item("chicken",10,14,325,425,4), 1);
        ordCargo.addItem(new Item("beef",12,17,425,550,3), 1);
        ordCargo.addItem(new Item("fish",13,16,375,475,2), 1);
        ordCargo.addItem(new Item("yoghurt",10,12,200,325,3), 1);
        ordCargo.addItem(new Item("milk",2,3,300,425,3), 1);
        ordCargo.addItem(new Item("cheese",4,7,375,450,3), 1);
        ordCargo.addItem(new Item("ice cream",8,14,175,250,-20), 1);
        ordCargo.addItem(new Item("ice",2,5,225,325,-10), 1);
        ordCargo.addItem(new Item("frozen meat",10,14,450,575,-14), 1);
        ordCargo.addItem(new Item("frozen vegetable mix",5,8,225,325,-12), 1);
        assertEquals(ordItemList, ordCargo.getItemList());

        // test refrigerated truck cargo
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
        refCargo.addItem(new Item("rice",2,3,225,300), 1);
        refCargo.addItem(new Item("beans",4,6,450,525), 1);
        refCargo.addItem(new Item("pasta",3,4,125,250), 1);
        refCargo.addItem(new Item("biscuits",2,5,450,575), 1);
        refCargo.addItem(new Item("nuts",5,9,125,250), 1);
        refCargo.addItem(new Item("chips",2,4,125,200), 1);
        refCargo.addItem(new Item("chocolate",5,8,250,375), 1);
        refCargo.addItem(new Item("bread",2,3,125,200), 1);
        refCargo.addItem(new Item("mushrooms",2,4,200,325,10), 1);
        refCargo.addItem(new Item("tomatoes",1,2,325,400,10), 1);
        refCargo.addItem(new Item("lettuce",1,2,250,350,10), 1);
        refCargo.addItem(new Item("grapes",4,6,125,225,9), 1);
        refCargo.addItem(new Item("asparagus",2,4,175,275,8), 1);
        refCargo.addItem(new Item("celery",2,3,225,350,8), 1);
        refCargo.addItem(new Item("chicken",10,14,325,425,4), 1);
        refCargo.addItem(new Item("beef",12,17,425,550,3), 1);
        refCargo.addItem(new Item("fish",13,16,375,475,2), 1);
        refCargo.addItem(new Item("yoghurt",10,12,200,325,3), 1);
        refCargo.addItem(new Item("milk",2,3,300,425,3), 1);
        refCargo.addItem(new Item("cheese",4,7,375,450,3), 1);
        refCargo.addItem(new Item("ice cream",8,14,175,250,-20), 1);
        refCargo.addItem(new Item("ice",2,5,225,325,-10), 1);
        refCargo.addItem(new Item("frozen meat",10,14,450,575,-14), 1);
        refCargo.addItem(new Item("frozen vegetable mix",5,8,225,325,-12), 1);
        assertEquals(refItemList, refCargo.getItemList());
    }

    @Test
    public void testTruckTemp(){
        assertEquals(-20 ,refrigeratedTruck.getTemperature());
    }
}
