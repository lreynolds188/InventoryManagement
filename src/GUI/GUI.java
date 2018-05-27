package GUI;

import CSV.CSVFormatException;
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
    private JPanel menuPanel, expansionPanel_1, itemlistPanel, expansionPanel_2;
    private JButton btnItemlist, btnLoadItemlist, btnExportManifest, btnLoadManifest, btnLoadSalesLog;
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
        if (e.getSource() == btnItemlist){
            itemlistPanel.setVisible(true);
        }

        if (e.getSource() == btnLoadItemlist){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    loadItemlist(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (CSVFormatException e1) {
                    displayError(e1.error);
                }
            }
        }

        if (e.getSource() == btnExportManifest){
            try {
                exportManifest();
            } catch (CSVFormatException e1) {
                displayError(e1.error);
            }
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
     * @throws CSVFormatException Exception loading itemlist
     */
    public void loadItemlist(String filename) throws CSVFormatException {
        store.loadItemlist(filename);
        HashMap<Item, Integer> hashMapData = Store.getInventory().getStock();
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
     * Loads a manifest and updates the GUI
     * @param fileName String
     * @throws CSVFormatException Exception loading manifest
     */
    public void loadManifest(String fileName) throws CSVFormatException {
        store.loadManifest(fileName);
        updateGUI();
    }

    /**
     * Exports a new manifest to .csv
     * @throws CSVFormatException Exception exporting manifest
     */
    public void exportManifest() throws CSVFormatException {
        store.getManifest().exportManifest(store.getInventory().getStock());
    }

    /**
     * Loads a sales log and updates the GUI
     * @param fileName String
     * @throws CSVFormatException Exception loading sales log
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
        btnItemlist = generateButton("Item List", 195, 40);

        menuPanel.add(capitolLabel);
        menuPanel.add(btnItemlist);
    }

    /**
     * Generates the GUI's item list panel
     */
    public void generateItemlistPanel(){
        itemlistPanel = new JPanel();
        itemlistPanel.setBounds(0, 0, 1080, 800);
        itemlistPanel.setVisible(true);
        btnLoadSalesLog = generateButton("Load Sales Log", 195, 40);
        btnLoadManifest = generateButton("Load Manifest", 195, 40);
        btnExportManifest = generateButton("Export Manifest", 195, 40);
        btnLoadItemlist = generateButton("Load Item List", 195, 40);
        itemlistPanel.add(btnLoadSalesLog);
        itemlistPanel.add(btnLoadManifest);
        itemlistPanel.add(btnExportManifest);
        itemlistPanel.add(btnLoadItemlist);

        generateItemListTable();
        generateScrollPane();
        itemlistPanel.add(scrollPane);

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
        generateItemlistPanel();
        layeredPane.add(itemlistPanel, 1, 0);
    }

    /**
     * Returns a new GUI button
     * @param text String
     * @param width int
     * @param height int
     * @return JButton
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
