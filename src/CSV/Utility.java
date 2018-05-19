package CSV;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import Delivery.Trucks.Truck;
import Delivery.Trucks.Ordinary_Truck;
import Delivery.Trucks.Refrigerated_Truck;
import Stock.Item;

import Stock.Store;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.omg.CORBA.INTERNAL;

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

    public static List<Map.Entry<Item, Integer>> sortMap(HashMap<Item, Integer> map){
        List<Map.Entry<Item, Integer>> temp = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        List<Map.Entry<Item, Integer>> copyTemp = temp.subList(0, temp.size());
        Collections.reverse(copyTemp);
        return temp;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, Truck> loadManifest(String fileName) {
        try {
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

                // SEPARATES CARGO INTO REFRIGERATED OR ORDINARY
                if (refrigerated) {
                    refCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                } else {
                    ordCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                }
            }

            // FORMAT MAP INTO SORTED LIST
            List<Map.Entry<Item, Integer>> sortedRefCargo = sortMap(refCargo);
            List<Map.Entry<Item, Integer>> sortedOrdCargo = sortMap(ordCargo);

            // ADD REF CARGO TO REF TRUCKS
            while (sortedRefCargo.size() != 0) {
                if (sortedRefCargo.size() != 1) {
                    Map.Entry<Item, Integer> highItem = null, lowItem = null;
                    int high = 0;
                    for (Map.Entry<Item, Integer> map1 : sortedRefCargo) {
                        for (Map.Entry<Item, Integer> map2 : sortedRefCargo) {
                            if (!map1.equals(map2)) {
                                if (map1.getValue() + map2.getValue() > high &&
                                        map1.getValue() + map2.getValue() <= 800) {
                                    highItem = map1;
                                    lowItem = map2;
                                    high = map1.getValue() + map2.getValue();
                                }
                            }
                        }
                    }
                    Truck temp = new Refrigerated_Truck();
                    temp.addItem(highItem.getKey(), highItem.getValue());
                    temp.addItem(lowItem.getKey(), lowItem.getValue());
                    sortedRefCargo.remove(highItem);
                    sortedRefCargo.remove(lowItem);
                    manifest.put(refTruckName + refIndex, temp);
                    refIndex++;
                } else {
                    Truck temp = new Refrigerated_Truck();
                    Item lastItem = (Item) sortedRefCargo.toArray()[0];
                    temp.addItem(lastItem, lastItem.getReorder_amount());
                    sortedRefCargo.remove(lastItem);
                    manifest.put(refTruckName + refIndex, temp);
                    refIndex++;
                }
            }

            // ADD BEST PLACE ORD CARGO INTO REF TRUCKS
            Map.Entry<Item, Integer> stockEntry = new AbstractMap.SimpleEntry<>(null, 0);
            for (Map.Entry<String, Truck> manifestEntry : manifest.entrySet()){
                Map.Entry<Item, Integer> bestItem = stockEntry;
                Truck currentTruck = manifestEntry.getValue();
                for (Map.Entry<Item, Integer> cargoEntry : sortedOrdCargo){
                    if (((currentTruck.getCargo().get_size() + cargoEntry.getValue()) <= 800) && ((currentTruck.getCargo().get_size() + cargoEntry.getValue()) > bestItem.getValue())){
                        bestItem = cargoEntry;
                    }
                }
                if (bestItem != stockEntry){
                    manifestEntry.getValue().addItem(bestItem.getKey(), bestItem.getValue());
                    sortedOrdCargo.remove(bestItem);
                }
            }

            // ADD REMAINING ORD CARGO INTO ORD TRUCKS
            while (sortedOrdCargo.size() != 0){
                Truck temp = new Ordinary_Truck();
                HashMap<Item, Integer> items = new HashMap<>();
                for (Map.Entry<Item, Integer> map1 : sortedOrdCargo) {
                        if ((map1.getValue() + temp.getCargo().get_size()) <= 1000){
                            temp.addItem(map1.getKey(), map1.getValue());
                            items.put(map1.getKey(), map1.getValue());
                        }
                }

                for (Map.Entry<Item, Integer> item : items.entrySet()) {
                    sortedOrdCargo.remove(item);
                }

                manifest.put(ordTruckName + ordIndex, temp);
                ordIndex++;
            }

            csvReader.close();
            return manifest;
        } catch (Exception err) {
            System.out.println("Error loading manifest!");
            err.printStackTrace();
            return null;
        }
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
