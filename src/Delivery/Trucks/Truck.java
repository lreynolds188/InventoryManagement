package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Stock.Item;

import java.math.BigDecimal;

public interface Truck {
    Cargo getCargo();

     void addItem(Item item, Integer quantity);
     BigDecimal getCost();
     int getCapacity();

}

