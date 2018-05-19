package Delivery.Trucks;

import CSV.Utility;
import Stock.Item;
import Stock.Store;

import java.math.BigDecimal;
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

    public void load_manifest(String filename) {
        manifest = Utility.loadManifest(filename);


        Store.putCapital(manifest.get("refrigerated1").getCost().negate());
        Store.putCapital(manifest.get("refrigerated1").getCargoCost().negate());

        Store.putCapital(manifest.get("ordinary1").getCost().negate());
        Store.putCapital(manifest.get("ordinary1").getCargoCost().negate());

        Store.addInventory(manifest.get("refrigerated1").getCargo().get_cargo());
        Store.addInventory(manifest.get("ordinary1").getCargo().get_cargo());
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