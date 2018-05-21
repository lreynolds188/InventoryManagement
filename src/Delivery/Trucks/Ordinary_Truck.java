package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Stock.Item;

import java.math.BigDecimal;

public class Ordinary_Truck extends Truck {

    private BigDecimal cost;
    private int capacity = 1000;
    private Cargo cargo;

    public Ordinary_Truck(){
        this.cargo = CargoFactory.getCargo(false);
    }

    @Override
    public int getCapacity(){
        return capacity;
    }

    @Override
    public BigDecimal getCost(){
        return cost.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    @Override
    public Cargo getCargo(){
        return cargo;
    }

    @Override
    public void addItem(Item item, Integer quantity){
        cargo.addItem(item, quantity);
        cost = new BigDecimal(750 + (0.25 * cargo.getSize()));
    }

}
