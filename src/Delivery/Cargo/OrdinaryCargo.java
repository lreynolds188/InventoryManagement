package Delivery.Cargo;

import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Ordinary cargo class implementing Cargo interface.
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
 * @version 1.0
 */
public class OrdinaryCargo implements Cargo{

    // Instance of hash map data structure for storing cargo items
    HashMap<Item, Integer> ord_cargo = new HashMap<>();

    /**
     * Getter for ordinary cargo
     * @return HashMap data structure of ordinary cargo items
     */
    @Override
    public HashMap<Item, Integer> getCargo() {
        return ord_cargo;
    }

    /**
     * Getter for cost of ordinary cargo
     * @return the total cost of the ordinary cargo
     */
    @Override
    public BigDecimal getCost(){

        BigDecimal cost = new BigDecimal(0);

        // for each item in ordinary cargo get the cost and add it to cost variable
        for (Map.Entry<Item, Integer> set : ord_cargo.entrySet()){
            BigDecimal temp = set.getKey().getManufacturingCost().multiply(new BigDecimal(set.getValue()));
            cost = cost.add(temp);
        }
        // return final cost
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * Adds a new ordinary cargo item to the ordinary cargo data structure
     * @param item - key
     * @param quantity - value
     */
    @Override
    public void addItem(Item item, Integer quantity) {
        ord_cargo.put(item, quantity);
    }

    /**
     * Getter for cargo size
     * @return
     */
    @Override
    public Integer getSize() {
        int size = 0;

        // for each entry of ordinary cargo get the size and return value
        for (Map.Entry<Item, Integer> temp: ord_cargo.entrySet()){
            size += temp.getValue();
        }
        return size;
    }
}
