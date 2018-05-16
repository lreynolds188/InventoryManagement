package Delivery.Truck;

import java.util.ArrayList;
import CSV.Utility;

/**
 * Ordinary truck class extending Truck class
 *
 * @author <Jonathan Gonzalez | n9821112>
 * @since <pre>May 16, 2018</pre>
 * @version 1.0
 */

public class OrdinaryTruck extends Truck {

    double cost;
    int cargo_cap;
    ArrayList<Object[]> cargo;
    Utility utility;

    /**
     * Class constructor that initializes class property fields
     */
    public OrdinaryTruck(){

        this.utility = new Utility();

        this.cargo_cap = 1000;

        this.cargo = new ArrayList<>();

        this.load_cargo();

        // Cost in dollars equal to 750 + 0.25q
        // where q is the total quantity of items in the cargo
        this.cost = 750 + (0.25 * cargo.size());
    }

    @Override
    public void load_cargo() {
        cargo = utility.readManifest("./assets/manifest.csv", false);
    }

    /**
     * Gets the list of cargo for the ordinary truck
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

    @Override
    public int get_temperature() {
        return 0;
    }
}
