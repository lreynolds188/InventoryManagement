package Delivery.Truck;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.StockException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import CSV.Utility;

/**
 * Ordinary truck class extending Truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class OrdinaryTruck extends Truck {

    private BigDecimal cost;
    private int capacity = 1000;
    private Cargo cargo;

    // Instantiate new cargo Factory object
    CargoFactory cargoFactory = new CargoFactory();

    /**
     * Class constructor that initializes class property fields
     */
    public OrdinaryTruck(){

        this.cargo = cargoFactory.getCargo(true);

        // Cost in dollars equal to 750 + 0.25q
        // where q is the total quantity of items in the cargo
        this.cost = new BigDecimal(750 + (0.25 * cargo.get_cargo().keySet().size()));
    }

    /**
     * Gets the trucks cargo capactity
     * @return integer representing cargo capacity
     */
    @Override
    public int get_capacity() {
        return capacity;
    }


    /**
     * Gets the total cost of the cargo in the truck
     * @return integer cost as dollars
     */
    @Override
    public BigDecimal get_cost() {
        return  cost.setScale(2, BigDecimal.ROUND_CEILING);
    }

    @Override
    public Cargo get_cargo(){
        return cargo;
    }

    @Override
    public void add_item(String item, Integer quantity){
        cargo.addItem(item, quantity);
    }
}
