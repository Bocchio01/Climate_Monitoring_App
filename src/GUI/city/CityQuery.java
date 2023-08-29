package src.GUI.city;

import javax.swing.*;

import src.GUI.Home;
import src.functions.CityFunctions;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;

import java.awt.*;
import java.awt.event.*;

public class CityQuery extends JFrame {

    private String windowsTitle = "Ricerca dati città";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JLabel labelQueryType = new JLabel();
    private JLabel labelCityName = new JLabel();
    private JLabel labelLatitude = new JLabel();
    private JLabel labelLongitude = new JLabel();
    private JTextField textfieldCityName = new JTextField();
    private JTextField textfieldLatitude = new JTextField();
    private JTextField textfieldLongitude = new JTextField();
    private JButton buttonPerfomQuery = new JButton();
    private JButton buttonToBack = new JButton();
    private JComboBox<String> comboboxQueryType = new JComboBox<String>();

    public CityQuery() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        comboboxQueryType.setSelectedIndex(0);
    }

    private void initializeComponents() {
        String[] comboboxValues = new String[] { "Cerca per nome", "Cerca per coordinate" };

        labelLogoImage.setIcon(new ImageIcon(AppConstants.Path.Assets.LOGO));

        labelQueryType.setText("Tipo di ricerca");
        comboboxQueryType.setModel(new DefaultComboBoxModel<>(comboboxValues));
        comboboxQueryType.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelCityName.setText("Città");
        labelCityName.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldCityName.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelLatitude.setText("Latitudine");
        labelLatitude.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldLatitude.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelLongitude.setText("Longitudine");
        labelLongitude.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldLongitude.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonPerfomQuery.setText("Cerca");
        buttonPerfomQuery.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPerfomQuery.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonToBack.setText("Indietro");
        buttonToBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToBack.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

    }

    private void createLayout() {
        setTitle(windowsTitle);

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
        panelMain.add(labelQueryType, gbc);
        panelMain.add(comboboxQueryType, gbc);

        panelMain.add(labelCityName, gbc);
        panelMain.add(textfieldCityName, gbc);

        panelMain.add(labelLatitude, gbc);
        panelMain.add(textfieldLatitude, gbc);

        panelMain.add(labelLongitude, gbc);
        panelMain.add(textfieldLongitude, gbc);

        panelMain.add(buttonPerfomQuery, gbc);
        panelMain.add(buttonToBack, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] {
                panelMain,
                labelQueryType,
                labelCityName,
                labelLatitude,
                labelLongitude
        });
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
            String citta = "";

            switch (comboboxQueryType.getSelectedIndex()) {
                case 0:
                    citta = textfieldCityName.getText();
                                //             JOptionPane.showMessageDialog(null,
                                // "Citta vuota!",
                                // "Dato mancante",
                                // JOptionPane.WARNING_MESSAGE);
                    if (CityFunctions.nameFind(citta, AppConstants.Path.Files.CITY_COORDS, 1)) {
                        dispose();
                        FrameHandler.setFrame(new CityVisualizer(citta.trim()));
                    } else {
                        JOptionPane.showMessageDialog(this, "Città non trovata.");
                    }
                    break;

                case 1:
                    citta = CityFunctions.coordFind(textfieldLatitude.getText(), textfieldLongitude.getText(),
                            AppConstants.Path.Files.CITY_COORDS, 1);
                    dispose();
                    FrameHandler.setFrame(new CityVisualizer(citta.trim()));
                    break;

                default:
                    break;
            }
        });

        buttonToBack.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new Home());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CityQuery cercaFrame = new CityQuery();
            cercaFrame.setSize(1200, 800);
            cercaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cercaFrame.setVisible(true);
            cercaFrame.setLocationRelativeTo(null);
        });
    }

}