package Tests.Stock;

import Stock.Stock;
import Stock.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Stock Tester.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 5, 2018</pre>
 * @version 1.0  HashMap<Object, Item> stock_items
 */
public class StockTest {

    Stock stock_object = new Stock();
    /**
     * Testing stock items collection is of type HashMap
     */
    @Test
    public void test_stock_collection_type(){

        assertThat(
                "This collection must be of type HashMap whose key is a String and value is an Object",
                stock_object.getStock(),
                instanceOf(HashMap.class));
    }

    /**
     * Test that Hashmap Value is of type Item and that the reference to the item is of type String
     */
    @Test
    public void test_hashmap_value_object() {

        Item item1 = new Item("car",new BigDecimal(100),new BigDecimal(200),1,5);
        stock_object.put(item1 , 5);

        // Test that hashmap value is of type Integer
        assertThat("Value object should be of type Integer ",stock_object.get(item1), instanceOf(Integer.class));

    }
}
