package src.GUI.city;

import javax.swing.*;

import src.GUI.Home;
import src.functions.AreaFunctions;
import src.functions.CityFunctions;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;
import java.awt.event.*;

public class CityQuery extends JPanel implements PanelHandler.PanelCreator {

    public static String windowsTitle = "Ricerca dati città";
    public static String ID = "CityQuery";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JTextField textfieldCityName = new JTextField();
    private JTextField textfieldLatitude = new JTextField();
    private JTextField textfieldLongitude = new JTextField();
    private JButton buttonPerfomQuery = Widget.createButton("Cerca dati città");
    private JButton buttonToBack = Widget.createButton("Indietro");
    private JComboBox<String> comboboxQueryType = new JComboBox<String>();

    private Object[][] formData = {
            { "Nome città", textfieldCityName },
            { "Latitudine", textfieldLatitude },
            { "Longitudine", textfieldLongitude }
    };

    public CityQuery() {

        // comboboxQueryType.setSelectedIndex(0);
    }

    private void initializeComponents() {
        String[] comboboxValues = new String[] { "Cerca per nome", "Cerca per coordinate" };
        comboboxQueryType.setModel(new DefaultComboBoxModel<>(comboboxValues));
    }

    private void createLayout() {
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(labelLogoImage, gbc);

        gbc.weighty = 1;
        panelMain.add(Widget.createFormPanel("Tipo di ricerca", comboboxQueryType), gbc);
        panelMain.add(Widget.createFormPanel("Città", textfieldCityName), gbc);
        panelMain.add(Widget.createFormPanel("Latitudine", textfieldLatitude), gbc);
        panelMain.add(Widget.createFormPanel("Longitudine", textfieldLongitude), gbc);

        panelMain.add(buttonPerfomQuery, gbc);
        panelMain.add(buttonToBack, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
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
            String cityName = "";
            Integer cityID = 0;

            switch (comboboxQueryType.getSelectedIndex()) {
                case 0:
                    cityName = textfieldCityName.getText();
                    cityID = AreaFunctions.getCityID(cityName);

                    if (cityID != null) {
                        PanelHandler.changePanel(CityVisualizer.ID);
                        CityVisualizer.loadDatas(cityID);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "La città inserita non è presente nel database.",
                                "Città non trovata",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case 1:
                    cityID = CityFunctions.getCityIDByCoords(
                            textfieldLatitude.getText(),
                            textfieldLongitude.getText());

                    if (cityID != null) {
                        PanelHandler.changePanel(CityVisualizer.ID);
                        CityVisualizer.loadDatas(cityID);
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

        buttonToBack.addActionListener(e -> {
            PanelHandler.changePanel(Home.ID);
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
    public JPanel createPanel() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        });
    }

}