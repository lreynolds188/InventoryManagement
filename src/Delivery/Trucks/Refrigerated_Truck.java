package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.RefrigeratedCargo;
import Stock.Item;

import java.math.BigDecimal;

public class Refrigerated_Truck extends Truck {

    private BigDecimal cost;
    private int capacity = 800, temperature;
    private Cargo cargo;

    public Refrigerated_Truck(){
        this.cargo = new RefrigeratedCargo();
    }

    @Override
    public int getCapacity(){
        return capacity;
    }

    @Override
    public BigDecimal getCost(){
        return cost.setScale(2, BigDecimal.ROUND_CEILING);
    }

    @Override
    public Cargo getCargo(){
        return cargo;
    }

    @Override
    public int getTemperature(){
        int temp = 0;
        for (Item item:cargo.get_cargo().keySet()) {
            if (item.getTemperature() < temp){
                temp = item.getTemperature();
            }
        }
        return temp;
    }

    @Override
    public BigDecimal getCargoCost(){
        return cargo.getCost();
    }

    @Override
    public void addItem(Item item, Integer quantity){
        cargo.addItem(item, quantity);
        temperature = getTemperature();
        cost = new BigDecimal(900+(200*Math.pow(0.7, temperature/5)));
    }

}
