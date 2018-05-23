package Tests.Delivery;

import Stock.Manifest;
import Delivery.Trucks.RefrigeratedTruck;
import Delivery.Trucks.OrdinaryTruck;
import Delivery.Trucks.Truck;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Manifest Tester.
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 7, 2018</pre>
 * @version 1.0
 */
public class ManifestTest {

    Manifest manifestObject;

    /**
     * Testing manifest collection type is of hashmap
     */
    @Test
    public void testManifestCollectionType(){

        assertThat(
                "This collection must be of type HashMap whose key is a String and value is an instance of TruckAbst",
                manifestObject,
                instanceOf(HashMap.class));
    }

    /**
     * Test that manifest takes objects of type TruckAbst
     */
    @Test
    public void testManifestValueType() {

        Truck refrigeratedTruck = new RefrigeratedTruck();
        Truck ordinaryTruck = new OrdinaryTruck();

        manifestObject.put("refrigerated1", refrigeratedTruck);
        manifestObject.put("ordinary1", ordinaryTruck);


        // Test that manifest value for refrigerated truck is of type TruckAbst
        assertThat(
                "Value object should be of type Item ",
                manifestObject.get("refrigerated1"),
                instanceOf(Truck.class));

        // Test that manifest value for ordinary truck is of type TruckAbst
        assertThat(
                "Value object should be of type Item ",
                manifestObject.get("ordinary1"),
                instanceOf(Truck.class));
    }

} 
