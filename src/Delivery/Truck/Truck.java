package Delivery.Truck;

import java.util.ArrayList;

/**
 * Abstract truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public abstract class Truck {

    public void load_cargo(){}

    public ArrayList<Object[]> get_cargo(){return null;}

    public abstract int get_capacity();

    public abstract int get_temperature();
}
