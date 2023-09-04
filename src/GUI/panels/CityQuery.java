package src.GUI.panels;

import javax.swing.*;

import src.GUI.GUI;
import src.GUI.templates.TwoColumns;
import src.GUI.templates.Widget;
import src.models.MainModel;
import src.models.data.DataQuery.QueryCondition;
import src.models.record.CityRecord;
import src.utils.Interfaces;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CityQuery extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "CityQuery";
    private GUI gui;
    private MainModel mainModel;

    private JTextField textfieldCityName = new JTextField();
    private JTextField textfieldLatitude = new JTextField();
    private JTextField textfieldLongitude = new JTextField();
    private JButton buttonPerfomQuery = new Widget.Button("Cerca dati città");
    private JComboBox<String> comboboxQueryType = new JComboBox<String>();

    public CityQuery(MainModel mainModel) {
        this.mainModel = mainModel;
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

            CityRecord[] result = null;
            List<QueryCondition> conditions = new ArrayList<>();

            switch (comboboxQueryType.getSelectedIndex()) {
                case 0:
                    String cityName = textfieldCityName.getText();

                    conditions.add(new QueryCondition("name", cityName));
                    result = mainModel.data.getCityBy(conditions);
                    break;

                case 1:
                    Double latitude = Double.parseDouble(textfieldLatitude.getText().replace(',', '.'));
                    Double longitude = Double.parseDouble(textfieldLongitude.getText().replace(',', '.'));

                    conditions.add(new QueryCondition("latitude", latitude));
                    conditions.add(new QueryCondition("longitude", longitude));
                    result = mainModel.data.getCityBy(conditions);
                    break;
            }

            if (result.length > 1) {
                CityRecord selectedCity = (CityRecord) JOptionPane.showInputDialog(
                        this,
                        "Sono state trovate più città con lo stesso nome. Seleziona quella desiderata.",
                        "Città trovate",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        result,
                        result[0]);
                if (selectedCity != null)
                    gui.goToPanel(CityVisualizer.ID, new Object[] { selectedCity.ID() });

            } else if (result.length == 1) {
                gui.goToPanel(CityVisualizer.ID, new Object[] { result[0].ID() });

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La città inserita non è presente nel database.",
                        "Città non trovata",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        comboboxQueryType.addActionListener(e -> {
            // textfieldCityName.setText("");
            // textfieldLatitude.setText("");
            // textfieldLongitude.setText("");

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
        addRight(new Widget.FormPanel(gui.appTheme, "Tipo di ricerca", comboboxQueryType));
        addRight(new Widget.FormPanel(gui.appTheme, "Città", textfieldCityName));
        addRight(new Widget.FormPanel(gui.appTheme, "Latitudine", textfieldLatitude));
        addRight(new Widget.FormPanel(gui.appTheme, "Longitudine", textfieldLongitude));
        addRight(buttonPerfomQuery);

        gui.appTheme.registerPanel(leftPanel);
        gui.appTheme.registerPanel(rightPanel);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        // comboboxQueryType.setSelectedIndex(0);
        // textfieldCityName.setText("");
        // textfieldLatitude.setText("");
        // textfieldLongitude.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            CityQuery cityQuery = new CityQuery(mainModel);

            gui.addPanel(cityQuery.createPanel(gui));
            cityQuery.onOpen(args);

            cityQuery.comboboxQueryType.setSelectedIndex(1);
            cityQuery.textfieldLatitude.setText("45,80819");
            cityQuery.textfieldLongitude.setText("9,0832");
            cityQuery.buttonPerfomQuery.doClick();

        });
    }

}