package Finestra;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

class FrameSettings extends JFrame {

    FrameSettings() {

    }

    public static void setFrame(JFrame e) {
        e.setTitle("Schermata Iniziale");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);

    }

}
