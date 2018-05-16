package Delivery.Truck;

import CSV.Utility;
import Stock.Item;

import java.util.ArrayList;

/**
 * Refrigerated truck class extending Truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class Refrigerated_Truck extends Truck {

    double cost;
    int cargo_cap, temperature;
    ArrayList<Object[]> cargo;
    Utility utility;

    /**
     * Class constructor that initializes class property fields
     */
    public Refrigerated_Truck(){

        this.utility = new Utility();

        this.cargo = new ArrayList<>();

        this.load_cargo();

        this.cargo_cap = 800;

        this.temperature = get_temperature();

        // Cost in dollars equal to 900 + 200 × 0.7^T/5
        // where T is  the  truck’s temperature in °C
        this.cost = 900+(200*Math.pow(0.7, temperature/5));
    }

    @Override
    public void load_cargo() {
        cargo = utility.readManifest("./assets/manifest.csv", true);
    }

    /**
     * Gets the list of cargo for the refrigerated truck
     * @return list of cargo
     */
    @Override
    public ArrayList<Object[]> get_cargo(){
        return cargo;
    }

    /**
     * Gets the trucks cargo capactity
     * @return integer representing cargo capacity
     */
    @Override
    public int get_capacity() {
        return cargo_cap;
    }

    /**
     * Gets the trucks temperature based on items in cargo
     * @return temperature
     */
    public int get_temperature(){

        int temp = 0;

        Item item;

        for (Object[] obj:cargo) {
            item = utility.getItem(obj[0].toString());
            if (item.temperature < temp){
                temp = item.temperature;
            }
        }
        return temp;
    }

}
