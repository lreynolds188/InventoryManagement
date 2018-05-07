package test.Stock; 

import Stock.Item;
import Stock.Stock;
import com.sun.tools.javac.jvm.Items;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * Testing stock items collection is of type HashMap
     */
    @Test
    public void test_stock_collection_type(){

        assertThat(
                "This collection must be of type HashMap whose key is a String and value is an Object",
                stock_object,
                instanceOf(HashMap.class));
    }

    /**
     * Test that Hashmap Value is of type Item and that the reference to the item is of type String
     */
    @Test
    public void test_hashmap_value_object() {

        Item item1 = new Item("car", 3);
        String key1 = item1.name;
        stock_object.put(key1 , item1);

        // Test that hashmap value is of type item
        assertThat("Value object should be of type Item ",stock_object.get(key1), instanceOf(Item.class));

        // Test that hashmap key is of type String
        assertThat("Key object should be of type String" , stock_object.get(key1).name, instanceOf(String.class));

    }
}
