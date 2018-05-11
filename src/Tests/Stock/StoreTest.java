package Tests.Stock;

import Stock.Stock;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Store Tester.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 7, 2018</pre>
 * @version 1.0
 */
public class StoreTest {

    Stock.Store store_singleton = Stock.Store.getInstance();

    /**
     * Tests for Store capital property
     */
    @Test
    public void test_store_capital_property(){

        BigDecimal one_hundred_thousand = new BigDecimal(100000.00);

        BigDecimal capital = store_singleton.getCapital();

        // Asserts that the singleton class property capital is of type Big Decimal
        assertThat(store_singleton.getCapital(), instanceOf(BigDecimal.class));

        // Assert that the capital value is formatted to 2 decimal points
        assert (store_singleton.getCapital().scale() ==  2) : "Capital value should round to 2 deciaml places and " +
                "that RoundingMode is set to CEILING";

        // Assert that the starting capital for a store is equal to 100,000.00
        assert (store_singleton.getCapital().compareTo(one_hundred_thousand) == 0) : "Value should be exactly 100000.00";
    }

    /**
     * Test Store name property
     */
    @Test
    public void test_store_name_property() {
        // Assert that the singleton class property name is of type String
        assertThat(store_singleton.getName(), instanceOf(String.class));
    }

    /**
     * Test Store inventory(stock) property
     */
    @Test
    public void test_store_inventory() {

        // Assert that singleton stock is of type Stock
        assertThat(store_singleton.getInventory(), instanceOf(Stock.Stock.class));
    }
}
