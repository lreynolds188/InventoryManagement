package GUI;

import CSV.CSVFormatException;
import CSV.Utility;
import Stock.Item;
import Stock.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Luke Reynolds
 * @version 1.0
 * @email lreynolds188@gmail.com
 * @website lukereynolds.net
 * @github https://github.com/lreynolds188
 */
public class GUI extends JPanel implements Runnable, ActionListener{

    private static Store store;
    private JLayeredPane layeredPane;
    private JLabel capitolLabel;
    private JFrame mainFrame;
    private JPanel menuPanel, expansionPanel_1, itemListPanel, expansionPanel_2;
    private JButton btnExpansion_1, btnItemList, btnExpansion_2, btnLoadItemList, btnExportManifest, btnLoadManifest, btnLoadSalesLog;
    private JTable itemListTable;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser;
    private DefaultTableModel tableModel;

    public static void main(String[] args){
        store = Store.getInstance();
        GUI gui = new GUI();
        SwingUtilities.invokeLater(gui);
    }

    @Override
    public void run() {
        generateMainFrame();
    }

    /**
     * ActionEvent function
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnExpansion_1){
            expansionPanel_1.setVisible(true);
            itemListPanel.setVisible(false);
            expansionPanel_2.setVisible(false);
        }

        if (e.getSource() == btnItemList){
            expansionPanel_1.setVisible(false);
            itemListPanel.setVisible(true);
            expansionPanel_2.setVisible(false);
        }

        if (e.getSource() == btnExpansion_2){
            expansionPanel_1.setVisible(false);
            itemListPanel.setVisible(false);
            expansionPanel_2.setVisible(true);
        }

        if (e.getSource() == btnLoadItemList){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    loadItemList(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (CSVFormatException e1) {
                    displayError(e1.error);
                }
            }
        }

        if (e.getSource() == btnExportManifest){
            exportManifest();
        }

        if (e.getSource() == btnLoadManifest){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    loadManifest(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (CSVFormatException e1) {
                    displayError(e1.error);
                }
            }
        }

        if (e.getSource() == btnLoadSalesLog){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    loadSalesLog(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (CSVFormatException e1) {
                    displayError(e1.error);
                }
            }
        }
    }

    /**
     * Loads an item list from file and update the tableModel
     * @param filename String
     * @throws CSVFormatException
     */
    public void loadItemList(String filename) throws CSVFormatException {
        HashMap<Item, Integer> hashMapData = Utility.readItemList(filename);
        tableModel.setRowCount(0);
        store.getInventory().getStock().clear();
        for (Map.Entry<Item, Integer> item : hashMapData.entrySet()){
            Object[] data = new Object[7];
            data[0] = item.getKey().getName();
            data[1] = item.getValue();
            data[2] = item.getKey().getManufacturingCost();
            data[3] = item.getKey().getSellPrice();
            data[4] = item.getKey().getReorderPoint();
            data[5] = item.getKey().getReorderAmount();
            if (item.getKey().getTemperature() != 11){
                data[6] = item.getKey().getTemperature();
            }
            store.getInventory().getStock().put(item.getKey(), item.getValue());
            tableModel.addRow(data);
        }
        tableModel.fireTableDataChanged();
    }

    /**
     * Loads a manifest and updates the GUI
     * @param fileName String
     * @throws CSVFormatException
     */
    public void loadManifest(String fileName) throws CSVFormatException {
        store.loadManifest(fileName);
        updateGUI();
    }

    /**
     * Exports a new manifest to .csv
     */
    public void exportManifest(){
        store.getManifest().exportManifest(store.getInventory().getStock());
    }

    /**
     * Loads a sales log and updates the GUI
     * @param fileName String
     * @throws CSVFormatException
     */
    public void loadSalesLog(String fileName) throws CSVFormatException {
        store.loadSalesLog(fileName);
        updateGUI();
    }

    /**
     * Updates the graphics user interface when a change is made
     */
    public void updateGUI(){
        updateCapital();
        updateItemList();
    }

    /**
     * Updates the capital to match the current capital
     */
    public void updateCapital(){
        capitolLabel.setText("Capital: $" + store.getCapitalToString());
    }

    /**
     * Loads an item list from stock and updates the tableModel
     */
    public void updateItemList(){
        HashMap<Item, Integer> hashMapData = store.getInventory().getStock();
        tableModel.setRowCount(0);
        for (Map.Entry<Item, Integer> item : hashMapData.entrySet()){
            Object[] data = new Object[7];
            data[0] = item.getKey().getName();
            data[1] = item.getValue();
            data[2] = item.getKey().getManufacturingCost();
            data[3] = item.getKey().getSellPrice();
            data[4] = item.getKey().getReorderPoint();
            data[5] = item.getKey().getReorderAmount();
            if (item.getKey().getTemperature() != 11){
                data[6] = item.getKey().getTemperature();
            }
            tableModel.addRow(data);
        }
        tableModel.fireTableDataChanged();
    }

