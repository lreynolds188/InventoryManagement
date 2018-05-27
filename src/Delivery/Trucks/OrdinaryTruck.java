package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.Item;

import java.math.BigDecimal;
/**
 * Ordinary Truck Class implements Truck interface
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
 * @version 1.0
 */

public class OrdinaryTruck implements Truck {

    private BigDecimal cost;

    private int capacity = 1000;

    private Cargo cargo;

    /**
     * Constructor of ordinary truck class
     * sets the cargo object to a new ordinary cargo object using cargo factory
     */
    public OrdinaryTruck(){
        this.cargo = CargoFactory.generateCargo(false);
    }

    /**
     * Getter for ordinary truck capacity
     * @return ordinary truck capacity as an integer
     */
    @Override
    public int getCapacity(){
        return capacity;
    }

    /**
     * Getter for cost of ordinary truck
     * @return get cost of truck as a big decimal rounded down
     */
    @Override
    public BigDecimal getCost(){
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * Getter for ordinary truck cargo
     * @return get the cargo object for an ordinary truck
     */
    @Override
    public Cargo getCargo(){
        return cargo;
    }

    /**
     * This method adds an item object to the cargo hashmap. Pass the key value pairs
     * as parameters
     * @param item object
     * @param quantity of items to add
     */
    @Override
    public void addItem(Item item, Integer quantity){

        cargo.addItem(item, quantity);
        // calculate the cost of an item added to the ordinary truck
        cost = new BigDecimal(750 + (0.25 * cargo.getSize()));
    }

}








