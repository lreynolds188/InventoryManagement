package CSV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import Delivery.Trucks.Truck;
import Delivery.Trucks.Ordinary_Truck;
import Delivery.Trucks.Refrigerated_Truck;
import Stock.Item;

import Stock.Store;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.text.html.HTMLDocument;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Utility {

    /**
     *
     * @return
     */
    public static HashMap<Item, Integer> readItemList(String filename){
        HashMap<Item, Integer> itemlist;
        try{
            itemlist = new HashMap<>();
            CSVReader csvReader = new CSVReader(new FileReader(filename));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null){
                if(nextRecord.length == 6){
                    itemlist.put(convertStringArrToItem(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4], nextRecord[5]), 0);
                } else {
                    itemlist.put(convertStringArrToItem(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4]), 0);
                }
            }
            csvReader.close();
            return itemlist;
        } catch (IOException e){
            System.out.println("Error loading item list!");
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static HashMap<String, Truck> loadManifest(String fileName){
        try{
            HashMap<String, Truck> manifest = new HashMap<>();

            String refTruckName = "refrigerated_";
            String ordTruckName = "ordinary_";
            int refIndex = 0;
            int ordIndex = 0;

            HashMap<Item, Integer> refCargo = new HashMap<>();
            HashMap<Item, Integer> ordCargo = new HashMap<>();

            // LOADS CSV IN
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            boolean refrigerated = false;
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].equalsIgnoreCase(">Refrigerated")) {
                    refrigerated = true;
                    continue;
                } else if (nextRecord[0].equalsIgnoreCase(">Ordinary")) {
                    refrigerated = false;
                    continue;
                }

                // SEPARATES ITEMS INTO REFRIGERATED OR ORDINARY
                if (refrigerated) {
                    refCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                } else {
                    ordCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                }
            }

            while(refCargo.size() != 0){
                if(refCargo.size() != 1){
                    Map.Entry<Item, Integer> highItem = null, lowItem = null;
                    int high = 0;
                    for (Map.Entry<Item, Integer> map1 : refCargo.entrySet()) {
                        for (Map.Entry<Item, Integer> map2 : refCargo.entrySet()) {
                            if (!map1.equals(map2)) {
                                if (map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount() > high &&
                                        map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount() <= 800) {
                                    highItem = map1;
                                    lowItem = map2;
                                    high = map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount();
                                }
                            }
                        }
                    }
                    Truck temp = new Refrigerated_Truck();
                    temp.addItem(highItem.getKey(), highItem.getValue());
                    temp.addItem(lowItem.getKey(), lowItem.getValue());
                    refCargo.remove(highItem.getKey());
                    refCargo.remove(lowItem.getKey());
                    manifest.put(refTruckName + refIndex, temp);
                    refIndex++;
                } else {
                    Truck temp = new Refrigerated_Truck();

                    Item lastItem = (Item) refCargo.keySet().toArray()[0];

                    System.out.println(lastItem.getName() + " " + lastItem.getReorder_amount());
                    temp.addItem(lastItem, lastItem.getReorder_amount());
                    refCargo.remove(lastItem);
                    manifest.put(refTruckName + refIndex, temp);
                    refIndex++;
                }
            }

//            while(ordCargo.size() != 0){
//                if(ordCargo.size() != 1){
//                    Map.Entry<Item, Integer> highItem = null, lowItem = null;
//                    int high = 0;
//                    for (Map.Entry<Item, Integer> map1 : refCargo.entrySet()) {
//                        for (Map.Entry<Item, Integer> map2 : refCargo.entrySet()) {
//                            if (!map1.equals(map2)) {
//                                if (map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount() > high &&
//                                        map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount() <= 800) {
//                                    highItem = map1;
//                                    lowItem = map2;
//                                    high = map1.getKey().getReorder_amount() + map2.getKey().getReorder_amount();
//                                }
//                            }
//                        }
//                    }
//                    Truck temp = new Refrigerated_Truck();
//                    temp.addItem(highItem.getKey(), highItem.getValue());
//                    temp.addItem(lowItem.getKey(), lowItem.getValue());
//                    refCargo.remove(highItem.getKey());
//                    refCargo.remove(lowItem.getKey());
//                    manifest.put(refTruckName + refIndex, temp);
//                    refIndex++;
//                } else {
//                    Truck temp = new Refrigerated_Truck();
//
//
//                    Item lastItem = (Item) refCargo.keySet().toArray()[0];
//
//                    System.out.println(lastItem.getName() + " " + lastItem.getReorder_amount());
//                    temp.addItem(lastItem, lastItem.getReorder_amount());
//                    refCargo.remove(lastItem);
//                    manifest.put(refTruckName + refIndex, temp);
//                    refIndex++;
//                }
//            }

            csvReader.close();
            return manifest;
        } catch (Exception err) {
            System.out.println("Error loading manifest!");
            err.printStackTrace();
            return null;
        }



//


