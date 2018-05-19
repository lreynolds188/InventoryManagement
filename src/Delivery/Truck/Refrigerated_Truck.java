package Delivery.Truck;

import CSV.Utility;
import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Refrigerated truck class extending Truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class Refrigerated_Truck extends Truck {

    private BigDecimal cost;
    private int capacity = 800, temperature;
    private Cargo cargo;

    // Instantiate new cargo Factory object
    CargoFactory cargoFactory = new CargoFactory();

    /**
     * Class constructor that initializes class property fields
     */
    public Refrigerated_Truck(){

        // creates a new cargo object from factory. Pass True for ordinary truck, False for refrigerated truck
        this.cargo = cargoFactory.getCargo(false);

        this.temperature = get_temperature();

        // Cost in dollars equal to 900 + 200 × 0.7^T/5
        // where T is  the  truck’s temperature in °C
        this.cost = new BigDecimal(900+(200*Math.pow(0.7, temperature/5)));
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
     * Gets the cost total cost of the cargo
     * @return Cost as dollars
     */
    @Override
    public BigDecimal getCost() {
        return cost.setScale(2, BigDecimal.ROUND_CEILING);
    }

    /**
     * Gets the list of cargo for the refrigerated truck
     * @return list of cargo
     */
    @Override
    public Cargo get_cargo(){
        return cargo;
    }


    /**
     * Gets the trucks temperature based on items in cargo
     * @return temperature
     */
    public int get_temperature(){
        int temp = 0;

        Item item;

        for (String obj:cargo.get_cargo().keySet()) {

            item = Utility.getItem(obj);

            if (item.getTemperature() < temp){

                temp = item.getTemperature();
            }
        }
        return temp;
    }

    @Override
    public void addItem(String item, Integer quantity) {
        cargo.addItem(item,quantity);
    }

}
