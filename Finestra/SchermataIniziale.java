package Finestra;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SchermataIniziale extends JFrame implements ActionListener {

    Container container = getContentPane();
    ImageIcon icona = new ImageIcon("Immagini/logo_png_home.png");// !Sistemare i bordi dell'immagine
    JButton homeButton = new JButton();
    JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    Cursor cursoreHome = homeButton.getCursor();

    SchermataIniziale() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
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

    }

    public void addComponentsToContainer() {

        container.add(homeButton);
        container.add(nomeLabel);

    }

    public void addActionEvent() {

        // ActionListener per il bottone

        homeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone homeButton
        if (e.getSource() == homeButton) {
            setFrame(new HomePage());
            dispose();// Chiude il frame dopo l'apertura del secondo

        }

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
