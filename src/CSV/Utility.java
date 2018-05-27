package CSV;

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
import Delivery.Trucks.OrdinaryTruck;
import Delivery.Trucks.RefrigeratedTruck;
import Delivery.Trucks.TruckFactory;
import Stock.Item;

import Stock.Store;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class Utility {

    /**
     * Returns an item list of type HashMap from selected file
     * @param filename String
     * @return HashMap
     * @throws CSVFormatException Exception loading itemlist
     */
    public static HashMap<Item, Integer> loadItemlist(String filename) throws CSVFormatException{

        try{
            if (!filename.contains("item_properties")){
                throw new CSVFormatException("Error 100: Invalid filename!\nFailed to load " + filename + "\nFilename must contain item_properties");
            }

            HashMap<Item, Integer> itemlist = new HashMap<>();
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
            System.out.println("Error 101: I/O exception!\nFailed loading item list.");
            e.printStackTrace();
            return null;
        } catch (ArrayIndexOutOfBoundsException err){
            throw new CSVFormatException("Error 102: Invalid file contents!\nFailed to load " + filename + ".");
        }
    }

    /**
     * Loads the manifest and splits the items into refrigerated and ordinary trucks
     * @param filename String
     * @return HashMap
     * @throws CSVFormatException Exception loading manifest
     */
    public static HashMap<String, Truck> loadManifest(String filename) throws CSVFormatException {
        HashMap<String, Truck> manifest = new HashMap<>();

        String refTruckName = "refrigerated_";
        String ordTruckName = "ordinary_";
        int refIndex = 0;
        int ordIndex = 0;

        // READ MANIFEST INTO HASHMAP<ITEM, INTEGER> AND THEN SEND THE HASHMAPS THROUGH SORTMAP INTO A SORTED LIST OF TYPE <MAP.ENTRY<ITEM, INTEGER>>
        List<Map.Entry<Item, Integer>> sortedRefCargo = sortMap(readManifest(filename, true));
        List<Map.Entry<Item, Integer>> sortedOrdCargo = sortMap(readManifest(filename, false));

        // ADD REF CARGO INTO REF TRUCKS
        while (sortedRefCargo.size() != 0){
            Truck temp = TruckFactory.generateTruck(true);
            HashMap<Item, Integer> items = new HashMap<>();
            for (Map.Entry<Item, Integer> map1 : sortedRefCargo) {
                if ((map1.getValue() + temp.getCargo().getSize()) <= 800){
                    temp.addItem(map1.getKey(), map1.getValue());
                    items.put(map1.getKey(), map1.getValue());
                }
            }
            for (Map.Entry<Item, Integer> item : items.entrySet()) {
                sortedRefCargo.remove(item);
            }
            manifest.put(refTruckName + refIndex, temp);
            refIndex++;
        }

        // ADD BEST POSSIBLE ORD CARGO INTO REF TRUCKS
        Map.Entry<Item, Integer> stockEntry = new AbstractMap.SimpleEntry<>(null, 0);
        for (Map.Entry<String, Truck> manifestEntry : manifest.entrySet()){
            Map.Entry<Item, Integer> bestItem = stockEntry;
            Truck currentTruck = manifestEntry.getValue();
            for (Map.Entry<Item, Integer> cargoEntry : sortedOrdCargo){
                if (((currentTruck.getCargo().getSize() + cargoEntry.getValue()) <= 800) && ((currentTruck.getCargo().getSize() + cargoEntry.getValue()) > bestItem.getValue())){
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
            Truck temp = TruckFactory.generateTruck(false);
            HashMap<Item, Integer> items = new HashMap<>();
            for (Map.Entry<Item, Integer> map1 : sortedOrdCargo) {
                    if ((map1.getValue() + temp.getCargo().getSize()) <= 1000){
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
        return manifest;
    }

    /**
     * Returns a sorted manifest of type List in descending order by item quantity
     * @param map HashMap
     * @return List
     */
    public static List<Map.Entry<Item, Integer>> sortMap(HashMap<Item, Integer> map){
        List<Map.Entry<Item, Integer>> temp = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        List<Map.Entry<Item, Integer>> copyTemp = temp.subList(0, temp.size());
        Collections.reverse(copyTemp);
        return temp;
    }

    /**
     * Returns an unsorted manifest of type HashMap from a selected file
     * @param filename String
     * @param ref Boolean
     * @return HashMap
     * @throws CSVFormatException Exception reading manifest
     */
    public static HashMap<Item, Integer> readManifest(String filename, Boolean ref) throws CSVFormatException{
        try{
            if (!filename.contains("manifest")){
                throw new CSVFormatException("Error 200: Invalid manifest file!\nFailed to load: " + filename + "\nFilename must contain manifest.");
            }

            HashMap<Item, Integer> tempCargo = new HashMap<>();

            // LOADS CSV IN
            CSVReader csvReader = new CSVReader(new FileReader(filename));
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

                // CHECK VALUE EXISTS IN ITEM LIST
                if (getItem(nextRecord[0]) == null){
                    throw new CSVFormatException("Error 201: Item in manifest not found!\nFailed to load: " + filename + "\nEnsure item list has been loaded and all items on manifest are in the item list.");
                }

                // SEPARATES CARGO INTO REFRIGERATED OR ORDINARY
                if (refrigerated) {
                    if(ref){
                        tempCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                    }
                } else {
                    if(!ref){
                        tempCargo.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                    }
                }
            }
            csvReader.close();
            return tempCargo;
        } catch(IOException e){
            throw new CSVFormatException("Error 202: I/O Exception!\nFailed to load: " + filename + ".");
        }
    }

    /**
     * Exports manifest into a .csv format from HashMap
     * @param manifest HashMap
     * @throws CSVFormatException Exception creating manifest
     */
    public static void createManifest(HashMap<Item, Integer> manifest) throws CSVFormatException {
        try{
            if (manifest.size() == 0){
                throw new CSVFormatException("Error 300: Empty manifest!\nThere is no data to be exported. Check the item list has been loaded.");
            }
            CSVWriter csvWriter = new CSVWriter(new FileWriter(getManifestFileName()),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            csvWriter.writeNext(new String[]{">Refrigerated"});
            for (Map.Entry<Item, Integer> entry:manifest.entrySet()) {
                if (entry.getKey().temperature != 11){
                    if (entry.getValue() <= entry.getKey().getReorderPoint()){
                        String[] temp = new String[]{entry.getKey().getName(), Integer.toString(entry.getKey().getReorderAmount())};
                        csvWriter.writeNext(temp);
                    }
                }
            }
            csvWriter.writeNext(new String[]{">Ordinary"});
            for (Map.Entry<Item, Integer> entry:manifest.entrySet()) {
                if (entry.getKey().temperature == 11){
                    if (entry.getValue() <= entry.getKey().getReorderPoint()){
                        String[] temp = new String[]{entry.getKey().getName(), Integer.toString(entry.getKey().getReorderAmount())};
                        csvWriter.writeNext(temp);
                    }
                }
            }
            csvWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new CSVFormatException("Error 301: I/O Exception!\nFailed to write manifest.");
        }
    }

    /**
     * Returns the next available manifest file name
     * @return String
     */
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
     * Returns a sales log of type HashMap from selected file
     * @param filename String
     * @return HashMap
     * @throws CSVFormatException Exception loading sales log
     */
    public static HashMap<Item, Integer> loadSalesLog(String filename) throws CSVFormatException{
        try{
            if (!filename.contains("sales_log")){
                throw new CSVFormatException("Error 400: Invalid filename!\nFailed to load: " + filename + "\nFilename must contain sales_log.");
            }

            // LOADS CSV IN
            HashMap<Item, Integer> tempLog = new HashMap<>();
            CSVReader csvReader = new CSVReader(new FileReader(filename));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                tempLog.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
            }
            return tempLog;
        } catch (IOException e){
            throw new CSVFormatException("Error 401: I/O Exception!\nFailed to load: " + filename + ".");
        } catch (ArrayIndexOutOfBoundsException e){
            throw new CSVFormatException("Error 402: Invalid file contents!\nFailed to load: " + filename + ".");
        }
    }

    /**
     * Returns an item that matches the String name
     * @param name String
     * @return Item
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
     * Returns an item to match the specifications with temperature
     * @param name String
     * @param cost String
     * @param price String
     * @param reorderPoint String
     * @param reorderAmount String
     * @param temperature String
     * @return Item
     */
    public static Item convertStringArrToItem(String name, String cost, String price, String reorderPoint, String reorderAmount, String temperature){
        return new Item(name, new BigDecimal(cost), new BigDecimal(price), Integer.parseInt(reorderPoint), Integer.parseInt(reorderAmount), Integer.parseInt(temperature));
    }

    /**
     * Returns an item to match the specifications without temperature
     * @param name String
     * @param cost String
     * @param price String
     * @param reorderPoint String
     * @param reorderAmount String
     * @return Item
     */
    public static Item convertStringArrToItem(String name, String cost, String price, String reorderPoint, String reorderAmount){
        return new Item(name, new BigDecimal(cost), new BigDecimal(price), Integer.parseInt(reorderPoint), Integer.parseInt(reorderAmount));
    }

}
