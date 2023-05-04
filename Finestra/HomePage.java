package Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

class HomePage extends JFrame implements ActionListener {

    // Oggetti da inserire nel frame

    Container container = getContentPane();
    JButton areaOpButton = new JButton("Area Operatore");
    JButton ospiteButton = new JButton("Dove vuoi andare?");
    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    Cursor cursoreOp = areaOpButton.getCursor();
    Cursor cursoreOpsite = ospiteButton.getCursor();

    HomePage() {

        // Formazione del frame+componenti

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {

        // Set info Container

        container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        // Set posizioni degli oggetti nel frame

        ospiteButton.setBounds(300, 270, 200, 30);
        logo.setBounds(290, 50, 200, 186);
        areaOpButton.setBounds(300, 320, 200, 30);

        ospiteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        areaOpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(ospiteButton);
        container.add(logo);
        container.add(areaOpButton);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        ospiteButton.addActionListener(this);
        areaOpButton.addActionListener(this);

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
        if (e.getSource() == ospiteButton)

        {
            // Apre la finestra per la ricerca della zona
            JOptionPane.showMessageDialog(this, "Finstra cerca/viasualizza area");

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

}
