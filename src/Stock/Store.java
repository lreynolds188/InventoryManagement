package Stock;

import CSV.CSVFormatException;
import CSV.Utility;

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

    /**
     * Instance holder class designed to limit the instances of store
     */
    private static class InstanceHolder{
        private final static Store INSTANCE = new Store("SuperMart");
    }

    /**
     * Returns an instance of the store class
     */
    public static Store getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * Adds BigDecimal value to the current capital
     * @param value BigDecimal
     */
    public static void putCapital(BigDecimal value){
        capital = capital.add(value);
    }

    /**
     * Adds each <Item, Quantity> in the HashMap<Item, Integer> to the inventory
     * @param itemList HashMap<Item, Integer>
     */
    public static void addInventory(HashMap<Item, Integer> itemList){
        for (Map.Entry<Item, Integer> set : itemList.entrySet()){
            stock.put(set.getKey(), stock.getStock().get(set.getKey()) + set.getValue());
        }
    }

    /**
     * Removes each <Item, Quantity> in the HashMap<Item, Integer> from the inventory
     * @param itemList HashMap<Item, Integer>
     */
    public static void removeInventory(HashMap<Item, Integer> itemList){
        for (Map.Entry<Item, Integer> set : itemList.entrySet()){
            stock.put(set.getKey(), stock.getStock().get(set.getKey()) - set.getValue());
        }
    }

    /**
     * Calls the loadManifest() function in the manifest
     * @param filename String
     * @throws CSVFormatException
     */
    public static void loadItemlist(String filename) throws CSVFormatException {
        stock.loadItemlist(filename);
    }

    /**
     * Calls the loadManifest() function in the manifest
     * @param filename String
     * @throws CSVFormatException
     */
    public static void loadManifest(String filename) throws CSVFormatException {
        manifest.loadManifest(filename);
    }

    /**
     * Calls the Utility.loadSalesLog() function, removing inventory and adding capital where required
     * @param filename String
     * @throws CSVFormatException
     */
    public static void loadSalesLog(String filename) throws CSVFormatException {
        try {
            HashMap<Item, Integer> sales = Utility.loadSalesLog(filename);
            Store.removeInventory(sales);

            for (Map.Entry<Item, Integer> log : sales.entrySet()){
                Store.putCapital(log.getKey().getSellPrice().multiply(new BigDecimal(log.getValue())));
            }
        }catch(NullPointerException err){
            throw new CSVFormatException("Error 4: Failed to load sales log " + filename + "\nNullPointerException: Please check that the item list has been loaded!");
        }
    }

    /**
     * Getter for the store's name
     * @return String
     */
    public static String getName(){
        return name;
    }

    /**
     * Getter for the store's inventory
     * @return Stock
     */
    public static Stock getInventory(){
        return stock;
    }

    /**
     * Getter for the store's manifest
     * @return Manifest
     */
    public static Manifest getManifest() {
        return manifest;
    }

    /**
     * Getter for the store's capital
     * @return BigDecimal
     */
    public static BigDecimal getCapital(){
        return capital;
    }

    /**
     * Returns the store's capital to string
     * @return String
     */
    public static String getCapitalToString(){
        return capital.setScale(2, BigDecimal.ROUND_CEILING).toString();
    }
}