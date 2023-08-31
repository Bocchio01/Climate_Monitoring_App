package src.GUI.area;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

import src.GUI.operator.OperatorHome;
import src.functions.AreaFunctions;
import src.functions.OperatorFunctions;
import src.utils.ENV;
import src.utils.GUIHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AreaAddData extends JPanel implements GUIHandler.Panel {

    public static String ID = "AreaAddData";
    public GUIHandler panelHandler;

    private static String datePattern = "dd/MM/yyyy";
    private static String dateMask = datePattern.replaceAll("[dMy]", "#");

    private JTextField textfieldAreaName = new JTextField();
    private JComboBox<String> comboboxCityName = new JComboBox<>();
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

    public AreaAddData() {
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
                    textfieldDate.setText(getCurrentDateString());
                    textfieldDate.setForeground(Color.GRAY);
                    return;
                }

                if (!AreaFunctions.isDateValid(dateString, datePattern)) {
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

            boolean allRowsNull = true;

            String insertedDate = textfieldDate.getText(),
                    nameArea = textfieldAreaName.getText(),
                    cityName = (String) comboboxCityName.getSelectedItem();

            Object[][] tableData = new Object[defaulmodelTable.getRowCount()][defaulmodelTable.getColumnCount() - 1];

            for (int i = 0; i < defaulmodelTable.getRowCount(); i++) {
                String scoreCell = defaulmodelTable.getValueAt(i, 1) != null
                        ? defaulmodelTable.getValueAt(i, 1).toString()
                        : "";
                String commentCell = defaulmodelTable.getValueAt(i, 2).toString();

                tableData[i] = new Object[] {
                        !scoreCell.isEmpty() ? scoreCell : ENV.EMPTY_STRING,
                        !commentCell.isEmpty() ? commentCell.trim() : ENV.EMPTY_STRING
                };
            }

            for (int i = 0; i < tableData.length; i++) {
                if (tableData[i][0] != ENV.EMPTY_STRING) {
                    allRowsNull = false;
                    break;
                }
            }

            if (allRowsNull) {
                JOptionPane.showMessageDialog(this,
                        "Inserisci almeno un punteggio nella tabella.",
                        "Valore errato",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (AreaFunctions.saveScoresToFile(AreaFunctions.getCityID(cityName), nameArea, insertedDate, tableData))
                JOptionPane.showMessageDialog(this,
                        "Dati salvati con successo!",
                        "Salvataggio dati",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this,
                        "Errore nella scrittura dei dati!",
                        "Errore nel salvataggio dati",
                        JOptionPane.ERROR_MESSAGE);
        });

    }

    public static String getCurrentDateString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        return currentDate.format(formatter);
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
    public AreaAddData createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        try {
            MaskFormatter maskFormatter = new MaskFormatter(dateMask);
            maskFormatter.install(textfieldDate);
        } catch (Exception e) {
            // TODO: handle exception
        }

        textfieldAreaName.setEnabled(false);
        textfieldAreaName.setEditable(false);

        textfieldDate.setText(getCurrentDateString());
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
        topPanel.add(new Widget.FormPanel("Area operatore", textfieldAreaName));
        topPanel.add(new Widget.FormPanel("Città selezionata", comboboxCityName));
        topPanel.add(new Widget.FormPanel("Data rilevazione", textfieldDate));
        // topPanel.add(new JSeparator());

        add(topPanel, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(buttonPerformSave, BorderLayout.SOUTH);

        Theme.registerPanel(topPanel);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        if (OperatorFunctions.isUserLogged()) {
            String nameArea = OperatorFunctions.getCurrentUserArea();

            if (nameArea != null) {
                Integer[] cityIDs = AreaFunctions.getCityIDInArea(nameArea);

                for (Integer cityID : cityIDs) {
                    String cityName = AreaFunctions.getCityName(cityID);
                    comboboxCityName.addItem(cityName);
                }

                textfieldAreaName.setText(nameArea);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Per inserire un nuovo set di dati devi prima aver creato la tua area.",
                        "Area non creata",
                        JOptionPane.ERROR_MESSAGE);
                panelHandler.goToPanel(AreaCreateNew.ID, args);
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Per inserire un nuovo set di dati devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);
            panelHandler.goToPanel(OperatorHome.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            OperatorFunctions.performLogin(ENV.DefaultData.ID, ENV.DefaultData.PWD);

            GUIHandler panelHandler = new GUIHandler();
            AreaAddData areaAddData = new AreaAddData();

            panelHandler.addPanel(areaAddData.createPanel(panelHandler));
            areaAddData.onOpen(args);
        });
    }
}
