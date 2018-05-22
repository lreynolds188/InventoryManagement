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

    Manifest manifest_object;

    /**
     * Testing manifest collection type is of hashmap
     */
    @Test
    public void test_manifest_collection_type(){

        assertThat(
                "This collection must be of type HashMap whose key is a String and value is an instance of TruckAbst",
                manifest_object,
                instanceOf(HashMap.class));
    }

    /**
     * Test that manifest takes objects of type TruckAbst
     */
    @Test
    public void test_manifest_value_type() {

        Truck refrigerated_truck = new RefrigeratedTruck();
        Truck ordinary_truck = new OrdinaryTruck();

        manifest_object.put("refrigerated1", refrigerated_truck);
        manifest_object.put("ordinary1", ordinary_truck);


        // Test that manifest value for refrigerated truck is of type TruckAbst
        assertThat(
                "Value object should be of type Item ",
                manifest_object.get("refrigerated1"),
                instanceOf(Truck.class));

        // Test that manifest value for ordinary truck is of type TruckAbst
        assertThat(
                "Value object should be of type Item ",
                manifest_object.get("ordinary1"),
                instanceOf(Truck.class));
    }

} 
