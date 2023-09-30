package GUI.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import GUI.GUI;
import GUI.Widget;
import GUI.layouts.TwoColumns;
import models.CurrentOperator;
import models.MainModel;
import models.data.DataQuery.QueryCondition;
import models.record.RecordCity;
import utils.ENV;
import utils.Interfaces;

public class AreaCreateNew extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "AreaCreateNew";
    private GUI gui;
    private MainModel mainModel;

    private JTextField textfieldAreaName = new JTextField();
    private JTextField textfieldStreetName = new JTextField();
    private JTextField textfieldStreetNumber = new JTextField();
    private JTextField textfieldCAP = new JTextField();
    private JTextField textfieldTownName = new JTextField();
    private JTextField textfieldDistrictName = new JTextField();
    private JTextField textfieldCityName = new JTextField();
    // private JButton buttonAddCityName = new Widget.Button("Aggiungi la città
    // all'area");
    private JButton buttonPerformInit = new Widget.Button("Crea l'area");

    private DefaultListModel<String> listmodelCityIDs = new DefaultListModel<>();
    private JList<String> listCityIDs = new JList<>(listmodelCityIDs);
    private JScrollPane scrollpaneCityInfo = new JScrollPane();

    public AreaCreateNew(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private void addActionEvent() {

        textfieldCityName.addActionListener(e -> {

            String cityName = textfieldCityName.getText().trim();

            if (!cityName.isEmpty() && !listmodelCityIDs.contains(cityName)) {

                RecordCity[] result = null;
                List<QueryCondition> conditions = new ArrayList<>();

                conditions.add(new QueryCondition("name", cityName));
                result = mainModel.data.getCityBy(conditions);

                if (result.length > 1) {
                    RecordCity selectedCity = (RecordCity) JOptionPane.showInputDialog(
                            this,
                            "Sono state trovate più città con lo stesso nome. Seleziona quella desiderata.",
                            "Città trovate",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            result,
                            result[0]);
                    if (selectedCity != null) {
                        listmodelCityIDs.addElement(selectedCity.toString());
                        textfieldCityName.setText("");
                    }

                } else if (result.length == 1) {
                    listmodelCityIDs.addElement(result[0].toString());
                    textfieldCityName.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "La città inserita non è presente nel database.",
                            "Città non trovata",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        listCityIDs.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = listCityIDs.getSelectedIndex();

                if (selectedIndex >= 0) {
                    Integer answer = JOptionPane.showConfirmDialog(
                            this,
                            "Vuoi rimuovere la città selezionata?",
                            "Rimuovi città",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (answer == JOptionPane.YES_OPTION) {
                        listmodelCityIDs.remove(selectedIndex);
                    }
                }
            }
        });

        buttonPerformInit.addActionListener(e -> {

            String areaName = textfieldAreaName.getText().trim();
            String streetName = textfieldStreetName.getText().trim();
            String streetNumber = textfieldStreetNumber.getText().trim();
            String CAP = textfieldCAP.getText().trim();
            String townName = textfieldTownName.getText().trim();
            String districtName = textfieldDistrictName.getText().trim();
            Integer[] cityIDs = new Integer[listmodelCityIDs.size()];

            for (int i = 0; i < listmodelCityIDs.size(); i++) {
                cityIDs[i] = Integer.parseInt(listmodelCityIDs.get(i).split(ENV.CSV_SEPARATOR)[0]);
            }

            try {
                mainModel.logicArea.initNewArea(areaName,
                        streetName,
                        streetNumber,
                        CAP,
                        townName,
                        districtName,
                        cityIDs);
                JOptionPane.showMessageDialog(
                        this,
                        "Nuova area inserita correttamente.",
                        "Nuova area inserita",
                        JOptionPane.INFORMATION_MESSAGE);
                gui.goToPanel(AreaAddData.ID, null);
                
            } catch (Exception exception) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage(),
                        "Dato errato",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    };

    @Override
    public AreaCreateNew createPanel(GUI gui) {
        this.gui = gui;

        scrollpaneCityInfo.setViewportView(listCityIDs);
        scrollpaneCityInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpaneCityInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpaneCityInfo.setPreferredSize(ENV.GUI.WIDGET_DIMENSION);

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel(gui.appTheme, "Nome dell'area", textfieldAreaName));
        addRight(new Widget.FormPanel(gui.appTheme, "Nome della via", textfieldStreetName));
        addRight(new Widget.FormPanel(gui.appTheme, "Numero civico", textfieldStreetNumber));
        addRight(new Widget.FormPanel(gui.appTheme, "CAP", textfieldCAP));
        addRight(new Widget.FormPanel(gui.appTheme, "Nome del comune", textfieldTownName));
        addRight(new Widget.FormPanel(gui.appTheme, "Sigla della provincia", textfieldDistrictName));
        addRight(new Widget.FormPanel(gui.appTheme, "Nomi delle città nell'area", textfieldCityName));
        addRight(scrollpaneCityInfo);
        // addRight(buttonAddCityName);
        addRight(buttonPerformInit);

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
        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (!currentOperator.isUserLogged()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Per creare una nuova area devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);

            gui.goToPanel(OperatorLogin.ID, null);

        } else if (currentOperator.getCurrentOperator().areaID() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hai già creato la tua area.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);

            gui.goToPanel(AreaAddData.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            AreaCreateNew areaCreateNew = new AreaCreateNew(mainModel);

            mainModel.logicOperator.performLogin(ENV.DefaultData.ID, ENV.DefaultData.PWD);
            gui.addPanel(areaCreateNew.createPanel(gui));
            areaCreateNew.onOpen(args);

            areaCreateNew.textfieldAreaName.setText("Area di Bocchio");
            areaCreateNew.textfieldStreetName.setText("Via Montagnola");
            areaCreateNew.textfieldStreetNumber.setText("13");
            areaCreateNew.textfieldCAP.setText("22040");
            areaCreateNew.textfieldTownName.setText("San Fermo della Battaglia");
            areaCreateNew.textfieldDistrictName.setText("CO");

        });
    }

}
