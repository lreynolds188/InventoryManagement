package Tests.Stock;

import Stock.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Item Tester.
 *
 * @author Jonathan Gonzalez
 * @since May 20, 2018
 * @version 1.0
 */
public class ItemTest {

    Item item = new Item("rice",new BigDecimal(2),new BigDecimal(3),225,300);

    /**
     * Tests if item is of class Item
     */
    @Test
    public void testItemObjType(){ assertThat(item, instanceOf(Item.class)); }

    /**
     * Series of tests for Item name property
     */
    @Test
    public void testItemNameProperty(){

        // Test that name is of type String and not Null
        assertThat(
                "name is required and cannot be null.",
                item.getName(),
                instanceOf(String.class));

    }

    /**
     * Test Item property is not empty
     */
    @Test
    public void testItemIsNotEmpty() {
        // Test that name is not empty
        assert (item.getName() != "") : "name can not be an empty string";
    }

    /**
     * Test manufacturing cost is instance of Big Decimal
     */
    @Test
    public void testItemManufacturingCost() {

        BigDecimal zero = new BigDecimal(0.0);

        // Test that manufacturing cost is not Null
        assertThat(
                "Manufacturing cost must be of type Big Decimal.",
                item.getManufacturingCost(),
                instanceOf(BigDecimal.class));
    }

    /**
     * Test manufacturing cost is greater than zero
     */
    @Test
    public void testManufacturingCostGreaterThanZero() {
        BigDecimal zero = new BigDecimal(0.0);

        // Test that manufacturing cost value is greater than zero
        assert (item.getManufacturingCost().compareTo(zero) > 0) : "Manufacturing cost must be greater than Zero.";

    }


    /**
     * Test that sell price is greater than manufacturing cost
     */
    @Test
    public void testSellPriceGreaterThanManufacturingCost() {
        // Test that sell price is greater than manufacturing cost
        assert (item.getSellPrice().compareTo(item.getManufacturingCost()) > 0);
    }

    /**
     * Test sell prices is greater than zero
     */
    @Test
    public void testSellPriceGreaterThanZero() {
        BigDecimal zero = new BigDecimal(0.0);

        // Test that sell price is greater than zero
        assert (item.getSellPrice().compareTo(zero) > 0);
    }

    /**
     * Test sell prices is of of type Big Decimal
     */
    @Test
    public void testSellPriceType() {
        // Test that sell price is of type Big Decimal - Appropriate for currency values
        assertThat(item.getSellPrice(), instanceOf(BigDecimal.class));
    }

    /**
     * Testing reorder point type
     */
    @Test
    public void testReorderPoint() {

        // Test that reorder point property is of type int
        assertThat(
                "reorder point must be of type int.",
                item.getReorderPoint(),
                instanceOf(Integer.class));

    }

    /**
     * Test reorder point is greater than zero
     */
    @Test
    public void testReorderPointGreaterThanZero() {
        // Test that reorder point is greater than or equal to zero
        assert (item.getReorderPoint() >= 0);
    }

    /**
     * Testing reorder point property
     */
    @Test
    public void testReorderAmount() {

        // Test that amount property is of type int
        assertThat(
                "amount must be of type int.",
                item.getReorderAmount(),
                instanceOf(Integer.class));
    }

    /**
     * Test reorder amount is greater than zero
     */
    @Test
    public void testReorderAmountGreaterThanZero() {
        // Test that amount point is greater than or equal to zero
        assert (item.getReorderAmount() >= 0);

    }

    /**
     * Testing temperature value
     */
    @Test
    public void testTemperatureValue() {

        // Test that temperature variable is of type Integer
        assertThat(
                "temperature must be of type int.",
                item.getTemperature(),
                instanceOf(Integer.class));

    }

}
