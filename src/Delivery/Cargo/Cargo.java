package Delivery.Cargo;

import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Cargo class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
 public interface Cargo {
    /**
     * Method signature for getting the list of cargo items
     */
    HashMap<Item,Integer> getCargo();

    /**
     * Method signature for getting cost of cargo object
     */
    BigDecimal getCost();

    /**
     * Method signature for adding items to the cargo list
     */
    void addItem(Item name, Integer quantity);

    /**
     * Method signature for getting size of cargo
     */
    Integer getSize();
}

