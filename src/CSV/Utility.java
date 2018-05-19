package CSV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
            int index = 0;

            manifest.put(refTruckName + index, new Refrigerated_Truck());
            manifest.put(ordTruckName + index, new Ordinary_Truck());

            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            boolean refrigerated = false;
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null){
                if (nextRecord[0].equalsIgnoreCase(">Refrigerated")){
                    refrigerated = true;
                    continue;
                } else if (nextRecord[0].equalsIgnoreCase(">Ordinary")){
                    refrigerated = false;
                    continue;
                }

                if (manifest.get(refTruckName + index).getCargo().get_cargo().size() < manifest.get(refTruckName + index).getCapacity()){
                    if (refrigerated){
                        manifest.get(refTruckName + index).addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                    } else {
                        manifest.get(ordTruckName + index).addItem(Utility.getItem(nextRecord[0]), Integer.parseInt(nextRecord[1]));
                    }
                } else {
                    index++;
                }

            }
            csvReader.close();
            for (Map.Entry<String, Truck> set : manifest.entrySet()){
                manifest.put(set.getKey(), set.getValue());
            }
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
