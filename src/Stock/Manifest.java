package Stock;

import CSV.CSVFormatException;
import CSV.Utility;
import Delivery.Trucks.Truck;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Manifest {

    public HashMap<String, Truck> manifest;

    public Manifest(){
        manifest = new HashMap<>();
    }

    /**
     * Loads the manifest by calling the loadManifest function inside the Utility class and update the capital and inventory
     * @param filename String
     * @throws CSVFormatException
     */
    public void loadManifest(String filename) throws CSVFormatException {
        manifest = Utility.loadManifest(filename);

        for (Map.Entry<String, Truck> temp: manifest.entrySet()){
            Store.putCapital(temp.getValue().getCost().negate());
            Store.putCapital(temp.getValue().getCargo().getCost().negate());
            Store.addInventory(temp.getValue().getCargo().getCargo());
        }
    }

    /**
     * Setter for the manifest
     * @param name String
     * @param truck Truck
     */
    public void put(String name, Truck truck){
        manifest.put(name, truck);
    }

    /**
     * Getter for the manifest value
     * @param name String
     * @return Truck
     */
    public Truck get(String name){
        return(manifest.get(name));
    }

    /**
     * Exports a new manifest by calling the createManifest() function in the Utility class
     * @param itemList HashMap<Item, Integer>
     */
    public void exportManifest(HashMap<Item, Integer> itemList) throws CSVFormatException {
        Utility.createManifest(itemList);
    }

}