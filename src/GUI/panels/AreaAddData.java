package GUI.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

import GUI.GUI;
import GUI.Widget;
import GUI.Widget.ComboItem;
import models.CurrentOperator;
import models.MainModel;
import models.record.RecordArea;
import models.record.RecordCity;
import utils.ENV;
import utils.Functions;
import utils.Interfaces;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AreaAddData extends JPanel implements Interfaces.UIPanel {

    public static String ID = "AreaAddData";
    private GUI gui;
    private MainModel mainModel;

    private static String datePattern = "dd/MM/yyyy";
    private static String dateMask = datePattern.replaceAll("[dMy]", "#");

    private JTextField textfieldAreaName = new JTextField();
    private JComboBox<ComboItem> comboboxCityName = new JComboBox<>();
    private JFormattedTextField textfieldDate = new JFormattedTextField();
    private JTable table = new JTable();
    private DefaultTableModel defaulmodelTable = new DefaultTableModel();
    private JButton buttonPerformSave = new Widget.Button("Salva il nuovo set di dati");

    private static String[][] data = {
            { "Vento", "Velocità del vento (km/h)" },
            { "Umidita'", "% di Umidità" },
            { "Pressione", "In hPa" },
            { "Temperatura", "In C°" },
            { "Precipitazioni", "In mm di pioggia" },
            { "Altitudine dei ghiacciai", "In m" },
            { "Massa dei ghiacciai", "In kg" }
    };

    public AreaAddData(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private void addActionEvent() {

        textfieldDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // textfieldDate.setText("");
                textfieldDate.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String dateString = textfieldDate.getText();

                if (dateString.equals(dateMask.replace("#", " "))) {
                    textfieldDate.setText(Functions.getCurrentDateString());
                    textfieldDate.setForeground(Color.GRAY);
                    return;
                }

                if (!Functions.isDateValid(dateString)) {
                    JOptionPane.showMessageDialog(null,
                            "La data inserita non è corretta.",
                            "Data errata",
                            JOptionPane.ERROR_MESSAGE);
                    textfieldDate.setForeground(Color.RED);
                } else {
                    textfieldDate.setForeground(Color.BLACK);
                }
            }
        });

        buttonPerformSave.addActionListener(e -> {

            Integer cityID = ((ComboItem) comboboxCityName.getSelectedItem()).getValue();
            String date = textfieldDate.getText();

            Object[][] tableData = new Object[defaulmodelTable.getRowCount()][defaulmodelTable.getColumnCount() - 1];

            for (int i = 0; i < defaulmodelTable.getRowCount(); i++) {
                String scoreCell = defaulmodelTable.getValueAt(i, 1) != null
                        ? defaulmodelTable.getValueAt(i, 1).toString()
                        : "";
                String commentCell = defaulmodelTable.getValueAt(i, 2).toString();

                tableData[i] = new Object[] {
                        !scoreCell.isEmpty() ? Integer.parseInt(scoreCell) : null,
                        !commentCell.isEmpty() ? commentCell.trim() : ENV.EMPTY_STRING
                };
            }

            try {
                mainModel.logicArea.addDataToArea(
                        cityID,
                        date,
                        tableData);
                JOptionPane.showMessageDialog(this,
                        "Dati salvati con successo!",
                        "Salvataggio dati",
                        JOptionPane.INFORMATION_MESSAGE);
                // gui.goToPanel(OperatorHome.ID, null);

            } catch (Exception exception) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(this,
                        "Errore nella scrittura dei dati!",
                        "Errore nel salvataggio dati",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public static class IntegerCellEditor extends DefaultCellEditor {

        private static final JComboBox<String> comboBox = new JComboBox<>(new String[] { "", "1", "2", "3", "4", "5" });

        public IntegerCellEditor() {
            super(comboBox);
        }

        @Override
        public Integer getCellEditorValue() {
            try {
                String value = comboBox.getSelectedItem().toString();
                return Integer.parseInt(value);
            } catch (Exception e) {
                // TODO: handle exception
                return null;
            }
        }
    }

    static class TooltipCellRenderer extends JTextArea implements TableCellRenderer {

        public TooltipCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            setText(value != null ? value.toString() : "");
            setToolTipText(value != null ? value.toString() : "");

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }

    @Override
    public AreaAddData createPanel(GUI gui) {
        this.gui = gui;

        try {
            MaskFormatter maskFormatter = new MaskFormatter(dateMask);
            maskFormatter.install(textfieldDate);
        } catch (Exception e) {
            // TODO: handle exception
        }

        textfieldAreaName.setEnabled(false);
        textfieldAreaName.setEditable(false);

        textfieldDate.setText(Functions.getCurrentDateString());
        textfieldDate.setForeground(Color.GRAY);

        defaulmodelTable.addColumn("Categoria");
        defaulmodelTable.addColumn("Punteggio");
        defaulmodelTable.addColumn("Commento");

        for (String[] rowInitData : data) {
            defaulmodelTable.addRow(new Object[] { rowInitData[0], "", "" });
        }

        table.setModel(defaulmodelTable);
        table.getColumnModel().getColumn(1).setCellEditor(new IntegerCellEditor());
        table.getColumnModel().getColumn(2).setCellRenderer(new TooltipCellRenderer());

        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(40);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Area operatore", textfieldAreaName));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Città selezionata", comboboxCityName));
        topPanel.add(new Widget.FormPanel(gui.appTheme, "Data rilevazione", textfieldDate));

        add(topPanel, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(buttonPerformSave, BorderLayout.SOUTH);

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
        comboboxCityName.removeAllItems();
        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (currentOperator.isUserLogged()) {
            Integer areaID = currentOperator.getCurrentOperator().areaID();

            RecordArea area = mainModel.data.getAreaBy(areaID);

            if (area != null) {

                Integer[] cityIDs = area.cityIDs();

                for (Integer cityID : cityIDs) {
                    RecordCity city = mainModel.data.getCityBy(cityID);
                    if (city != null) {
                        comboboxCityName.addItem(new ComboItem(city.name(), city.ID()));
                    }
                }

                textfieldAreaName.setText(area.areaName());

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Per inserire un nuovo set di dati devi prima aver creato la tua area.",
                        "Area non creata",
                        JOptionPane.ERROR_MESSAGE);
                gui.goToPanel(AreaCreateNew.ID, args);
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Per inserire un nuovo set di dati devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);
            gui.goToPanel(OperatorHome.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            AreaAddData areaAddData = new AreaAddData(mainModel);

            mainModel.logicOperator.performLogin(ENV.DefaultData.ID, ENV.DefaultData.PWD);

            gui.addPanel(areaAddData.createPanel(gui));
            areaAddData.onOpen(args);
        });
    }
}
