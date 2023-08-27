package Finestra.frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Finestra.functions.CercaFunc;
import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

import javax.swing.*;

public class Tabella extends JFrame implements ActionListener {

    private JScrollPane scrollPane;
    private JButton indietroButton, homeButton;
    private Cursor cursoreInd, cursoreHome;
    private ImageIcon icona;
    private JTextArea idArea;
    private JTextArea datiArea = new JTextArea();

    public Tabella(String nomeCitta) {

        indietroButton = new JButton("Indietro");
        icona = new ImageIcon("Immagini/icona_home.png");
        homeButton = new JButton(icona);
        cursoreInd = indietroButton.getCursor();
        cursoreHome = homeButton.getCursor();

        String testo = "";
        try {
            FileReader fin = new FileReader("OperatoriRegistrati.dati.csv");
            BufferedReader fbuffer = new BufferedReader(fin);

            // Intestazione area
            testo = fbuffer.readLine();
            idArea = new JTextArea(testo);
            idArea.setEditable(false);
            fbuffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int offset = testo.length() * 7 / 2;

        if (CercaFunc.nameFind(nomeCitta, "DatiRegistrati.csv", 0)) {
            // Città popolata da operatore

            try {
                Map<String, MediaCommenti> mediaPunteggi = calculateAverageScoresByCategory(nomeCitta);// passato nel
                // costruttore

                if (mediaPunteggi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Non sono ancora presenti dati per questo luogo");

                } else {
                    // Ordine delle categorie
                    String[] categorie = { "Vento", "Umidita'", "Pressione", "Temperatura", "Precipitazioni",
                            "Altitudine dei ghiacciai", "Massa dei ghiacciai" };

                    // Dichiarazione di un StringBuilder per accumulare i dati
                    StringBuilder datiBuilder = new StringBuilder();

                    for (String categoria : categorie) {
                        MediaCommenti mediaCommenti = mediaPunteggi.get(categoria);
                        if (mediaCommenti != null) {
                            Integer media = mediaCommenti.getMedia();
                            String commenti = mediaCommenti.getComment();

                            if (media != null) {
                                // arrotonda la media ai punti più vicini tra 1 e 5
                                media = Math.round(media);
                            } else {
                                media = 0;// se la categoria non è presente imposta la media a 0
                            }

                            // Accumula i dati nel StringBuilder
                            datiBuilder.append("Categoria: ").append(categoria)
                                    .append(", Media Punteggi: ").append(media)
                                    .append(", Commenti: ").append(commenti)
                                    .append("\n"); // Aggiungi un salto di riga per separare le voci
                        } else {
                            JOptionPane.showMessageDialog(this, "Non sono ancora presenti dati per questo luogo");
                        }
                    }
                    // Imposta il testo accumulato nella datiArea
                    datiArea.setText(datiBuilder.toString());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "L'operatore non ha ancora inserito dati per la città selezionata.");

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    SetFrameFunc.setFrame(new Cerca());
                }
            });

            timer.setRepeats(false);
            timer.start();
        }

        JPanel container = new JPanel();
        Theme.applyThemeToContainer(container);

        datiArea.setEditable(false);
        scrollPane = new JScrollPane(datiArea);

        scrollPane.setBounds(50, 180, 700, 250);
        homeButton.setBounds(635, 500, 30, 30);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        idArea.setBounds(400 - offset, 100, 500, 50);

        indietroButton.addActionListener((ActionListener) this);
        homeButton.addActionListener((ActionListener) this);

        container.add(scrollPane);
        container.add(homeButton);
        container.add(indietroButton);
        container.add(idArea);
        add(container);

    }

    public static Map<String, MediaCommenti> calculateAverageScoresByCategory(String cityName) throws IOException {

        Map<String, Integer> averageScores = new LinkedHashMap<>();
        Map<String, StringBuilder> commentsByCategory = new LinkedHashMap<>();
        String filePath = "DatiRegistrati.csv";

        BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
        String line;
        int count = 0, totalRecords = 1;

        while ((line = reader.readLine()) != null) {
            line = line.replace("\uFEFF", "");
            String[] parts = line.split(",");
            String cityInFile = parts[0].trim();

            if (cityInFile.equalsIgnoreCase(cityName.trim())) {
                String category = parts[3].trim();
                int score = Integer.parseInt(parts[4].trim());
                String comment = parts[5].trim();

                averageScores.merge(category, score, Integer::sum);

                if (!comment.equalsIgnoreCase("0")) {
                    commentsByCategory.computeIfAbsent(category, k -> new StringBuilder()).append(" | " + comment);
                }

                count++;
                if (count % 8 == 0) {
                    totalRecords++;
                }
            }
        }

        reader.close();

        if (averageScores.isEmpty()) {
            return new LinkedHashMap<>();
        }

        // Calculate the average scores for each category
        for (Map.Entry<String, Integer> entry : averageScores.entrySet()) {
            String category = entry.getKey();
            int average = Math.round(entry.getValue() / totalRecords);
            averageScores.put(category, average);
        }

        // Create a final map containing the averages and comments
        Map<String, MediaCommenti> mediaCommentMap = new LinkedHashMap<>();
        for (String category : averageScores.keySet()) {
            Integer average = averageScores.get(category);
            StringBuilder comments = commentsByCategory.get(category);
            mediaCommentMap.put(category, new MediaCommenti(average, comments.toString()));
        }

        return mediaCommentMap;
    }

    public static class MediaCommenti {
        private Integer media;
        private String commenti;

        public MediaCommenti(Integer media, String commenti) {
            this.media = media;
            this.commenti = commenti;
        }

        public Integer getMedia() {
            return media;
        }

        public String getComment() {
            return commenti != null ? commenti : "Nessun commento disponibile";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            SetFrameFunc.setFrame(new Cerca());

        }
        if (e.getSource() == homeButton) {

            dispose();
            SetFrameFunc.setFrame(new HomePage());

        }

    }

}
