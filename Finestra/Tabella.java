package Finestra;

import java.awt.Color;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Tabella extends JFrame {

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
            FileReader f = new FileReader("OperatoriRegistrati.dati.csv");
            BufferedReader b = new BufferedReader(f);

            // Intestazione area
            testo = b.readLine();
            idArea = new JTextArea(testo);
            idArea.setEditable(false);
            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int offset = testo.length() * 7 / 2;

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

        JPanel container = new JPanel();
        container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
        datiArea.setEditable(false);
        scrollPane = new JScrollPane(datiArea);

        scrollPane.setBounds(50, 180, 700, 250);
        homeButton.setBounds(635, 500, 30, 30);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        idArea.setBounds(400 - offset, 100, 500, 50);

        container.add(scrollPane);
        container.add(homeButton);
        container.add(indietroButton);
        container.add(idArea);
        add(container);

    }

    // public static Map<String, MediaCommenti> calcolaMediaPunteggiPerCitta(String
    // nomeCitta) throws IOException {

    // Map<String, Integer> mediaPunteggi = new LinkedHashMap<>();// Uso
    // LinkedHashMap per mantenere l'ordine
    // Map<String, StringBuilder> commentiPerCategoria = new LinkedHashMap<>();//
    // Mappa per i commenti

    // String filePath = "DatiRegistrati.csv";

    // BufferedReader reader = new BufferedReader(new FileReader(filePath));
    // String line;
    // int count = 0, tot = 1;

    // while ((line = reader.readLine()) != null) {
    // String[] parts = line.split(",");
    // String nomeCittaNelFile = parts[0].trim();

    // if (nomeCittaNelFile.equalsIgnoreCase(nomeCitta.trim())) {
    // String categoria = parts[3].trim(); // rimozione spazi bianchi
    // int punteggio = Integer.parseInt(parts[4].trim());
    // String commento = parts[5].trim();

    // if (mediaPunteggi.containsKey(categoria)) {
    // int mediaAttuale = mediaPunteggi.get(categoria);
    // mediaPunteggi.put(categoria, mediaAttuale + punteggio);
    // } else {
    // mediaPunteggi.put(categoria, punteggio);
    // }

    // // Aggiunta dei commenti alla mappa dei commenti, vedere con cosa sostituire
    // la
    // // stringa vuota
    // if (!commento.equalsIgnoreCase("0")) {
    // if (commentiPerCategoria.containsKey(categoria)) {
    // commentiPerCategoria.get(categoria).append(" | " + commento);
    // } else {
    // commentiPerCategoria.put(categoria, new StringBuilder(commento));
    // }
    // }

    // count++;
    // if (count % 8 == 0) {
    // tot++;
    // }
    // }
    // }

    // reader.close();

    // if (count == 0) {
    // return new LinkedHashMap<>();// Nessuna corrispondenza trovata, restituisci
    // una mappa vuota
    // }
    // // Calcola la media dei punteggi per ogni categoria
    // for (Map.Entry<String, Integer> entry : mediaPunteggi.entrySet()) {
    // String categoria = entry.getKey();
    // int media = Math.round(entry.getValue() / tot);
    // mediaPunteggi.put(categoria, media);
    // }
    // // Crea una mappa finale che contiene la media e i commenti
    // Map<String, MediaCommenti> mediaCommentiMap = new LinkedHashMap<>();
    // for (String categoria : mediaPunteggi.keySet()) {
    // Integer media = mediaPunteggi.get(categoria);
    // StringBuilder commenti = commentiPerCategoria.get(categoria);
    // mediaCommentiMap.put(categoria, new MediaCommenti(media,
    // commenti.toString()));
    // }

    // return mediaCommentiMap;

    // }

    public static Map<String, MediaCommenti> calculateAverageScoresByCategory(String cityName) throws IOException {

        Map<String, Integer> averageScores = new LinkedHashMap<>();
        Map<String, StringBuilder> commentsByCategory = new LinkedHashMap<>();
        String filePath = "DatiRegistrati.csv";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int count = 0, totalRecords = 1;

        while ((line = reader.readLine()) != null) {
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
}
