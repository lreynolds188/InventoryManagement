package Delivery.Cargo;

import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Ordinary cargo class implementing Cargo interface.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public class RefrigeratedCargo implements Cargo{

    // Instance of hash map data structure for storing cargo items
    HashMap<Item, Integer> refrigerated_cargo = new HashMap<>();

    /**
     * Gets the data structure object holding all the refrigerated cargo items
     * @return data structure
     */
    @Override
    public HashMap<Item, Integer> get_cargo() {
        return refrigerated_cargo;
    }

    @Override
    public BigDecimal getCost(){
        BigDecimal cost = new BigDecimal(0);
        for (Map.Entry<Item, Integer> set : refrigerated_cargo.entrySet()){
            BigDecimal temp = set.getKey().getManufacturing_cost().multiply(new BigDecimal(set.getValue()));
            cost = cost.add(temp);
        }
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * Adds a new refrigerated cargo item to the refrigerated cargo data structure
     * @param item - key
     * @param quantity - value
     */
    @Override
    public void addItem(Item item, Integer quantity) {
        refrigerated_cargo.put(item,quantity);
    }

    @Override
    public Integer get_size() {
        int size = 0;
        for (Map.Entry<Item, Integer> temp: refrigerated_cargo.entrySet()){
            size += temp.getValue();
        }
        return size;
    }

}
