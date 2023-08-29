package src.utils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameHandler {

    public static void setFrame(JFrame mainFrame) {
        ImageIcon iconImage = new ImageIcon(AppConstants.Path.IMG +  "/favicon.png");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setIconImage(iconImage.getImage());
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
