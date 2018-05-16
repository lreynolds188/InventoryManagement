package Delivery.Truck;

import java.math.BigDecimal;
import java.util.*;

/**
 * Abstract truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public abstract class Truck {

    public void load_cargo(){}

    public abstract HashMap<String, Integer> get_cargo();

    public abstract int get_capacity();

    public abstract int get_temperature();

    public abstract BigDecimal getCost();

    public abstract void addItem(String s, int i);
}
