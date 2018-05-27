package Tests.Stock;

import Stock.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Store Tester.
 *
 * @author Jonathan Gonzalez
 * @since May 20, 2018
 * @version 1.0
 */
public class StoreTest {

    Store storeSingleton = Store.getInstance();

    /**
     * Tests for Store capital property
     */
    @Test
    public void testStoreCapitalProperty(){

        BigDecimal oneHundredThousand = new BigDecimal(100000.00);

        BigDecimal capital = storeSingleton.getCapital();

        // Asserts that the singleton class property capital is of type Big Decimal
        assertThat(storeSingleton.getCapital(), instanceOf(BigDecimal.class));

        // Assert that the capital value is formatted to 2 decimal points
        assert (storeSingleton.getCapital().scale() ==  2) : "Capital value should round to 2 deciaml places and " +
                "that RoundingMode is set to CEILING";

        // Assert that the starting capital for a store is equal to 100,000.00
        assert (storeSingleton.getCapital().compareTo(oneHundredThousand) == 0) : "Value should be exactly 100000.00";
    }

    /**
     * Test Store name property
     */
    @Test
    public void testStoreNameProperty() {
        // Assert that the singleton class property name is of type String
        assertThat(storeSingleton.getName(), instanceOf(String.class));
    }

    /**
     * Test Store inventory(stock) property
     */
    @Test
    public void testStoreInventory() {

        // Assert that singleton stock is of type Stock
        assertThat(storeSingleton.getInventory(), instanceOf(Stock.class));
    }
}
