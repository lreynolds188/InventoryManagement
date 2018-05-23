package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Stock.Item;

import java.math.BigDecimal;

/**
 * Truck interface that defines a set of methods used by Ordinary and Refrigerated
 * sub classes
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public interface Truck {

    /**
     * Method signature for getting the list of cargo items
     */
    Cargo getCargo();

    /**
     * Method signature for adding items to truck cargo
     */
    void addItem(Item item, Integer quantity);

    /**
     * Method signature for getting the cost of the truck
     */
    BigDecimal getCost();

    /**
     * Method signature for getting the trucks capacity
     */
    int getCapacity();

}

