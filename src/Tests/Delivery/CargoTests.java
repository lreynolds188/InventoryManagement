package Tests.Delivery;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Delivery.Cargo.OrdinaryCargo;
import Delivery.Cargo.RefrigeratedCargo;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class CargoTests {

    Cargo ordCargo, refCargo;

    //
    @Before@Test
    public void test1InitOrdCargo (){
        // Tests Cargo class creation passing it a boolean variable relative to whether the Cargo is refrigerated or not
        ordCargo = CargoFactory.generateCargo(false);
    }

    //
    @Before@Test
    public void test2InitRefCargo (){
        // Tests Cargo class creation passing it a boolean variable relative to whether the Cargo is refrigerated or not
        refCargo = CargoFactory.generateCargo(true);
    }

    //
    @Test
    public void testOrdCargoClassType(){
        assertThat(ordCargo, instanceOf(OrdinaryCargo.class));
    }

    //
    @Test
    public void testRefCargoClassType(){
        assertThat(refCargo, instanceOf(RefrigeratedCargo.class));
    }

    //
    @Test
    public void testOrdCargoType(){
        assertThat(ordCargo.getCargo(), instanceOf(HashMap.class));
    }

    //
    @Test
    public void testRefCargoType(){
        assertThat(refCargo.getCargo(), instanceOf(HashMap.class));
    }

    //
    @Test
    public void testOrdCargoCost(){
        assertThat(ordCargo.getCost(), instanceOf(BigDecimal.class));
    }

    //
    @Test
    public void testRefCargoCost(){
        assertThat(refCargo.getCost(), instanceOf(BigDecimal.class));
    }

    //
    @Test
    public void testOrdCargoSize(){
        assertThat(ordCargo.getSize(), instanceOf(Integer.class));
    }

    //
    @Test
    public void testRefCargoSize(){
        assertThat(ordCargo.getSize(), instanceOf(Integer.class));
    }



}
