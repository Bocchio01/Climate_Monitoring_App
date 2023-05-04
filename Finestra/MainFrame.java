package Finestra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

    static JFrame frame1;
    JFrame frame2;

    Container container1 = getContentPane();
    Container container2 = getContentPane();
    Container container3 = getContentPane();

    private ImageIcon icona = new ImageIcon("Immagini/logo_png_home.png");// !Sistemare i bordi dell'immagine
    private JButton homeButton = new JButton(icona);
    private JLabel nomeLabel = new JLabel("Monitoraggio Climatico");
    private Cursor cursoreHome = homeButton.getCursor();
    private JButton areaOpButton = new JButton("Area Operatore");
    private JButton ospiteButton = new JButton("Dove vuoi andare?");
    private JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    private Cursor cursoreOp = areaOpButton.getCursor();
    private Cursor cursoreOpsite = ospiteButton.getCursor();

    public void setLayoutManager(Container c) {

        // Set info container
        c.setBackground(new Color(153, 255, 255));
        c.setLayout(null);
    }

    public MainFrame() {

        setLayoutManager(container1);
        setLayoutManager(container2);
        setLayoutManager(container3);

        // Schermata Iniziale
        frame1 = new JFrame();

        nomeLabel.setBounds(210, 470, 405, 55);
        nomeLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));

        homeButton.setBounds(150, 50, 500, 400);
        homeButton.setBorder(null);
        homeButton.setContentAreaFilled(false);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.setFrame(frame2, "HomePage");
            }
        }));

        container1.add(homeButton);
        container1.add(nomeLabel);
        frame1.add(container1);

        // Home Page
        frame2 = new JFrame();

        ospiteButton.setBounds(300, 270, 200, 30);
        ospiteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        areaOpButton.setBounds(300, 320, 200, 30);
        areaOpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logo.setBounds(290, 50, 200, 186);

        container2.add(ospiteButton);
        container2.add(areaOpButton);
        container2.add(logo);
        frame2.add(container2);

    }

    private static void setFrame(JFrame e, String nome) {

        e.setTitle(nome);
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);

    }

    public static void main(String[] args) {
        setFrame(new MainFrame(), "Schermata iniziale");
    }
}