    /**
     * Generates the  GUI's main frame
     */
    public void generateMainFrame(){
        mainFrame = new JFrame(store.getName());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setPreferredSize(new Dimension(1280, 800));
        mainFrame.setLocation(300, 150);
        generateMenuPanel();
        generateLayeredPane();
        mainFrame.add(menuPanel, BorderLayout.WEST);
        mainFrame.add(layeredPane, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Generates the GUI's menu panel
     */
    public void generateMenuPanel(){
        menuPanel = new JPanel(new GridLayout(18, 1));
        menuPanel.setPreferredSize(new Dimension(200, 800));

        capitolLabel = new JLabel("Capital: $" + store.getCapitalToString());
        capitolLabel.setHorizontalAlignment(JLabel.CENTER);
        btnItemList = generateButton("Item List", 195, 40);
        btnExpansion_1 = generateButton("Expansion_1", 195, 40);
        btnExpansion_2 = generateButton("Expansion_2", 195, 40);

        menuPanel.add(capitolLabel);
        menuPanel.add(btnItemList);
        menuPanel.add(btnExpansion_1);
        menuPanel.add(btnExpansion_2);
    }

    /**
     * Generates the GUI's item list panel
     */
    public void generateItemListPanel(){
        itemListPanel = new JPanel();
        itemListPanel.setBounds(0, 0, 1080, 800);
        itemListPanel.setVisible(true);
        btnLoadSalesLog = generateButton("Load Sales Log", 195, 40);
        btnLoadManifest = generateButton("Load Manifest", 195, 40);
        btnExportManifest = generateButton("Export Manifest", 195, 40);
        btnLoadItemList = generateButton("Load Item List", 195, 40);
        itemListPanel.add(btnLoadSalesLog);
        itemListPanel.add(btnLoadManifest);
        itemListPanel.add(btnExportManifest);
        itemListPanel.add(btnLoadItemList);

        generateItemListTable();
        generateScrollPane();
        itemListPanel.add(scrollPane);

        generateFileChooser();
    }

    /**
     * Generates the GUI's file chooser
     */
    public void generateFileChooser(){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/assets"));
    }

    /**
     * Generates the GUI's expansion panel 1
     */
    public void generateExpansionPanel_1(){
        expansionPanel_1 = new JPanel();
        expansionPanel_1.setBounds(0, 0, 1080, 800);
        expansionPanel_1.setBackground(Color.BLACK);
        expansionPanel_1.setVisible(false);
    }

    /**
     * Generates the GUI's expansion panel 2
     */
    public void generateExpansionPanel_2(){
        expansionPanel_2 = new JPanel();
        expansionPanel_2.setBounds(0, 0, 1080, 800);
        expansionPanel_2.setBackground(Color.GREEN);
        expansionPanel_2.setVisible(false);
    }

    /**
     * Generates the GUI's item list table
     */
    public void generateItemListTable(){
        String[] columnNames = {"Item Name", "Quantity", "Manufacture Cost", "Price", "Reorder Point", "Reorder Amount", "Temperature"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        itemListTable = new JTable(tableModel);
        itemListTable.setPreferredSize(new Dimension(1080, 760));
    }

    /**
     * Generates the GUI's scroll pane
     */
    public void generateScrollPane(){
        scrollPane = new JScrollPane(itemListTable);
        itemListTable.setFillsViewportHeight(false);
        scrollPane.setBounds(200, 0, 1080, 700);
        scrollPane.setPreferredSize(new Dimension(1080, 700));
    }

    /**
     * Generates the GUI's layered pane
     */
    public void generateLayeredPane(){
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(200, 0, 520, 480);
        generateExpansionPanel_1();
        generateItemListPanel();
        generateExpansionPanel_2();
        layeredPane.add(itemListPanel, new Integer(1), 0);
        layeredPane.add(expansionPanel_1, new Integer(0), 0);
        layeredPane.add(expansionPanel_2, new Integer(2), 0);
    }

    /**
     * Returns a new GUI button
     * @param text String
     * @param width int
     * @param height int
     * @return
     */
    public JButton generateButton(String text, int width, int height){
        JButton temp = new JButton(text);
        temp.addActionListener(this);
        temp.setSize(width, height);
        return temp;
    }

    /**
     * Displays an error dialog box
     * @param error String
     */
    public void displayError(String error){
        JOptionPane.showMessageDialog(this,
                error,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
