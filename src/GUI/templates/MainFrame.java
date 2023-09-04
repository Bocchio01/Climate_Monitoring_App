package src.GUI.templates;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import src.utils.ENV;

public class MainFrame extends JFrame {

    public MainFrame() {
        setSize(ENV.GUI.FRAME_WIDTH, ENV.GUI.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        setTitle(ENV.APP_TITLE);
        setIcon(ENV.Path.Assets.LOGO);
        setLayout(new BorderLayout());
    }

    private void setIcon(String iconPath) {
        ImageIcon iconImage = new ImageIcon();
        
        try {
            Image originalImage = ImageIO.read(Widget.class.getResource(iconPath));
            iconImage = new ImageIcon(originalImage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        setIconImage(iconImage.getImage());
    }
}
