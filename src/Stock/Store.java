package Stock;

import Delivery.Manifest;

import java.math.BigDecimal;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Store {
    String name;
    BigDecimal capital;
    Stock stock;
    Manifest manifest;

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

    public String getName(){
        return name;
    }

    public BigDecimal getCapital(){
        return capital;
    }

    public String getCapitalToString(){
        return capital.setScale(2, BigDecimal.ROUND_CEILING).toString();
    }

    public Stock getStock(){
        return stock;
    }

    public Manifest getManifest() {
        return manifest;
    }
}