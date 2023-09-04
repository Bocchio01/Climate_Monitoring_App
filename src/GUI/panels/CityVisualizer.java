package src.GUI.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.GUI.GUI;
import src.GUI.Widget;
import src.models.MainModel;
import src.models.data.DataQuery.QueryCondition;
import src.models.logic.LogicCity.WeatherTableData;
import src.models.record.RecordCity;
import src.models.record.RecordWeather;
import src.utils.Interfaces;

public class CityVisualizer extends JPanel implements Interfaces.UIPanel {

    public static String ID = "CityVisualizer";
    private GUI gui;
    private MainModel mainModel;

    private JTextField textfieldCityName = new JTextField();
    private JTextField textfieldCountryName = new JTextField();
    private JTextField textfieldLatitude = new JTextField();
    private JTextField textfieldLongitude = new JTextField();
    private JTable table = new JTable();
    private DefaultTableModel defaulmodelTable = new DefaultTableModel();
    private JButton buttonToBack = new Widget.Button("Indietro");

    private static String[] tableCategory = {
            "Vento",
            "Umidità",
            "Pressione",
            "Temperatura",
            "Precipitazioni",
            "Altitudine dei ghiacciai",
            "Massa dei ghiacciai" };

    public CityVisualizer(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private void addActionEvent() {

        buttonToBack.addActionListener(e -> {
            gui.goToPanel(CityQuery.ID, null);
        });
    }

    public void loadDatas(Integer cityID) {

        RecordCity RecordCity = mainModel.data.getCityBy(cityID);
        textfieldCityName.setText(RecordCity.name());
        textfieldCountryName.setText(RecordCity.countryName());
        textfieldLatitude.setText(String.valueOf(RecordCity.latitude()));
        textfieldLongitude.setText(String.valueOf(RecordCity.longitude()));

        QueryCondition condition = new QueryCondition("cityID", cityID);
        RecordWeather[] weatherRecords = mainModel.data.getWeatherBy(condition);

        if (weatherRecords.length > 0) {

            Integer row = 0;
            WeatherTableData weatherTableData = new WeatherTableData(weatherRecords);

            for (String keyString : WeatherTableData.keys) {

                Float avgScore = weatherTableData.getCategoryAvgScore(keyString);
                Integer recordCount = weatherTableData.getCategoryRecordCount(keyString);
                String comment = String.join(" | ", weatherTableData.getCategoryComments(keyString));

                if (avgScore != null) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    defaulmodelTable.setValueAt(decimalFormat.format(avgScore), row, 1);
                } else {
                    defaulmodelTable.setValueAt("N/A", row, 1);
                }
                defaulmodelTable.setValueAt(recordCount, row, 2);
                defaulmodelTable.setValueAt(comment, row, 3);

                row++;
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "L'operatore non ha ancora inserito dati per la città selezionata.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE);
            gui.goToPanel(CityQuery.ID, null);
        }
    }

    @Override
    public CityVisualizer createPanel(GUI gui) {
        this.gui = gui;

        textfieldCityName.setEditable(false);
        textfieldCountryName.setEditable(false);
        textfieldLatitude.setEditable(false);
        textfieldLongitude.setEditable(false);

        defaulmodelTable.addColumn("Categoria");
        defaulmodelTable.addColumn("Punteggio");
        defaulmodelTable.addColumn("Numero campionamenti");
        defaulmodelTable.addColumn("Commento");

        for (String columnName : tableCategory) {
            defaulmodelTable.addRow(new Object[] { columnName, "/", "0", "" });
        }

        table.setModel(defaulmodelTable);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(40);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Nome città", textfieldCityName));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Nome nazione", textfieldCountryName));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Latitudine", textfieldLatitude));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Longitudine", textfieldLongitude));

        add(topPanel, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(buttonToBack, BorderLayout.SOUTH);

        gui.appTheme.registerPanel(topPanel);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        if (args != null && args.length > 0) {
            loadDatas((Integer) args[0]);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Errore nell'apertura della pagina.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            gui.goToPanel(Home.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            CityVisualizer cityVisualizer = new CityVisualizer(mainModel);

            gui.addPanel(cityVisualizer.createPanel(gui));
            // cityVisualizer.onOpen(args);
            cityVisualizer.onOpen(new Object[] { 3178229 });
        });
    }
}
