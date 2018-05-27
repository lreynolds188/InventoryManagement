package Delivery.Cargo;

import Stock.Item;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Cargo Class
 *
 * @author Jonathan Gonzalez
 * @since May 16, 2018
 * @version 1.0
 */
 public interface Cargo {

   /**
    * Method signature for getting the list of cargo items
    * @return HashMap
    */
   HashMap<Item,Integer> getCargo();

   /**
    * Method signature for getting cost of cargo object
    * @return BigDecimal
    */
    BigDecimal getCost();

   /**
    * Method signature for adding items to the cargo list
    * @param name Item
    * @param quantity Integer
    */
    void addItem(Item name, Integer quantity);

   /**
    * Method signature for getting size of cargo
    * @return Integer
    */
    Integer getSize();
}

