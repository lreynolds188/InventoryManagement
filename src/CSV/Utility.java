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
import Delivery.Trucks.Ordinary_Truck;
import Delivery.Trucks.Refrigerated_Truck;
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
     *
     * @return
     */
    public static HashMap<Item, Integer> readItemList(String filename) throws CSVFormatException{

        try{
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
            System.out.println("Error loading item list!");
            e.printStackTrace();
            return null;
        } catch (ArrayIndexOutOfBoundsException err){
            throw new CSVFormatException("Error 100: Failed to load " + filename + "\nInvalid file contents");
        }
    }

    /**
     *
     * @return
     */
    public static HashMap<String, Truck> loadManifest(String fileName) throws CSVFormatException {
        HashMap<String, Truck> manifest = new HashMap<>();

        String refTruckName = "refrigerated_";
        String ordTruckName = "ordinary_";
        int refIndex = 0;
        int ordIndex = 0;

        // READ MANIFEST INTO HASHMAP<ITEM, INTEGER> AND THEN SEND THE HASHMAPS THROUGH SORTMAP INTO A SORTED LIST OF TYPE <MAP.ENTRY<ITEM, INTEGER>>
        List<Map.Entry<Item, Integer>> sortedRefCargo = sortMap(readManifest(fileName, true));
        List<Map.Entry<Item, Integer>> sortedOrdCargo = sortMap(readManifest(fileName, false));

        // ADD REF CARGO INTO REF TRUCKS
        while (sortedRefCargo.size() != 0){
            Truck temp = new Refrigerated_Truck();
            HashMap<Item, Integer> items = new HashMap<>();
            for (Map.Entry<Item, Integer> map1 : sortedRefCargo) {
                if ((map1.getValue() + temp.getCargo().get_size()) <= 800){
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
        return manifest;
    }

    /**
     *
     * @param map
     * @return
     */
    public static List<Map.Entry<Item, Integer>> sortMap(HashMap<Item, Integer> map){
        List<Map.Entry<Item, Integer>> temp = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        List<Map.Entry<Item, Integer>> copyTemp = temp.subList(0, temp.size());
        Collections.reverse(copyTemp);
        return temp;
    }

    public static HashMap<Item, Integer> readManifest(String fileName, Boolean ref) throws CSVFormatException{
        try{
            HashMap<Item, Integer> tempCargo = new HashMap<>();

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

                // CHECK VALUE EXISTS IN ITEM LIST
                if (getItem(nextRecord[0]) == null){
                    throw new CSVFormatException("Error 300: Failed to load " + fileName + "\nItem not contained in item list");
                } else if (nextRecord.length != 2){
                    throw new CSVFormatException("Error 301: Failed to load " + fileName + "\nInvalid file contents");
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
        } catch(IOException err){
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

    /**
     *
     * @return
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
     *
     * @param fileName
     * @return
     */
    public static HashMap<Item, Integer> loadSalesLog(String fileName) throws CSVFormatException{
        try{
            // LOADS CSV IN
            HashMap<Item, Integer> tempLog = new HashMap<>();
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if(nextRecord.length == 2){
                    tempLog.put(getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                } else {
                    throw new CSVFormatException("Error 400: Failed to load " + fileName + "\nInvalid file contents");
                }
            }
            return tempLog;
        } catch (IOException err){
            throw new CSVFormatException("Error 401: Failed to load " + fileName + "\nInvalid file contents");
        } catch (ArrayIndexOutOfBoundsException err){
            throw new CSVFormatException("Error 402: Failed to load " + fileName + "\nInvalid file contents");
        }
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

}
