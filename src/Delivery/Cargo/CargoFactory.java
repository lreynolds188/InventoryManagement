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
     * If boolean passed to getCargo method is TRUE
     * return an ordinary cargo object from the OrdinaryCargo Class
     * vis versa for a refrigerated cargo object.
     */
    public static Cargo getCargo(boolean ref_cargo){
        if(ref_cargo == true){
            return new RefrigeratedCargo();
        }
        else{
            return new OrdinaryCargo();
        }
    }
}
