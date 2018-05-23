package Delivery.Trucks;

/**
 *
 * Truck factory class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class TruckFactory {
    /**
     * Returns a refrigerated truck object if the boolean value parameter is True
     * Otherwise return an ordinary truck object if boolean value is False
     */
    public static Truck generateTruck(boolean refTruck){
        if(refTruck){
            return new RefrigeratedTruck();
        }
        else{
            return new OrdinaryTruck();
        }
    }
}
