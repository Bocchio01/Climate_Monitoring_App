package Finestra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NuovaFinestra extends JFrame {

    NuovaFinestra() throws IOException {

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Climate Monitoring");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("logo_png.png");
        setIconImage(logo.getImage());

        JLabel imageHolder = new JLabel();
        imageHolder.setIcon(makeImageIcon("logo_png_sfocato.png"));

        JPanel panel = new JPanel();
        panel.add(imageHolder);
        panel.setMaximumSize(new Dimension(800, 600));

        add(panel);
        setVisible(true);

        // BufferedImage logo_in = ImageIO.read(new File("logo_png.png"));
        // JLabel wlogo = new JLabel(new ImageIcon(logo_in));
        // wlogo.setPreferredSize((new Dimension(100, 100)));
        // add(wlogo);

    }

    public static ImageIcon makeImageIcon(String filename) {
        BufferedImage immagine = null;
        try {
            immagine = ImageIO.read(new File(filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(immagine);
    }

    public static void main(String[] args) throws IOException {
        new NuovaFinestra();
    }

}
