package Finestra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Finestra {
    public static void main(String[] args) throws IOException {

        // JLabel= un'area per fare vedere una stringa di testo/immagine

        Border border = BorderFactory.createLineBorder(Color.green, 3);// crea un bordo attorno alla label

        JLabel label = new JLabel(); // crea una label
        label.setText("testo etichetta");// imposta il teso dell'etichetta

        // label.setIcon(image); // imposta un'immagine nella finestra
        label.setHorizontalTextPosition(JLabel.CENTER);// imposta il testo LEFT,CENTER,RIGHT rispetto l'immagine
        label.setVerticalTextPosition(JLabel.TOP);// imposta il testo TOP,CENTER,BOTTOM rispetto l'immagine
        label.setForeground(new Color(0x00FF00));// imposta il colore del testo
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));// imposta il font del testo
        label.setIconTextGap(50);// imposta un gap tra il testo e l'immagine
        label.setBackground(Color.black); // imposta il background della label
        label.setOpaque(true);// mostra il background della label
        label.setBorder(border);// imposto il bordo
        label.setVerticalAlignment((JLabel.CENTER));// imposta l'allineamento verticale di immagine+testo entro la label
        label.setHorizontalAlignment(JLabel.CENTER);// imposta l'allineamento orizzontale di immagine+testo entro la
                                                    // label
        label.setBounds(100, 100, 250, 250);// imposta le posizioni x,y e le dimensioni della label nella finestra
        MyFrame frame = new MyFrame(); // crea una finestra

        frame.add(label);
        frame.setLayout(null);
        // frame.pack();// togliendo il setLayout e setBounds crea la finestra fittando
        // la label e va messo dopo .add

    }
}