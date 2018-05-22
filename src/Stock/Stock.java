package Stock;

import java.util.HashMap;

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
    private HashMap<Item, Integer> stockList;

    public Stock(){
        stockList = new HashMap<>();
    }

    /**
     * Adds items to the stock list
     * @param item Item
     * @param quantity Integer
     */
    public void put(Item item, Integer quantity){
        stockList.put(item, quantity);
    }

    /**
     * Gets a quantity for an item in the stock list
     * @param item Item
     * @return Integer
     */
    public Integer get(Item item){
        return(stockList.get(item));
    }

    /**
     * Getter for the stock list
     * @return HashMap<Item, Integer>
     */
    public HashMap<Item, Integer> getStock(){
        return stockList;
    }
}
