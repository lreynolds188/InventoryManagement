package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.Item;

import java.math.BigDecimal;

/**
 *
 * Refrigerated Truck Class implements Truck interface
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class RefrigeratedTruck implements Truck {

    private BigDecimal cost;

    private int capacity = 800, temperature = 11;

    private Cargo cargo;

    /**
     * Constructor of refrigerated truck class
     * sets the cargo object to a new refrigerated cargo object using cargo factory
     */
    public RefrigeratedTruck(){
        this.cargo = CargoFactory.generateCargo(true);
    }

    /**
     * Getter for refrigerated truck capacity
     * @return refrigerated truck capacity as an integer
     */
    @Override
    public int getCapacity(){
        return capacity;
    }

    /**
     * Getter for cost of refrigerated truck
     * @return get cost of truck as a big decimal rounded down
     */
    @Override
    public BigDecimal getCost(){
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    /**
     * Getter for refrigerated truck cargo
     * @return get the cargo object for an refrigerated truck
     */
    @Override
    public Cargo getCargo(){
        return cargo;
    }

    /**
     * This method adds an item object to the cargo hashmap.
     * Pass the key value pairs as parameters.
     * Also sets the refrigerated truck cargo temperature based on new items
     * and updates the cost of the truck
     * @param item object
     * @param quantity of items to add
     */
    @Override
    public void addItem(Item item, Integer quantity){

        cargo.addItem(item, quantity);

        // check to see if item temperature is less than current temperature
        // if so change temperature to item temperature
        if (item.temperature < temperature){
            temperature = item.temperature;
        }

        // calculate the refrigerated cargo cost
        cost = new BigDecimal(900+(200*Math.pow(0.7, temperature/5)));
    }
}
