package Finestra;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    public Data() {

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
        scrollPaneTable.setBounds(45, 120, 600, 303);

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
        commento = new JTextArea("         Commenti");
        commento.setEditable(false);
        commento.setBackground(new Color(239, 238, 238));
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

        Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
        commento.setBorder(border);

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

        barAlt.setBounds(644, 140, 120, 41);
        barVento.setBounds(644, 180, 120, 41);
        barUmidita.setBounds(644, 220, 120, 41);
        barTemp.setBounds(644, 260, 120, 41);
        barPres.setBounds(644, 300, 120, 41);
        barPrec.setBounds(644, 340, 120, 41);
        barGhiac.setBounds(644, 380, 120, 42);
        commento.setBounds(644, 120, 120, 20);
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

        add(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salvaButton) {
            salvaFile();
        }
    }

    private void salvaFile() {
        // Creazione del file e scrittura nel file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tabella.txt", true));
            for (int i = 0; i < model.getRowCount(); i++) {
                String row = model.getValueAt(i, 0) + "," + model.getValueAt(i, 2) + "," + commenti[i].getText().trim();
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
