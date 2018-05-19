package Delivery.Cargo;

import java.util.HashMap;

/**
 * Refrigerated cargo class implementing Cargo interface.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public class RefrigeratedCargo implements Cargo{

    // Instance of hash map data structure for storing cargo items
    HashMap<String, Integer> refrigerated_cargo = new HashMap<>();

    /**
     * Gets the data structure object holding all the refrigerated cargo items
     * @return data structure
     */
    @Override
    public HashMap<String, Integer> get_cargo() {
        return refrigerated_cargo;
    }

    /**
     * Adds a new refrigerated cargo item to the refrigerated cargo data structure
     * @param name - key
     * @param quantity - value
     */
    @Override
    public void addItem(String name, Integer quantity) {
        refrigerated_cargo.put(name,quantity);
    }

}
