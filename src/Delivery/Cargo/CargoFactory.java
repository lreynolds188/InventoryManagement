package Delivery.Cargo;

/**
 * Cargo Factory class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class CargoFactory {
    /**
     * If boolean passed to get_cargo method is TRUE
     * return an ordinary cargo object from the OrdinaryCargo Class
     * vis versa for a refrigerated cargo object.
     */
    public Cargo getCargo(boolean ord_cargo){
        if(ord_cargo == true){
            return new OrdinaryCargo();
        }
        else{
            return new RefrigeratedCargo();
        }
    }
}
