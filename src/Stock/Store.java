package Stock;

import CSV.CSVFormatException;
import CSV.Utility;
import Delivery.Trucks.Manifest;

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
public class Store {
    private static String name;
    private static BigDecimal capital;
    private static Stock stock;
    private static Manifest manifest;

    protected Store(String name) {
        this.name = name;
        this.capital = new BigDecimal(100000.00);
        this.stock = new Stock();
        this.manifest = new Manifest();
    }

    private static class InstanceHolder{
        private final static Store INSTANCE = new Store("SuperMart");
    }

    public static Store getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public static void putCapital(BigDecimal value){
        capital = capital.add(value);
    }

    public static void addInventory(HashMap<Item, Integer> itemList){
        for (Map.Entry<Item, Integer> set : itemList.entrySet()){
            stock.put(set.getKey(), stock.getStock().get(set.getKey()) + set.getValue());
        }
    }

    public static void removeInventory(HashMap<Item, Integer> itemList){
        for (Map.Entry<Item, Integer> set : itemList.entrySet()){
            stock.put(set.getKey(), stock.getStock().get(set.getKey()) - set.getValue());
        }
    }

    public static void loadManifest(String fileName) throws CSVFormatException {
        manifest.load_manifest(fileName);
    }

    public static void loadSalesLog(String fileName) throws CSVFormatException {
        try {
            HashMap<Item, Integer> sales = Utility.loadSalesLog(fileName);
            Store.removeInventory(sales);

            for (Map.Entry<Item, Integer> log : sales.entrySet()){
                Store.putCapital(log.getKey().getSell_price().multiply(new BigDecimal(log.getValue())));
            }
        }catch(NullPointerException err){
            throw new CSVFormatException("Error 4: Failed to load sales log " + fileName + "\nNullPointerException: Please check that the item list has been loaded!");
        }
    }

    public static String getName(){
        return name;
    }

    public static BigDecimal getCapital(){
        return capital;
    }

    public static Stock getInventory(){
        return stock;
    }

    public static Manifest getManifest() {
        return manifest;
    }

    public static String getCapitalToString(){
        return capital.setScale(2, BigDecimal.ROUND_CEILING).toString();
    }
}