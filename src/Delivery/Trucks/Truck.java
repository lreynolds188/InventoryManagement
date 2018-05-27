package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Stock.Item;

import java.math.BigDecimal;

/**
 * Truck interface that defines a set of methods used by Ordinary and Refrigerated
 * sub classes
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
 * @version 1.0
 */
public interface Truck {

    /**
     * Method signature for getting the list of cargo items
     * @return Cargo
     */
    Cargo getCargo();

    /**
     * Method signature for adding items to truck cargo
     * @param item Item
     * @param quantity Integer
     */
    void addItem(Item item, Integer quantity);

    /**
     * Method signature for getting the cost of the truck
     * @return BigDecimal
     */
    BigDecimal getCost();

    /**
     * Method signature for getting the trucks capacity
     * @return int
     */
    int getCapacity();

}

