package Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    public BigDecimal manufacturing_cost, sell_price;
    public int reorder_point, reorder_amount, temperature;
    public String name;

    /**
     * constructs a new item object with a temperature
     * @param name
     * @param manufacturing_cost
     * @param sell_price
     * @param reorder_point
     * @param reorder_amount
     * @param temperature
     */
    public Item(String name, BigDecimal manufacturing_cost, BigDecimal sell_price, int reorder_point, int reorder_amount, int temperature){
        this.name = name;
        this.manufacturing_cost = manufacturing_cost;
        this.manufacturing_cost.setScale(2, RoundingMode.FLOOR);
        this.sell_price = sell_price;
        this.sell_price.setScale(2, RoundingMode.FLOOR);
        this.reorder_point = reorder_point;
        this.reorder_amount = reorder_amount;
        this.temperature = temperature;
    }

    /**
     * constructs a new item object without a temperature
     * @param name
     * @param manufacturing_cost
     * @param sell_price
     * @param reorder_point
     * @param reorder_amount
     */
    public Item(String name, BigDecimal manufacturing_cost, BigDecimal sell_price, int reorder_point, int reorder_amount){
        this.name = name;
        this.manufacturing_cost = manufacturing_cost;
        this.manufacturing_cost.setScale(2, RoundingMode.FLOOR);
        this.sell_price = sell_price;
        this.sell_price.setScale(2, RoundingMode.FLOOR);
        this.reorder_point = reorder_point;
        this.reorder_amount = reorder_amount;
        this.temperature = 11;
    }
}
