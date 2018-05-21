package Delivery.Trucks;

import CSV.CSVFormatException;
import CSV.Utility;
import Stock.Item;
import Stock.Store;

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

    private HashMap<String, Truck> manifest;

    public Manifest(){
        manifest = new HashMap<>();
    }

    public void load_manifest(String filename) throws CSVFormatException {
        manifest = Utility.loadManifest(filename);

        for (Map.Entry<String, Truck> temp: manifest.entrySet()){
            Store.putCapital(temp.getValue().getCost().negate());
            Store.putCapital(temp.getValue().getCargo().getCost().negate());
            Store.addInventory(temp.getValue().getCargo().getCargo());
        }
    }

    public void put(String name, Truck truck){
        manifest.put(name, truck);
    }

    public Truck get(String name){
        return(manifest.get(name));
    }

    public void exportManifest(HashMap<Item, Integer> itemList){
        Utility.createManifest(itemList);
    }

}