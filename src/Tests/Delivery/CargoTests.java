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

    // Tests the existence and ability to create an instance of required objects and their functions.
    @Before@Test
    public void test1InitOrdCargo (){
        ordCargo = CargoFactory.generateCargo(false);
    }

    // Tests the existence and ability to create an instance of required objects and their functions.
    @Before@Test
    public void test2InitRefCargo (){
        refCargo = CargoFactory.generateCargo(true);
    }

    // Tests the ordinary cargo's class type
    @Test
    public void testOrdCargoClassType(){
        assertThat(ordCargo, instanceOf(OrdinaryCargo.class));
    }

    // Tests the refrigerated cargo's class type
    @Test
    public void testRefCargoClassType(){
        assertThat(refCargo, instanceOf(RefrigeratedCargo.class));
    }

    // Tests the ordinary cargo's cargo type
    @Test
    public void testOrdCargoType(){
        assertThat(ordCargo.getCargo(), instanceOf(HashMap.class));
    }

    // Tests the refrigerated cargo's cargo type
    @Test
    public void testRefCargoType(){
        assertThat(refCargo.getCargo(), instanceOf(HashMap.class));
    }

    // Tests the ordinary cargo's cost type
    @Test
    public void testOrdCargoCost(){
        assertThat(ordCargo.getCost(), instanceOf(BigDecimal.class));
    }

    // Tests the refrigerated cargo's cost type
    @Test
    public void testRefCargoCost(){
        assertThat(refCargo.getCost(), instanceOf(BigDecimal.class));
    }

    // Tests the ordinary cargo's size
    @Test
    public void testOrdCargoSize(){
        assertThat(ordCargo.getSize(), instanceOf(Integer.class));
    }

    // Tests the refrigerated cargo's size
    @Test
    public void testRefCargoSize(){
        assertThat(ordCargo.getSize(), instanceOf(Integer.class));
    }

}
