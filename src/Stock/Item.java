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
    private BigDecimal manufacturingCost, sellPrice;
    private int reorderPoint, reorderAmount;
    public int temperature;

    /**
     * Instantiate item with temperature value
     * @param name String
     * @param manufacturingCost BigDecimal
     * @param price BigDecimal
     * @param reorderPoint int
     * @param reorderAmount int
     * @param temperature int
     */
    public Item(String name, BigDecimal manufacturingCost, BigDecimal price, int reorderPoint, int reorderAmount, int temperature){
        this.name = name;
        this.manufacturingCost = manufacturingCost;
        this.sellPrice = price;
        this.reorderPoint = reorderPoint;
        this.reorderAmount = reorderAmount;
        this.temperature = temperature;
    }

    /**
     * Instantiate item without temperature value
     * @param name String
     * @param manufacturingCost BigDecimal
     * @param price BigDecimal
     * @param reorderPoint int
     * @param reorderAmount int
     */
    public Item(String name, BigDecimal manufacturingCost, BigDecimal price, int reorderPoint, int reorderAmount){
        this.name = name;
        this.manufacturingCost = manufacturingCost;
        this.sellPrice = price;
        this.reorderPoint = reorderPoint;
        this.reorderAmount = reorderAmount;
        this.temperature = 11;
    }

    /**
     * Getter for item name
     * @return String name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for item manufacturingCost
     * @return BigDecimal manufacturingCost
     */
    public BigDecimal getManufacturingCost(){
        return manufacturingCost;
    }

    /**
     * Getter for item sellPrice
     * @return BigDecimal sellPrice
     */
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     * Getter for item reorderPoint
     * @return int reorderPoint
     */
    public int getReorderPoint(){
        return reorderPoint;
    }

    /**
     * Getter for item temperature
     * @return int temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Getter for item reorderAmount
     * @return int reorderAmount
     */
    public int getReorderAmount() {
        return reorderAmount;
    }
}
