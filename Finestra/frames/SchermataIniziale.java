package Finestra.frames;

import javax.swing.*;

import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

import java.awt.*;

public class SchermataIniziale extends JFrame {

    JPanel container = new JPanel();
    JLabel icona = new JLabel(new ImageIcon("Immagini/logo_png_home.png"));// !Sistemare i bordi dell'immagine
    JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    JProgressBar progressBar = new JProgressBar(0, 100); // Valore minimo 0, valore massimo 100
    Timer timer;

    public SchermataIniziale() {

        setLocationAndSize();
        addComponentsToContainer();
        Theme.applyThemeToContainer(container);
        Theme.applyThemeToLabel(nomeLabel);

        timer = new Timer(0, e -> {
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

    public void setLocationAndSize() {
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

        add(container);
    }

}
