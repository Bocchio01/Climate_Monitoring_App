package GUI;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constants;

public class Widget {

    public static class FormPanel extends JPanel {

        public FormPanel(Theme appTheme, String labelText, JComponent activeArea) {
            super(new GridBagLayout());

            JLabel label = new JLabel(labelText);
            activeArea.setPreferredSize(Constants.GUI.WIDGET_DIMENSION);
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.insets = new Insets(5, 5, 2, 5);
            add(label, gbc);

            gbc.gridy = 1;
            gbc.insets = new Insets(0, 5, 5, 5);
            add(activeArea, gbc);

            appTheme.registerPanel(this);
            appTheme.registerLabel(label);
        }

    }

    public static class Button extends JButton {

        public Button(String text) {
            setText(text);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setPreferredSize(Constants.GUI.WIDGET_DIMENSION);
        }
    }

    public static class LogoLabel extends JLabel {

        public LogoLabel() {
            this(2);
        }
    
        public LogoLabel(double scale) {
            this((int) (200 * scale), (int) (186 * scale));
        }
    
        public LogoLabel(int width, int height) {
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
    
            try {
                BufferedImage originalImage = ImageIO.read(getClass().getResource(Constants.Path.Assets.LOGO));
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    
                ImageIcon icon = new ImageIcon(scaledImage);
                setIcon(icon);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ComboItem {
        private String label;
        private int value;
    
        public ComboItem(String label, int value) {
            this.label = label;
            this.value = value;
        }
    
        public int getValue() {
            return value;
        }
    
        @Override
        public String toString() {
            return label;
        }
    }
}
