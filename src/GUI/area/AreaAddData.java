package src.GUI.area;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

import src.GUI.operator.OperatorHome;
import src.functions.AreaFunctions;
import src.functions.OperatorFunctions;
import src.utils.AppConstants;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Limite caratteri a 256 per i commenti

public class AreaAddData extends JFrame {

    private static String windowsTitle = "Nuovo set di dati";
    private static String datePattern = "dd/MM/yyyy";
    private static String dateMask = datePattern.replaceAll("[dMy]", "#");

    private JPanel panelMain = new JPanel();
    private JTextField textfieldAreaName = new JTextField();
    private JComboBox<String> comboboxCityName = new JComboBox<>();
    private JFormattedTextField textfieldDate = new JFormattedTextField();
    private JTable table = new JTable();
    private JScrollPane scrollpanelTable = new JScrollPane();
    private DefaultTableModel defaulmodelTable = new DefaultTableModel();
    private JButton buttonPerformSave = Widget.createButton("Salva il nuovo set di dati");

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
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        if (OperatorFunctions.isUserLogged()) {
            String nameArea = OperatorFunctions.getCurrentUserArea();

            if (nameArea != null) {
                Integer[] cityIDs = AreaFunctions.getCityIDInArea(nameArea);

                for (Integer cityID : cityIDs) {
                    String cityName = AreaFunctions.getCityName(cityID);
                    comboboxCityName.addItem(cityName);
                }

                textfieldAreaName.setText(nameArea);
                textfieldAreaName.setEditable(false);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Per inserire un nuovo set di dati devi prima aver creato la tua area.",
                        "Area non creata",
                        JOptionPane.ERROR_MESSAGE);
                // PanelHandler.setFrame(new AreaCreateNew());
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Per inserire un nuovo set di dati devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);
            // PanelHandler.setFrame(new OperatorHome());
        }

    }

    private void initializeComponents() {

        try {
            MaskFormatter maskFormatter = new MaskFormatter(dateMask);
            maskFormatter.install(textfieldDate);
        } catch (Exception e) {
            // TODO: handle exception
        }

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

        // table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollpanelTable.setViewportView(table);
        // scrollpanelTable.setPreferredSize(new Dimension(1000, 500));

    }

    private void createLayout() {
        setTitle(windowsTitle);

        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        panelMain.add(Widget.createFormPanel("Area operatore", textfieldAreaName), gbc);
        panelMain.add(Widget.createFormPanel("Città selezionata", comboboxCityName), gbc);
        panelMain.add(Widget.createFormPanel("Data rilevazione", textfieldDate), gbc);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 3;
        panelMain.add(scrollpanelTable, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        panelMain.add(buttonPerformSave, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
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
                String scoreCell = (String) defaulmodelTable.getValueAt(i, 1);
                String commentCell = (String) defaulmodelTable.getValueAt(i, 2);

                tableData[i] = new Object[] {
                        !scoreCell.isEmpty() ? scoreCell : AppConstants.EMPTY_STRING,
                        !commentCell.isEmpty() ? commentCell.trim() : AppConstants.EMPTY_STRING
                };
            }

            for (int i = 0; i < tableData.length; i++) {
                if (tableData[i][0] != null) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorFunctions.performLogin(AppConstants.DefaultData.ID, AppConstants.DefaultData.PWD);

            AreaAddData areaAddDataFrame = new AreaAddData();
            areaAddDataFrame.setSize(1200, 800);
            areaAddDataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            areaAddDataFrame.setVisible(true);
            areaAddDataFrame.setLocationRelativeTo(null);

            areaAddDataFrame.defaulmodelTable.setValueAt("1", 0, 1);
            areaAddDataFrame.defaulmodelTable.setValueAt("Commento1", 0, 2);
            areaAddDataFrame.defaulmodelTable.setValueAt("3", 3, 1);
            areaAddDataFrame.defaulmodelTable.setValueAt("Commento3", 3, 2);
            areaAddDataFrame.defaulmodelTable.setValueAt("4", 4, 1);
            areaAddDataFrame.defaulmodelTable.setValueAt("Commento4", 4, 2);
        });
    }
}
