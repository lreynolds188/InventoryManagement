package Stock;

import CSV.CSVFormatException;
import CSV.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Stock {

    /**
     * Stores all items and their quantities
     */
    private HashMap<Item, Integer> stocklist;

    public Stock(){
        stocklist = new HashMap<>();
    }

    /**
     * Loads itemlist, adds values to the
     * @param filename
     * @return
     * @throws CSVFormatException
     */
    public void loadItemlist(String filename) throws CSVFormatException {
        HashMap<Item, Integer> temp = Utility.loadItemlist(filename);
        stocklist.clear();
        for (Map.Entry<Item, Integer> item : temp.entrySet()){
            stocklist.put(item.getKey(), item.getValue());
        }
    }

    /**
     * Adds items to the stock list
     * @param item Item
     * @param quantity Integer
     */
    public void put(Item item, Integer quantity){
        stocklist.put(item, quantity);
    }

    /**
     * Gets a quantity for an item in the stock list
     * @param item Item
     * @return Integer
     */
    public Integer get(Item item){
        return(stocklist.get(item));
    }

    /**
     * Getter for the stock list
     * @return HashMap<Item, Integer>
     */
    public HashMap<Item, Integer> getStock(){
        return stocklist;
    }
}
