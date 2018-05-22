package GUI;

import CSV.Utility;
import Stock.Item;
import Stock.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel menuPanel, manifestPanel, itemListPanel, salesPanel;
    private JButton btnManifest, btnItemList, btnSales, btnLoadItemList, btnExportManifest, btnLoadManifest;
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

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnManifest){
            manifestPanel.setVisible(true);
            itemListPanel.setVisible(false);
            salesPanel.setVisible(false);
        }

        if (e.getSource() == btnItemList){
            manifestPanel.setVisible(false);
            itemListPanel.setVisible(true);
            salesPanel.setVisible(false);
        }

        if (e.getSource() == btnSales){
            manifestPanel.setVisible(false);
            itemListPanel.setVisible(false);
            salesPanel.setVisible(true);
        }

        if (e.getSource() == btnLoadItemList){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                loadItemList(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }

        if (e.getSource() == btnExportManifest){
            exportManifest();
        }

        if (e.getSource() == btnLoadManifest){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                loadManifest(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    public void loadManifest(String fileName){
        store.getManifest().load_manifest(fileName);
        updateCapital();
        updateItemList();
    }

    public void loadItemList(String filename){
        HashMap<Item, Integer> hashMapData = Utility.readItemList(filename);
        tableModel.setRowCount(0);
        store.getInventory().getStock().clear();
        for (Map.Entry<Item, Integer> item : hashMapData.entrySet()){
            Object[] data = new Object[7];
            data[0] = item.getKey().getName();
            data[1] = item.getValue();
            data[2] = item.getKey().getManufacturing_cost();
            data[3] = item.getKey().getSell_price();
            data[4] = item.getKey().getReorder_point();
            data[5] = item.getKey().getReorder_amount();
            if (item.getKey().getTemperature() != 11){
                data[6] = item.getKey().getTemperature();
            }
            store.getInventory().getStock().put(item.getKey(), item.getValue());
            tableModel.addRow(data);
        }
        tableModel.fireTableDataChanged();
    }

    public void updateCapital(){
        capitolLabel.setText("Capital: $" + store.getCapitalToString());
    }

    public void updateItemList(){
        HashMap<Item, Integer> hashMapData = store.getInventory().getStock();
        tableModel.setRowCount(0);
        for (Map.Entry<Item, Integer> item : hashMapData.entrySet()){
            Object[] data = new Object[7];
            data[0] = item.getKey().getName();
            data[1] = item.getValue();
            data[2] = item.getKey().getManufacturing_cost();
            data[3] = item.getKey().getSell_price();
            data[4] = item.getKey().getReorder_point();
            data[5] = item.getKey().getReorder_amount();
            if (item.getKey().getTemperature() != 11){
                data[6] = item.getKey().getTemperature();
            }
            tableModel.addRow(data);
        }
        tableModel.fireTableDataChanged();
    }

    public void exportManifest(){
        store.getManifest().exportManifest(store.getInventory().getStock());
    }

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

    public void generateManifestPanel(){
        manifestPanel = new JPanel();
        manifestPanel.setBounds(0, 0, 1080, 800);
        manifestPanel.setBackground(Color.BLACK);
        manifestPanel.setVisible(false);

    }

    public void generateItemListPanel(){
        itemListPanel = new JPanel();
        itemListPanel.setBounds(0, 0, 1080, 800);
        itemListPanel.setVisible(true);
        btnLoadManifest = generateButton("Load Manifest", 195, 40);
        btnExportManifest = generateButton("Export Manifest", 195, 40);
        btnLoadItemList = generateButton("Load Item List", 195, 40);
        itemListPanel.add(btnLoadManifest);
        itemListPanel.add(btnExportManifest);
        itemListPanel.add(btnLoadItemList);

        generateItemListTable();
        generateScrollPane();
        itemListPanel.add(scrollPane);

        generateFileChooser();

    }

    public void generateFileChooser(){
        fileChooser = new JFileChooser();
    }

    public void generateSalesPanel(){
        salesPanel = new JPanel();
        salesPanel.setBounds(0, 0, 1080, 800);
        salesPanel.setBackground(Color.GREEN);
        salesPanel.setVisible(false);
    }

    public void generateMenuPanel(){
        menuPanel = new JPanel(new GridLayout(18, 1));
        menuPanel.setPreferredSize(new Dimension(200, 800));

        capitolLabel = new JLabel("Capital: $" + store.getCapitalToString());
        capitolLabel.setHorizontalAlignment(JLabel.CENTER);
        btnManifest = generateButton("Manifests", 195, 40);
        btnItemList = generateButton("Item List", 195, 40);
        btnSales = generateButton("Sales", 195, 40);

        menuPanel.add(capitolLabel);
        menuPanel.add(btnItemList);
        menuPanel.add(btnManifest);
        menuPanel.add(btnSales);
    }

    public void generateItemListTable(){
        String[] columnNames = {"Item Name", "Quantity", "Manufacture Cost", "Price", "Reorder Point", "Reorder Amount", "Temperature"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        itemListTable = new JTable(tableModel);
        itemListTable.setPreferredSize(new Dimension(1080, 760));
    }

    public void generateScrollPane(){
        scrollPane = new JScrollPane(itemListTable);
        itemListTable.setFillsViewportHeight(false);
        scrollPane.setBounds(200, 0, 1080, 700);
        scrollPane.setPreferredSize(new Dimension(1080, 700));
    }

    public void generateLayeredPane(){
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(200, 0, 520, 480);
        generateManifestPanel();
        generateItemListPanel();
        generateSalesPanel();
        layeredPane.add(manifestPanel, new Integer(0), 0);
        layeredPane.add(itemListPanel, new Integer(1), 0);
        layeredPane.add(salesPanel, new Integer(2), 0);
    }

    public JButton generateButton(String text, int width, int height){
        JButton temp = new JButton(text);
        temp.addActionListener(this);
        temp.setSize(width, height);
        return temp;
    }
}
