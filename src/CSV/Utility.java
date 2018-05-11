package CSV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import Delivery.OrdinaryTruck;
import Delivery.RefrigeratedTruck;
import Delivery.Truck;
import Stock.Item;

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

    private static String manifestFileName = "./assets/manifest.csv";
    private static String itemlistFileName = "./assets/item_properties.csv";

    /**
     *
     * @return
     */
    public static HashMap<Item, Integer> readItemList(){
        HashMap<Item, Integer> itemlist;
        try{
            itemlist = new HashMap<>();
            CSVReader csvReader = new CSVReader(new FileReader(itemlistFileName));
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
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param items
     */
    public static void writeItemList(HashMap<Item, Integer> items){
        try{
            CSVWriter csvWriter = new CSVWriter(new FileWriter(itemlistFileName),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            for (Map.Entry<Item, Integer> item:items.entrySet()) {
                String[] temp = convertItemToStringArr(item.getKey());
                csvWriter.writeNext(temp);
            }
            csvWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public static HashMap<String, Truck> readManifest(){
        try{
            HashMap<String, Truck> items = new HashMap<>();
            Truck refrigerated1 = new RefrigeratedTruck();
            Truck ordinary1 = new OrdinaryTruck();
            CSVReader csvReader = new CSVReader(new FileReader(manifestFileName));
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

                if (refrigerated){
                    refrigerated1.addItem(nextRecord[0], Integer.parseInt(nextRecord[1]));
                } else {
                    ordinary1.addItem(nextRecord[0], Integer.parseInt(nextRecord[1]));
                }
            }
            csvReader.close();
            items.put("refrigerated1", refrigerated1);
            items.put("ordinaryl", ordinary1);
            return items;
        } catch (Exception err){
            return null;
        }
    }

    /**
     *
     * @param refTruckCargo
     * @param ordTruckCargo
     */
    public static void writeManifest(HashMap<String, Integer> refTruckCargo, HashMap<String, Integer> ordTruckCargo){
        try{
            CSVWriter csvWriter = new CSVWriter(new FileWriter(manifestFileName),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            csvWriter.writeNext(new String[]{">Refrigerated"});
            for (Map.Entry<String, Integer> entry:refTruckCargo.entrySet()) {
                String[] temp = new String[]{entry.getKey(), entry.getValue().toString()};
                csvWriter.writeNext(temp);
            }
            csvWriter.writeNext(new String[]{">Ordinary"});
            for (Map.Entry<String, Integer> entry:ordTruckCargo.entrySet()) {
                String[] temp = new String[]{entry.getKey(), entry.getValue().toString()};
                csvWriter.writeNext(temp);
            }
            csvWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public static Item getItem(String name){
        HashMap<Item, Integer> items = readItemList();
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

    /**
     *
     * @param item
     * @return
     */
    public static String[] convertItemToStringArr(Item item){
        String[] temp;
        if (item.getTemperature() >= -20 && item.getTemperature() <= 10){
            temp = new String[]{item.getName(), String.valueOf(item.getManufacturing_cost()), String.valueOf(item.getSell_price()), String.valueOf(item.getReorder_point()), String.valueOf(item.getReorder_amount()), String.valueOf(item.getTemperature())};
        } else {
            temp = new String[]{item.getName(), String.valueOf(item.getManufacturing_cost()), String.valueOf(item.getSell_price()), String.valueOf(item.getReorder_point()), String.valueOf(item.getReorder_amount())};
        }
        return temp;
    }

}
