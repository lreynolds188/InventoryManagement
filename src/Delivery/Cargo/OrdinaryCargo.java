package Delivery.Cargo;

import java.util.HashMap;

/**
 * Ordinary cargo class implementing Cargo interface.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public class OrdinaryCargo implements Cargo{

    // Instance of hash map data structure for storing cargo items
    HashMap<String, Integer> ord_cargo = new HashMap<>();

    /**
     * Gets the data structure object holding all the ordinary cargo items
     * @return data structure
     */
    @Override
    public HashMap<String, Integer> getItemList() {
        return ord_cargo;
    }

    /**
     * Adds a new ordinary cargo item to the ordinary cargo data structure
     * @param name - key
     * @param quantity - value
     */
    @Override
    public void addItem(String name, Integer quantity) {
        ord_cargo.put(name, quantity);
    }
}
