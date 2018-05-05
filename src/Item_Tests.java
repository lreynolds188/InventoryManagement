
import Stock.Item;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.junit.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class Item_Tests {

    private Item item = new Item("rice",new BigDecimal(2),new BigDecimal(3),225,300);

    //Tests if item is of class Item
    @Test
    public void test_item_obj_type(){ assertThat(item, instanceOf(Item.class)); }

    // Series of tests for Item name property
    @Test
    public void test_item_name_property(){

        // Test that name is of type String and not Null
        assertThat(
                "name is required and cannot be null.",
                item.name, instanceOf(String.class));

        // Test that name is not empty
        assert (item.name != "") : "name can not be an empty string";

    }

    // Series of tests for Manufacturing cost in dollars
    @Test
    public void test_Item_manufacturing_cost() {

        BigDecimal zero = new BigDecimal(0.0);

        // Test that manufacturing cost is not Null
        assertThat(
                "Manufacturing cost must be of type Big Decimal.",
                item.manufacturing_cost, instanceOf(BigDecimal.class));

        // Test that manufacturing cost scale is set to 2 values after decimal point
        assert (item.manufacturing_cost.scale() == 2 ) : "Big Decimal scale must be set to 2 with Rounding to Floor";

        // Test that manufacturing cost is value is greater than zero
        assert (item.manufacturing_cost.compareTo(zero) > 0) : "Manufacturing cost must be greater than Zero.";

    }

    // Testing that Sell price is in dollars format
    @Test
    public void test_Item_sell_price() {

        BigDecimal zero = new BigDecimal(0.0);

        // Test that sell price is not Null
        assertThat(
                "Sell price must be of type Big Decimal",
                item.sell_price, instanceOf(BigDecimal.class));

        // Test that sell price is greater than zero
        assert (item.sell_price.compareTo(zero) > 0);

        // Test that sell price value is formatted to 2 decimal points
        assert (item.sell_price.scale() ==  2);

        // Test that sell price is of type Big Decimal - Appropriate for currency values
        assertThat(item.sell_price, instanceOf(BigDecimal.class));

        // Test that sell price is greater than manufacturing cost
        assert (item.sell_price.compareTo(item.manufacturing_cost) > 0);
    }

    // Testing reorder point property
    @Test
    public void test_reorder_point() {

        // Test that reorder point property is of type int
        assertThat(
                "reorder point must be of type int.",
                item.reorder_point, instanceOf(Integer.class));

        // Test that reorder point is greater than or equal to zero
        assert (item.reorder_point >= 0);

    }

    @Test
    public void test_reorder_amount() {
        // Test that amount property is of type int
        assertThat(
                "amount must be of type int.",
                item.reorder_amount, instanceOf(Integer.class));

        // Test that amount point is greater than or equal to zero
        assert (item.reorder_amount >= 0);
    }

    // Testing temperature value
    @Test
    public void test_temperature_value() {

        // Test that temperature variable is of type Integer
        assertThat(
                "temperature must be of type int.",
                item.temperature, instanceOf(Integer.class));

    }

}
