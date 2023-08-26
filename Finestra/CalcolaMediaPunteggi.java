package Finestra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalcolaMediaPunteggi {
    public static void main(String[] args) {
        String nomeCittaDaCercare = "Como"; // Sostituire con il nome della città desiderata
        try {
            Map<String, MediaCommenti> mediaPunteggi = calcolaMediaPunteggiPerCitta(nomeCittaDaCercare);

            if (mediaPunteggi.isEmpty()) {
                System.out.println("Nome della città non trovato nel file.");
            } else {
                // Definisci l'ordine delle categorie
                String[] categorie = { "Vento", "Umidita'", "Pressione", "Temperatura", "Precipitazioni",
                        "Altitudine dei ghiacciai", "Massa dei ghiacciai" };

                for (String categoria : categorie) {
                    MediaCommenti mediaCommenti = mediaPunteggi.get(categoria);
                    if (mediaCommenti != null) {
                        Integer media = mediaCommenti.getMedia();
                        String commenti = mediaCommenti.getCommenti();

                        if (media != null) {
                            // Arrotonda la media ai punti più vicini tra 1 e 5
                            media = Math.round(media);
                        } else {
                            media = 0; // Se la categoria non è presente, imposta la media a 0
                        }

                        System.out.println(
                                "Categoria: " + categoria + ", Media Punteggi: " + media + ", Commenti: " + commenti);
                    } else {
                        System.out.println("Categoria: " + categoria + ", Media Punteggi: 0, Commenti: null");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, MediaCommenti> calcolaMediaPunteggiPerCitta(String nomeCitta) throws IOException {
        Map<String, Integer> mediaPunteggi = new LinkedHashMap<>(); // Usiamo LinkedHashMap per mantenere l'ordine
        Map<String, StringBuilder> commentiPerCategoria = new LinkedHashMap<>(); // Mappa per i commenti

        String filePath = "tabella2.csv"; // Sostituire con il percorso del tuo file CSV

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int count = 0;
        int tot = 1;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String nomeCittaNelFile = parts[0].trim();

            if (nomeCittaNelFile.equalsIgnoreCase(nomeCitta.trim())) {
                String categoria = parts[3].trim(); // Rimuovi spazi bianchi
                int punteggio = Integer.parseInt(parts[4].trim()); // Rimuovi spazi bianchi
                String commento = parts[5].trim(); // Rimuovi spazi bianchi

                if (mediaPunteggi.containsKey(categoria)) {
                    int mediaAttuale = mediaPunteggi.get(categoria);
                    mediaPunteggi.put(categoria, mediaAttuale + punteggio);
                } else {
                    mediaPunteggi.put(categoria, punteggio);
                }

                // Aggiungi il commento alla mappa dei commenti
                if (!commento.equalsIgnoreCase("0")) {
                    if (commentiPerCategoria.containsKey(categoria)) {
                        commentiPerCategoria.get(categoria).append(" | ").append(commento);
                    } else {
                        commentiPerCategoria.put(categoria, new StringBuilder(commento));
                    }
                }

                count++;
                if (count % 8 == 0) {
                    tot++;
                }
            }
        }

        reader.close();

        if (count == 0) {
            return new LinkedHashMap<>(); // Nessuna corrispondenza trovata, restituisci una mappa vuota
        }

        // Calcola la media dei punteggi per ogni categoria
        for (Map.Entry<String, Integer> entry : mediaPunteggi.entrySet()) {
            String categoria = entry.getKey();
            int media = Math.round(entry.getValue() / tot);
            mediaPunteggi.put(categoria, media);
        }

        // Crea una mappa finale che contiene la media e i commenti
        Map<String, MediaCommenti> mediaCommentiMap = new LinkedHashMap<>();
        for (String categoria : mediaPunteggi.keySet()) {
            Integer media = mediaPunteggi.get(categoria);
            StringBuilder commenti = commentiPerCategoria.get(categoria);
            System.out.println(commenti);
            mediaCommentiMap.put(categoria, new MediaCommenti(media, commenti.toString()));
        }

        return mediaCommentiMap;
    }
}

class MediaCommenti {
    private Integer media;
    private String commenti;

    public MediaCommenti(Integer media, String commenti) {
        this.media = media;
        this.commenti = commenti;
    }

    public Integer getMedia() {
        return media;
    }

    public String getCommenti() {
        return commenti != null ? commenti : "Nessun commento disponibile";
    }
}
