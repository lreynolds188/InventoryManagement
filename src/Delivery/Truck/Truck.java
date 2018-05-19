package Delivery.Truck;

import Delivery.Cargo.*;

import java.math.BigDecimal;

/**
 * Abstract truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public abstract class Truck {

    public Cargo get_cargo(){return null;}

    public void add_item(String item, Integer quantity){}

    public BigDecimal get_cost(){return null;}

    public int get_capacity(){return -1;}

    public int get_temperature(){return 11;}
}
