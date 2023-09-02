package src.GUI.city;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import src.GUI.Home;
import src.models.MainModel;
import src.models.data.DataQuery.QueryCondition;
import src.models.record.CityRecord;
import src.models.record.WeatherRecord;
import src.utils.GUI;
import src.utils.Interfaces;
import src.utils.Theme;
import src.utils.Widget;

public class CityVisualizer extends JPanel implements Interfaces.UIPanel {

    public static String ID = "CityVisualizer";
    public GUI gui;

    private JTextArea textareaOperator = new JTextArea();
    private static JTextArea textareaDatas = new JTextArea();
    private JButton buttonToBack = new Widget.Button("Indietro");

    public CityVisualizer() {
    }

    private void initializeComponents() {
        textareaOperator.setEnabled(false);
        textareaDatas.setEnabled(false);
    }

    private void createLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(textareaOperator, gbc);
        add(textareaDatas, gbc);

        gbc.weighty = 1;
        add(buttonToBack, gbc);

    }

    private void addActionEvent() {

        buttonToBack.addActionListener(e -> {
            gui.goToPanel(CityQuery.ID, null);

        });
    }

    public void loadDatas(Integer cityID) {

        QueryCondition condition = new QueryCondition("cityID", cityID);
        WeatherRecord[] weatherRecords = gui.mainModel.dataHandler.dataQuery.getWeatherBy(condition);
        
        if (weatherRecords.length > 0) {
            for (WeatherRecord weatherRecord : weatherRecords) {
                textareaDatas.append(weatherRecord.toString() + "\n");
            }

            CityRecord cityRecord = gui.mainModel.dataHandler.dataQuery.getCityBy(cityID);
            textareaOperator.setText(cityRecord.toString());

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

        initializeComponents();
        createLayout();
        Theme.registerPanel(this);
        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        textareaOperator.setText("");
        textareaDatas.setText("");

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
            CityVisualizer cityVisualizer = new CityVisualizer();

            gui.addPanel(cityVisualizer.createPanel(gui));
            cityVisualizer.onOpen(args);
        });
    }
}
