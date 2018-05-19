package Delivery.Cargo;

import CSV.Utility;
import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Item Tester.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public interface Cargo {
    /**
     * Method signature for getting the list of cargo items
      * @return
     */
    HashMap<Item,Integer> get_cargo();

    BigDecimal getCost();

    /**
     * Method signature for adding items to the cargo list
     * @return
     */
    void addItem(Item name, Integer quantity);

}
