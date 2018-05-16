package Stock;

import java.math.BigDecimal;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Item {

    private String name;
    private BigDecimal manufacturing_cost, sell_price;
    private int reorder_point, reorder_amount, temperature;

    public Item(String name, BigDecimal manufacturing_cost, BigDecimal price, int reorderPoint, int reorderAmount, int temperature){
        this.name = name;
        this.manufacturing_cost = manufacturing_cost;
        this.sell_price = price;
        this.reorder_point = reorderPoint;
        this.reorder_amount = reorderAmount;
        this.temperature = temperature;
    }

    public Item(String name, BigDecimal manufacturing_cost, BigDecimal price, int reorderPoint, int reorderAmount){
        this.name = name;
        this.manufacturing_cost = manufacturing_cost;
        this.sell_price = price;
        this.reorder_point = reorderPoint;
        this.reorder_amount = reorderAmount;
        this.temperature = 11;
    }


    public String getName(){
        return name;
    }

    public BigDecimal getManufacturing_cost(){
        return manufacturing_cost;
    }

    public BigDecimal getSell_price() {
        return sell_price;
    }

    public int getReorder_point(){
        return reorder_point;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getReorder_amount() {
        return reorder_amount;
    }
}
