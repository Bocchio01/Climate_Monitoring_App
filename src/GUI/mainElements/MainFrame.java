package GUI.mainElements;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import GUI.Widget;
import utils.Constants;

public class MainFrame extends JFrame {

    public MainFrame() {
        setSize(Constants.GUI.FRAME_WIDTH, Constants.GUI.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        setTitle(Constants.APP_TITLE);
        setIcon(Constants.Path.Assets.LOGO);
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
