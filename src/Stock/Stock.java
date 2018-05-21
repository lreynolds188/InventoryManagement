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

    private HashMap<Item, Integer> stockList;

    public Stock(){
        stockList = new HashMap<>();
    }

    public void put(Item item, Integer quantity){
        stockList.put(item, quantity);
    }

    public Integer get(Item item){
        return(stockList.get(item));
    }

    public HashMap<Item, Integer> getStock(){
        return stockList;
    }
}
