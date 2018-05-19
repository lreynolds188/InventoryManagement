package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Stock.Item;

import java.math.BigDecimal;

public abstract class Truck {
    public Cargo getCargo(){return null;}
    public void addItem(Item item, Integer quantity){}
    public BigDecimal getCargoCost(){return null;}
    public BigDecimal getCost(){return null;}
    public int getCapacity(){return -1;}
    public int getTemperature(){return 11;}
}
