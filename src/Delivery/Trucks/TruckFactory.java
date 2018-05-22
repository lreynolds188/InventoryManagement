package Delivery.Trucks;

/**
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */
public class TruckFactory {
    public static Truck generateTruck(boolean refTruck){
        if(refTruck){
            return new Refrigerated_Truck();
        }
        else{
            return new Ordinary_Truck();
        }
    }
}
