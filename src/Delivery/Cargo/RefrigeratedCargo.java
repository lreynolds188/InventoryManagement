package Delivery.Cargo;

import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Refrigerated cargo class implementing Cargo interface.
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
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
    public HashMap<Item, Integer> getCargo() {
        return refrigerated_cargo;
    }

    /**
     * Getter for cost of refrigerated cargo
     * @return the total cost of the refrigerated cargo
     */
    @Override
    public BigDecimal getCost(){

        BigDecimal cost = new BigDecimal(0);

        // for each item in ordinary cargo calculate the cost using temperature variable
        for (Map.Entry<Item, Integer> set : refrigerated_cargo.entrySet()){
            BigDecimal temp = set.getKey().getManufacturingCost().multiply(new BigDecimal(set.getValue()));
            cost = cost.add(temp);
        }

        // set scale and round down and return the cost
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

    /**
     * Getter for cargo size
     * @return
     */
    @Override
    public Integer getSize() {
        int size = 0;
        for (Map.Entry<Item, Integer> temp: refrigerated_cargo.entrySet()){
            size += temp.getValue();
        }
        return size;
    }

}
