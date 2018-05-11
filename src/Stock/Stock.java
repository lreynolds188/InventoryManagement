package Stock;

import CSV.Utility;

import java.util.HashMap;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Stock {

    private HashMap<Item, Integer> stocklist;

    public Stock(){
        loadStock();
    }

    public void loadStock(){
        stocklist = Utility.readItemList();
    }

    public void put(Item item, Integer quantity){
        stocklist.put(item, quantity);
    }

    public Integer get(String name){
        return(stocklist.get(name));
    }

    public void saveStock() {
        Utility.writeItemList(stocklist);
    }

    public HashMap<Item, Integer> getStock(){
        return stocklist;
    }
}
