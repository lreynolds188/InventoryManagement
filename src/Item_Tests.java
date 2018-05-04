
import Stock.Item;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.junit.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class Item_Tests {

    private Item item = new Item();


    /**
     * Tests the existence and the ability to create an instance of required objects and their functions.
     */
    @Before@Test
    public void test_init (){
        test_item_init();
    }

    @Test
    public void test_item_init(){
        item = new Item();
    }

    //Tests if item is of class Item
    @Test
    public void test_item_obj_type(){
        assertThat(item, instanceOf(Item.class));
    }

    // Series of tests for Item name property
    @Test
    public void test_item_name_property(){

        assertThat(item, instanceOf(Item.class));
        assert (item.name != "");
        assertThat(item.name, instanceOf(String.class));
    }

    // Series of tests for Manufacturing cost in dollars
    @Test
    public void test_Item_manufacturing_cost() {
        item.manufacturing_cost = new BigDecimal(.11).setScale(2, BigDecimal.ROUND_FLOOR);
        BigDecimal zero = new BigDecimal(0.0);
        System.out.print("scale " + item.manufacturing_cost.scale() + "Item manufacturing cost: " + item.manufacturing_cost);

        assert (item.manufacturing_cost.compareTo(zero) > 0);
        assert (item.manufacturing_cost.scale() == 2 );
        assertThat(item.manufacturing_cost, instanceOf(BigDecimal.class));
    }
}
