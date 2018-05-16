package Delivery;

import CSV.Utility;
import Delivery.Truck.Truck;

import java.util.HashMap;

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
        manifest = load_manifest();
    }

    public HashMap<String, Truck> load_manifest() {
        return Utility.readManifest();
    }

    public void put(String name, Truck truck){
        manifest.put(name, truck);
    }

    public Truck get(String name){
        return(manifest.get(name));
    }

    public void saveManifest(){

    }

}