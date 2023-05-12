package Finestra;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SchermataIniziale extends JFrame {

    Container container = getContentPane();
    JLabel icona = new JLabel(new ImageIcon("Immagini/logo_png_home.png"));// !Sistemare i bordi dell'immagine
    JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    Timer timer = new Timer();

    SchermataIniziale() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        TimerTask task = new TimerTask() {
            public void run() {
                dispose();
                setFrame(new HomePage()); // chiamata al metodo setFrame() dopo 3 secondi
            }
        };

        timer.schedule(task, 2000); // programma l'esecuzione del task dopo 3 secondi

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

    }

    public void addComponentsToContainer() {

        container.add(icona);
        container.add(nomeLabel);

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
