package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Stock.Item;

import java.math.BigDecimal;

/**
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public interface Truck {
    Cargo getCargo();

     void addItem(Item item, Integer quantity);
     BigDecimal getCost();
     int getCapacity();

}

