package Delivery.Cargo;

import java.util.HashMap;

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
    HashMap<String,Integer> getItemList();

    /**
     * Method signature for adding items to the cargo list
     * @return
     */
    void addItem(String name, Integer quantity);

}
