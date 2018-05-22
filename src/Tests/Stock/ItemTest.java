package Tests.Stock;

import Stock.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Item Tester.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 5, 2018</pre>
 * @version 1.0
 */
public class ItemTest {

    Item item = new Item("rice",new BigDecimal(2),new BigDecimal(3),225,300);

    /**
     * Tests if item is of class Item
     */
    @Test
    public void test_item_obj_type(){ assertThat(item, instanceOf(Item.class)); }

    /**
     * Series of tests for Item name property
     */
    @Test
    public void test_item_name_property(){

        // Test that name is of type String and not Null
        assertThat(
                "name is required and cannot be null.",
                item.getName(),
                instanceOf(String.class));

        // Test that name is not empty
        assert (item.getName() != "") : "name can not be an empty string";

    }

    /**
     * Series of tests for Manufacturing cost in dollars
     */
    @Test
    public void test_Item_manufacturing_cost() {

        BigDecimal zero = new BigDecimal(0.0);

        // Test that manufacturing cost is not Null
        assertThat(
                "Manufacturing cost must be of type Big Decimal.",
                item.getManufacturingCost(),
                instanceOf(BigDecimal.class));

        // Test that manufacturing cost is value is greater than zero
        assert (item.getManufacturingCost().compareTo(zero) > 0) : "Manufacturing cost must be greater than Zero.";

    }

    /**
     * Series of tests for Manufacturing cost in dollars
     */
    @Test
    public void test_Item_sell_price() {

        BigDecimal zero = new BigDecimal(0.0);

        // Test that sell price is of type Big Decimal
        assertThat(
                "Sell price must be of type Big Decimal",
                item.getSellPrice(),
                instanceOf(BigDecimal.class));

        // Test that sell price is greater than zero
        assert (item.getSellPrice().compareTo(zero) > 0);

        // Test that sell price is of type Big Decimal - Appropriate for currency values
        assertThat(item.getSellPrice(), instanceOf(BigDecimal.class));

        // Test that sell price is greater than manufacturing cost
        assert (item.getSellPrice().compareTo(item.getManufacturingCost()) > 0);
    }

    /**
     * Testing reorder point property
     */
    @Test
    public void test_reorder_point() {

        // Test that reorder point property is of type int
        assertThat(
                "reorder point must be of type int.",
                item.getReorderPoint(),
                instanceOf(Integer.class));

        // Test that reorder point is greater than or equal to zero
        assert (item.getReorderPoint() >= 0);

    }

    /**
     * Testing reorder point property
     */
    @Test
    public void test_reorder_amount() {

        // Test that amount property is of type int
        assertThat(
                "amount must be of type int.",
                item.getReorderAmount(),
                instanceOf(Integer.class));

        // Test that amount point is greater than or equal to zero
        assert (item.getReorderAmount() >= 0);
    }

    /**
     * Testing temperature value
     */
    @Test
    public void test_temperature_value() {

        // Test that temperature variable is of type Integer
        assertThat(
                "temperature must be of type int.",
                item.getTemperature(),
                instanceOf(Integer.class));

    }

}
