package Finestra.frames;

import javax.swing.*;

import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    // Oggetti da inserire nel frame

    JPanel container = new JPanel();
    JButton areaOpButton = new JButton("Area Operatore");
    JButton ospiteButton = new JButton("Dove vuoi andare?");
    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    Cursor cursoreOp = areaOpButton.getCursor();
    Cursor cursoreOpsite = ospiteButton.getCursor();

    ImageIcon sun_moon = new ImageIcon("Immagini/tema.png");
    JButton theme = new JButton(sun_moon);

    public HomePage() {

        // Formazione del frame+componenti
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);

    }

    public void setLocationAndSize() {

        // Set posizioni degli oggetti nel frame

        ospiteButton.setBounds(300, 270, 200, 30);
        logo.setBounds(290, 50, 200, 186);
        areaOpButton.setBounds(300, 320, 200, 30);

        ospiteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        areaOpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        theme.setBounds(20, 20, 30, 30);
        theme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(ospiteButton);
        container.add(logo);
        container.add(areaOpButton);

        container.add(theme);

        add(container);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        ospiteButton.addActionListener(this);
        areaOpButton.addActionListener(this);

        theme.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone area operatore
        if (e.getSource() == areaOpButton) {
            // Apre la finestra per l'accesso
            this.setVisible(false);
            SetFrameFunc.setFrame(new AreaOperatore());

        }

        // Bottone Ospite
        if (e.getSource() == ospiteButton) {

            dispose();
            SetFrameFunc.setFrame(new Cerca());
        }

        // Bottone tema
        if (e.getSource() == theme) {
            Theme.toggleTheme();
            Theme.applyThemeToContainer(container);
        }

    }

}
