package Finestra;

import javax.swing.*;
import java.awt.*;

public class SchermataIniziale extends JFrame {

    Container container = getContentPane();
    JLabel icona = new JLabel(new ImageIcon("Immagini/logo_png_home.png"));// !Sistemare i bordi dell'immagine
    JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    JProgressBar progressBar = new JProgressBar(0, 100); // Valore minimo 0, valore massimo 100
    Timer timer;

    SchermataIniziale() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        timer = new Timer(35, e -> {
            int progress = progressBar.getValue();
            if (progress < 100) {
                progressBar.setValue(progress + 1);
            } else {
                timer.stop();
                dispose();
                SetFrameFunc.setFrame(new HomePage());
            }
        });

        timer.start();
    }

    public void setLayoutManager() {
        // Set info Container
        container.setBackground(new Color(224, 251, 255));
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        // !Centrare i compnenti
        icona.setBounds(150, 50, 420, 400);
        nomeLabel.setBounds(210, 470, 405, 55);
        nomeLabel.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));
        progressBar.setBounds(280, 520, 200, 20);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Mostra il testo sull'indicatore
    }

    public void addComponentsToContainer() {
        container.add(icona);
        container.add(nomeLabel);
        container.add(progressBar);
    }

}
