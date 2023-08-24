package Finestra;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

//import org.w3c.dom.events.MouseEvent;
//import java.awt.event.MouseAdapter;

import java.text.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Data extends JFrame implements ActionListener {

    private JTable table;
    private JScrollPane scrollPaneTable;
    private DefaultTableModel model;
    private JButton salvaButton;
    private JTextArea commentoVento;
    private JScrollPane barVento;
    private JTextArea commentoUmidita;
    private JScrollPane barUmidita;
    private JTextArea commentoPres;
    private JScrollPane barPres;
    private JTextArea commentoTemp;
    private JScrollPane barTemp;
    private JTextArea commentoPrec;
    private JScrollPane barPrec;
    private JTextArea commentoAlt;
    private JScrollPane barAlt;
    private JTextArea commentoGhiac;
    private JScrollPane barGhiac;
    private JTextArea commento;
    private JTextArea[] commenti;
    private JLabel area;
    private JLabel nomeCentro;
    private JFormattedTextField dateTextField;
    private String dateToString;
    private DateFormat dateFormat;
    private long milliseconds;
    private String cleanDate;

    public Data(String s, String n) {

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####"); // Defining the date format

            area = new JLabel();
            nomeCentro = new JLabel();
            dateTextField = new JFormattedTextField(dateMask);
            milliseconds = System.currentTimeMillis();
            Date date = new Date(milliseconds);
            dateFormat = new SimpleDateFormat("ddMMyyyy");
            dateToString = dateFormat.format(date);
            dateTextField.setText(dateToString);

            dateTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    dateTextField.setText("");
                    dateTextField.setForeground(Color.BLACK); // Cambia il colore del testo quando prende il focus
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (dateTextField.getText().equals("  /  /    ")) {
                        dateTextField.setText(dateToString);
                        dateTextField.setForeground(Color.GRAY); // Ripristina il colore del testo quando perde il focus
                    }
                }
            });

            this.area.setText(s);
            this.nomeCentro.setText(n);

            Object[][] data = {
                    { "Vento", "Velocità del vento (km/h)", 1 },
                    { "Umidita'", "% di Umidità", 1 },
                    { "Pressione", "In hPa", 1 },
                    { "Temperatura", "In C°", 1 },
                    { "Precipitazioni", "In mm di pioggia", 1 },
                    { "Altitudine dei ghiacciai", "In m", 1 },
                    { "Massa dei ghiacciai", "In kg", 1 }
            };

            String[] columnNames = { "Categoria climatica", "Spiegazione", "Punteggio" };

            // Creazione del modello della tabella con 7 righe e 3 colonne
            model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Rendi modificabili solo l'ultima colonna (indice 2)
                    return column == 2;
                }
            };

            // Creazione della tabella con il modello creato
            table = new JTable(model);
            salvaButton = new JButton("Salva");
            // Timer per togliere il focus da cella data
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvaButton.requestFocusInWindow();
                }
            });
            timer.setRepeats(false); // Esegui il timer solo una volta
            timer.start();

            // Impostazione del renderer delle celle per centrare il testo
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            // Impostazione delle dimensioni delle colonne per impedire all'utente di
            // modificarle

            table.getTableHeader().setResizingAllowed(false);
            table.getTableHeader().setReorderingAllowed(false);

            // Creazione del pannello contenitore e dello scroll pane per la tabella
            JPanel container = new JPanel();
            container.setBackground(new Color(153, 255, 255));
            container.setLayout(null);
            scrollPaneTable = new JScrollPane(table);

            // Modifica x,y per spostare gli oggetti
            scrollPaneTable.setBounds(25, 120, 600, 303);

            salvaButton.setBounds(340, 440, 100, 30);

            int columnWidth = 200; // Larghezza desiderata delle colonne (esempio: 100 pixel)
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setMinWidth(columnWidth);
                table.getColumnModel().getColumn(i).setMaxWidth(columnWidth);
            }

            // Creazione del renderer delle celle personalizzato per impostare la grandezza
            // del carattere
            TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Impostazione della grandezza del carattere per le celle
                    c.setFont(new Font("Arial", Font.PLAIN, 14)); // Esempio: Arial, stile normale, dimensione 14
                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);

                    table.setRowHeight(row, 40);

                    return c;
                }
            };

            // Applica il renderer personalizzato a tutte le colonne della tabella
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
            }

            commentoAlt = new JTextArea();
            commentoGhiac = new JTextArea();
            commentoPrec = new JTextArea();
            commentoPres = new JTextArea();
            commentoTemp = new JTextArea();
            commentoUmidita = new JTextArea();
            commentoVento = new JTextArea();
            JPanel commento = new JPanel();
            commento.setLayout(new BorderLayout());
            commento.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel label = new JLabel("Commenti");
            Font arialFont = new Font("Arial", Font.PLAIN, 13);
            label.setFont(arialFont);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            commento.add(label);
            /*
             * commento = new JTextArea("             Commenti");
             * commento.setEditable(false);
             * commento.setBackground(new Color(239, 238, 238));
             */

            commenti = new JTextArea[7];

            commentoAlt.setLineWrap(true);
            commentoGhiac.setLineWrap(true);
            commentoPrec.setLineWrap(true);
            commentoPres.setLineWrap(true);
            commentoTemp.setLineWrap(true);
            commentoUmidita.setLineWrap(true);
            commentoVento.setLineWrap(true);

            commentoAlt.setWrapStyleWord(true);
            commentoGhiac.setWrapStyleWord(true);
            commentoPrec.setWrapStyleWord(true);
            commentoPres.setWrapStyleWord(true);
            commentoTemp.setWrapStyleWord(true);
            commentoUmidita.setWrapStyleWord(true);
            commentoVento.setWrapStyleWord(true);

            // Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
            // commento.setBorder(border);

            commenti[0] = commentoVento;
            commenti[1] = commentoUmidita;
            commenti[2] = commentoPres;
            commenti[3] = commentoTemp;
            commenti[4] = commentoPrec;
            commenti[5] = commentoAlt;
            commenti[6] = commentoGhiac;

            int limite = 256;
            for (int i = 0; i < commenti.length; i++) {
                JTextArea commentoArea = commenti[i];
                AbstractDocument doc = (AbstractDocument) commentoArea.getDocument();
                doc.setDocumentFilter(new CharacterLimitFilter(limite));
            }

            barAlt = new JScrollPane(commentoAlt);
            barGhiac = new JScrollPane(commentoGhiac);
            barPrec = new JScrollPane(commentoPrec);
            barPres = new JScrollPane(commentoPres);
            barTemp = new JScrollPane(commentoTemp);
            barUmidita = new JScrollPane(commentoUmidita);
            barVento = new JScrollPane(commentoVento);

            barVento.setBounds(624, 140, 140, 42);
            barUmidita.setBounds(624, 180, 140, 42);
            barPres.setBounds(624, 220, 140, 42);
            barTemp.setBounds(624, 260, 140, 42);
            barPrec.setBounds(624, 300, 140, 42);
            barAlt.setBounds(624, 340, 140, 42);
            barGhiac.setBounds(624, 380, 140, 42);
            commento.setBounds(623, 120, 140, 21);

            nomeCentro.setBounds(150, 50, 125, 30);
            area.setBounds(380, 50, 125, 30);
            dateTextField.setBounds(550, 55, 125, 25);

            // Azione bottone Salva
            salvaButton.addActionListener(this);

            // Aggiunta della tabella allo scroll pane e dello scroll pane al pannello
            container.add(scrollPaneTable);
            container.add(barAlt);
            container.add(barGhiac);
            container.add(barPrec);
            container.add(barPres);
            container.add(barTemp);
            container.add(barUmidita);
            container.add(barVento);
            container.add(salvaButton);
            container.add(commento);
            container.add(area);
            container.add(nomeCentro);
            container.add(dateTextField);

            add(container);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salvaButton) {
            salvaFile();
        }
    }

    private void salvaFile() {
        String data = dateTextField.getText();

        if (!isValidDate(data)) {
            JOptionPane.showMessageDialog(this, "Data non valida");
        } else {
            // Creazione del file e scrittura nel file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("tabella.txt", true));

                writer.write(area.getText() + "," + nomeCentro.getText() + "," + dateTextField.getText());
                writer.newLine();

                for (int i = 0; i < model.getRowCount(); i++) {
                    String row = model.getValueAt(i, 0) + "," + model.getValueAt(i, 2) + ","
                            + commenti[i].getText().trim();
                    writer.write(row);
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Dati salvati con successo!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Errore nella scrittura dei dati!");
            }
        }
    }

    private boolean isValidDate(String date) {
        // Verifica che la data abbia il formato "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }

        // Estrai i componenti della data
        String[] components = date.split("/");
        int day = Integer.parseInt(components[0]);
        int month = Integer.parseInt(components[1]);
        int year = Integer.parseInt(components[2]);

        LocalDate currentDate = LocalDate.now();

        // Verifica i limiti per giorno, mese e anno
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        // Verifica se la data è nel passato
        LocalDate inputDate = LocalDate.of(year, month, day);
        if (inputDate.isAfter(currentDate)) {
            return false;
        }

        return true;
    }

}
