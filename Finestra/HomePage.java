package Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

class HomePage extends JFrame implements ActionListener {

    // Oggetti da inserire nel frame

    JPanel container = new JPanel();
    JButton areaOpButton = new JButton("Area Operatore");
    JButton ospiteButton = new JButton("Dove vuoi andare?");
    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    Cursor cursoreOp = areaOpButton.getCursor();
    Cursor cursoreOpsite = ospiteButton.getCursor();
    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    ImageIcon sun_moon = new ImageIcon("Immagini/tema.png");
    JButton theme = new JButton(sun_moon);

    HomePage() {

        // Formazione del frame+componenti

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {

        // Set info Container

        if (Theme.tema()) {
            container.setBackground(new Color(153, 255, 255));
        } else {
            container.setBackground(new Color(49, 51, 56));
        }

        container.setLayout(null);
    }

    public void setLocationAndSize() {

        // Set posizioni degli oggetti nel frame

        ospiteButton.setBounds(300, 270, 200, 30);
        logo.setBounds(290, 50, 200, 186);
        areaOpButton.setBounds(300, 320, 200, 30);

        ospiteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        areaOpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        theme.setBounds(20, 20, 30, 30);
        theme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(ospiteButton);
        container.add(logo);
        container.add(areaOpButton);
        container.add(indietroButton);
        container.add(homeButton);

        container.add(theme);

        add(container);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        ospiteButton.addActionListener(this);
        areaOpButton.addActionListener(this);
        indietroButton.addActionListener(this);
        homeButton.addActionListener(this);

        theme.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone area operatore
        if (e.getSource() == areaOpButton) {
            // Apre la finestra per l'accesso
            this.setVisible(false);
            setFrame(new AreaOperatore());

        }

        // Bottone Ospite
        if (e.getSource() == ospiteButton) {

            dispose();
            setFrame(new Cerca());
        }

        // Bottone Indietro (inutile farlo due volte A.)
        if (e.getSource() == indietroButton) {

            dispose();
            setFrame(new SchermataIniziale());
        }

        // Bottone Home
        if (e.getSource() == homeButton) {

            dispose();
            setFrame(new SchermataIniziale());

        }

        // Bottone tema
        if (e.getSource() == theme) {
            setLayoutManager();
        }

    }

    private void setFrame(AreaOperatore e) {

        e.setTitle("Area Operatore");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

    private void setFrame(Cerca e) {

        e.setTitle("Cerca");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

}
