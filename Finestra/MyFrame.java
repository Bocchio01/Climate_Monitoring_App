package Finestra;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

    MyFrame() {
        // JFrame = una finestra a cui aggiungere componenti
        setTitle("Qui va inserito il titolo della finestra");// imposta il titolo della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esce dall'applicazione
        setResizable(true);// previene il resize della finestra
        setSize(500, 500); // imposta le dimensioni (x,y) della finestra
        setVisible(true); // rende visibile la finestra, alla fine delle opzioni

        ImageIcon image = new ImageIcon("filename"); // crea un'icona
        setIconImage(image.getImage());// cambia l'icona della finestra
        getContentPane().setBackground(new Color(123, 50, 250)); // imposta il colore del background in variabili
                                                                 // RGB o esadecimali

    }

}
