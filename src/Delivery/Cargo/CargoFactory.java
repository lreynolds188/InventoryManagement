package Delivery.Cargo;

/**
 * Cargo Factory class
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
 * @version 1.0
 */

public class CargoFactory {

    /**
     * Returns a refrigerated cargo object if the boolean value parameter is True
     * Otherwise return an ordinary cargo object if boolean value is False
     * @param ref_cargo Cargo
     * @return Cargo
     */
    public static Cargo generateCargo(boolean ref_cargo){
        if(ref_cargo){
            return new RefrigeratedCargo();
        }
        else{
            return new OrdinaryCargo();
        }
    }
}
