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

    public void addItem(String item, Integer quantity){}

    public BigDecimal getCost(){return null;}

    public int get_capacity(){return -1;}

    public int getTemperature(){return 11;}
}