//
//
//                refIndex = 0;
//                // for each truck in the list
//                for (Map.Entry<String, Truck> set: manifest.entrySet()){
//                    // if truck is refrigerated
//                    if (set.getKey().contains("refrigerated")){
//                        // get the truck from the hashmap
//                        Truck tempRefTruck = set.getValue();
//                        // if truck cargo + item < truck capacity
//                        if ((tempRefTruck.getCargo().get_size() + Integer.parseInt(nextRecord[1])) < tempRefTruck.getCapacity()) {
//                            // add item to cargo
//                            tempRefTruck.addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
//                        // move on to next refrigerated truck
//                        } else {
//                            refIndex++;
//                            if (manifest.containsKey(refTruckName + (refIndex))){
//                                 continue;
//                            } else {
//                                manifest.put(refTruckName + refIndex, new Refrigerated_Truck());
//                                manifest.get(refTruckName + refIndex).addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
//                            }
//                        }
//                    }
//
//                }
//
//
//
//                } else {
//                    Truck tempOrdTruck = manifest.get(ordTruckName + ordIndex);
//                    if (tempOrdTruck.getCargo().get_size() + Integer.parseInt(nextRecord[1]) < tempOrdTruck.getCapacity()) {
//                        tempOrdTruck.addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
//                    } else {
//                        ordIndex++;
//                        manifest.put(ordTruckName + ordIndex, new Ordinary_Truck());
//                        manifest.get(ordTruckName + ordIndex).addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
//                    }
//                }
//
    }

    /**
     *
     * @param manifest
     */
    public static void createManifest(HashMap<Item, Integer> manifest){
        try{
            CSVWriter csvWriter = new CSVWriter(new FileWriter(getManifestFileName()),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            csvWriter.writeNext(new String[]{">Refrigerated"});
            for (Map.Entry<Item, Integer> entry:manifest.entrySet()) {
                if (entry.getKey().temperature != 11){
                    if (entry.getValue() <= entry.getKey().getReorder_point()){
                        String[] temp = new String[]{entry.getKey().getName(), Integer.toString(entry.getKey().getReorder_amount())};
                        csvWriter.writeNext(temp);
                    }
                }
            }
            csvWriter.writeNext(new String[]{">Ordinary"});
            for (Map.Entry<Item, Integer> entry:manifest.entrySet()) {
                if (entry.getKey().temperature == 11){
                    if (entry.getValue() <= entry.getKey().getReorder_point()){
                        String[] temp = new String[]{entry.getKey().getName(), Integer.toString(entry.getKey().getReorder_amount())};
                        csvWriter.writeNext(temp);
                    }
                }
            }
            csvWriter.close();
        } catch (IOException e){
            System.out.println("Error writing to manifest!");
            e.printStackTrace();
        }
    }

    public static String getManifestFileName(){
        String fileName = "./assets/manifest_0.csv";

        String extension = ".";
        String name = "";

        int idxOfDot = fileName.lastIndexOf('.');   //Get the last index of . to separate extension
        extension = fileName.substring(idxOfDot);
        name = fileName.substring(0, idxOfDot - 1);

        Path path = Paths.get(fileName);
        int counter = 1;
        while(Files.exists(path)){
            fileName = name+""+counter+""+extension;
            path = Paths.get(fileName);
            counter++;
        }
        return fileName;
    }

    /**
     *
     * @param name
     * @return
     */
    public static Item getItem(String name){
        HashMap<Item, Integer> items = Store.getInventory().getStock();
        for (Map.Entry<Item, Integer> entry:items.entrySet()) {
            if (entry.getKey().getName().equalsIgnoreCase(name)){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @param cost
     * @param price
     * @param reorderPoint
     * @param reorderAmount
     * @param temperature
     * @return
     */
    public static Item convertStringArrToItem(String name, String cost, String price, String reorderPoint, String reorderAmount, String temperature){
        return new Item(name, new BigDecimal(cost), new BigDecimal(price), Integer.parseInt(reorderPoint), Integer.parseInt(reorderAmount), Integer.parseInt(temperature));
    }

    /**
     *
     * @param name
     * @param cost
     * @param price
     * @param reorderPoint
     * @param reorderAmount
     * @return
     */
    public static Item convertStringArrToItem(String name, String cost, String price, String reorderPoint, String reorderAmount){
        return new Item(name, new BigDecimal(cost), new BigDecimal(price), Integer.parseInt(reorderPoint), Integer.parseInt(reorderAmount));
    }

//    /**
//     *
//     * @param item
//     * @return
//     */
//    public static String[] convertItemToStringArr(Item item){
//        String[] temp;
//        if (item.getTemperature() >= -20 && item.getTemperature() <= 10){
//            temp = new String[]{item.getName(), String.valueOf(item.getManufacturing_cost()), String.valueOf(item.getSell_price()), String.valueOf(item.getReorder_point()), String.valueOf(item.getReorder_amount()), String.valueOf(item.getTemperature())};
//        } else {
//            temp = new String[]{item.getName(), String.valueOf(item.getManufacturing_cost()), String.valueOf(item.getSell_price()), String.valueOf(item.getReorder_point()), String.valueOf(item.getReorder_amount())};
//        }
//        return temp;
//    }

}
