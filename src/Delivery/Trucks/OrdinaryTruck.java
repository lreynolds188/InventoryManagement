package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.Item;

import java.math.BigDecimal;
/**
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class OrdinaryTruck implements Truck {

    private BigDecimal cost;
    private int capacity = 1000;
    private Cargo cargo;

    public OrdinaryTruck(){
        this.cargo = CargoFactory.getCargo(false);
    }

    @Override
    public int getCapacity(){
        return capacity;
    }

    @Override
    public BigDecimal getCost(){
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    @Override
    public Cargo getCargo(){
        return cargo;
    }

    @Override
    public void addItem(Item item, Integer quantity){
        cargo.addItem(item, quantity);
        cost = new BigDecimal(750 + (0.25 * cargo.getSize()));
    }

}
