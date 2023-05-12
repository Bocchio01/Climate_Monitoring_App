package Finestra;

import javax.swing.*;
import java.awt.*;

public class Schermo1 extends JFrame {

    Container container = getContentPane();
    ImageIcon icona = new ImageIcon("Immagini/logo_png_home.png");// !Sistemare i bordi dell'immagine
    JButton homeButton = new JButton();
    JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    Cursor cursoreHome = homeButton.getCursor();
    JProgressBar progressBar = new JProgressBar(0, 100); // Valore minimo 0, valore massimo 100
    Timer timer;

    Schermo1() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        timer = new Timer(5, e -> {
            int progress = progressBar.getValue();
            if (progress < 100) {
                progressBar.setValue(progress + 1);
            } else {
                timer.stop();
                dispose();
                setFrame(new HomePage());
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
        homeButton.setBounds(150, 50, 420, 400);
        homeButton.setIcon(icona);
        homeButton.setBorder(null);
        homeButton.setContentAreaFilled(false);
        nomeLabel.setBounds(210, 470, 405, 55);
        nomeLabel.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        progressBar.setBounds(280, 520, 200, 20);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Mostra il testo sull'indicatore
    }

    public void addComponentsToContainer() {
        container.add(homeButton);
        container.add(nomeLabel);
        container.add(progressBar);
    }

    private void setFrame(HomePage e) {

        e.setTitle("Home Page");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }
}
