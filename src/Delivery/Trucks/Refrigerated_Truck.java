package Delivery.Trucks;

import Delivery.Cargo.Cargo;
import Delivery.Cargo.CargoFactory;
import Delivery.Cargo.RefrigeratedCargo;
import Stock.Item;

import java.math.BigDecimal;
import java.util.Map;

public class Refrigerated_Truck extends Truck {

    private BigDecimal cost;
    private int capacity = 800, temperature;
    private Cargo cargo;

    public Refrigerated_Truck(){
        this.cargo = CargoFactory.getCargo(true);
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
        if (item.temperature < temperature){
            temperature = item.temperature;
        }
        cost = new BigDecimal(900+(200*Math.pow(0.7, temperature/5)));
    }

}
