package Finestra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {

    MyFrame() throws IOException {
        // JPanel panel = new JPanel();
        // JButton accedi = new JButton("Accedi");
        // panel.add(accedi);
        // add(panel);

        // JFrame = una finestra a cui aggiungere componenti
        setTitle("Climate Monitoring");// imposta il titolo della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esce dall'applicazione
        setResizable(true);// previene il resize della finestra
        setSize(1000, 1000); // imposta le dimensioni (x,y) della finestra
        setLocationRelativeTo(null);
        setVisible(true); // rende visibile la finestra, alla fine delle opzioni
        BufferedImage logo = ImageIO.read(new File("./logo.png"));
        JLabel wlogo = new JLabel(new ImageIcon(logo));
        wlogo.setPreferredSize(new Dimension(20, 20));
        add(wlogo);

        ImageIcon image = new ImageIcon("logo.png"); // crea un'icona, quando Marta ha trovato il logo lo si mette qui
        setIconImage(image.getImage());// cambia l'icona della finestra
        getContentPane().setBackground(new Color(255, 255, 255)); // imposta il colore del background in variabili
                                                                  // RGB o esadecimali

    }

    public static void main(String[] args) throws IOException {
        new MyFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'actionPerformed'");
    // }

}
