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
     * Returns a refrigerated cargo object if the boolean value parameter is True
     * Otherwise return an ordinary cargo object if boolean value is False
     */
    public static Cargo makeCargoObject(boolean ref_cargo){
        if(ref_cargo == true){
            return new RefrigeratedCargo();
        }
        else{
            return new OrdinaryCargo();
        }
    }
}
