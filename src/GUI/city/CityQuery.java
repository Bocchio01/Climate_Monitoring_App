package src.GUI.city;

import javax.swing.*;

import src.logics.CityFunctions;
import src.models.MainModel;
import src.models.data.DataQuery.QueryCondition;
import src.models.record.CityRecord;
import src.utils.GUI;
import src.utils.Interfaces;
import src.utils.Widget;
import src.utils.templates.TwoColumns;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CityQuery extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "CityQuery";
    private GUI gui;

    private JTextField textfieldCityName = new JTextField();
    private JTextField textfieldLatitude = new JTextField();
    private JTextField textfieldLongitude = new JTextField();
    private JButton buttonPerfomQuery = new Widget.Button("Cerca dati città");
    private JComboBox<String> comboboxQueryType = new JComboBox<String>();

    public CityQuery() {
    }

    public void addActionEvent() {
        KeyListener enterKeyListenerCityName = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonPerfomQuery.doClick();
                }
            }
        };

        KeyListener enterKeyListenerCoordinates = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String latitude = textfieldLatitude.getText();
                    String longitude = textfieldLongitude.getText();

                    if (!latitude.isEmpty() && !longitude.isEmpty()) {
                        buttonPerfomQuery.doClick();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Completa prima tutti i campi relativi alle coordinate",
                                "Dato mancante",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        };

        buttonPerfomQuery.addActionListener(e -> {

            switch (comboboxQueryType.getSelectedIndex()) {
                case 0:
                    String cityName = textfieldCityName.getText();

                    List<QueryCondition> conditions = new ArrayList<>();
                    conditions.add(new QueryCondition("name", cityName));

                    CityRecord[] result = gui.mainModel.dataHandler.dataQuery.getCityBy(conditions);
                    if (result.length == 1) {
                        gui.goToPanel(CityVisualizer.ID, new Object[] { result[0].ID() });
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "La città inserita non è presente nel database.",
                                "Città non trovata",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case 1:
                    Integer cityID = CityFunctions.getCityIDByCoords(
                            textfieldLatitude.getText(),
                            textfieldLongitude.getText());

                    if (cityID != null) {
                        gui.goToPanel(CityVisualizer.ID, new Object[] { cityID });
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Le coordinate inserite non hanno portato ad alcuna città nel database.",
                                "Città non trovata",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                default:
                    break;
            }
        });

        comboboxQueryType.addActionListener(e -> {
            textfieldCityName.setText("");
            textfieldLatitude.setText("");
            textfieldLongitude.setText("");

            if (comboboxQueryType.getSelectedIndex() == 0) {
                textfieldCityName.setEnabled(true);
                textfieldLatitude.setEnabled(false);
                textfieldLongitude.setEnabled(false);
            } else {
                textfieldCityName.setEnabled(false);
                textfieldLatitude.setEnabled(true);
                textfieldLongitude.setEnabled(true);
            }
        });

        textfieldCityName.addKeyListener(enterKeyListenerCityName);
        textfieldLatitude.addKeyListener(enterKeyListenerCoordinates);
        textfieldLongitude.addKeyListener(enterKeyListenerCoordinates);

    }

    @Override
    public CityQuery createPanel(GUI gui) {
        this.gui = gui;

        String[] comboboxValues = new String[] { "Cerca per nome", "Cerca per coordinate" };
        comboboxQueryType.setModel(new DefaultComboBoxModel<>(comboboxValues));

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel("Tipo di ricerca", comboboxQueryType));
        addRight(new Widget.FormPanel("Città", textfieldCityName));
        addRight(new Widget.FormPanel("Latitudine", textfieldLatitude));
        addRight(new Widget.FormPanel("Longitudine", textfieldLongitude));
        addRight(buttonPerfomQuery);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        comboboxQueryType.setSelectedIndex(0);
        textfieldCityName.setText("");
        textfieldLatitude.setText("");
        textfieldLongitude.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            CityQuery cityQuery = new CityQuery();

            gui.addPanel(cityQuery.createPanel(gui));
            cityQuery.onOpen(args);
        });
    }

}