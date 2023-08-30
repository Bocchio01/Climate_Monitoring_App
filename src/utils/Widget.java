package src.utils;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Widget {

    public static class FormElement {
        public String label;
        public JComponent component;

        public FormElement(String label, JComponent component) {
            this.label = label;
            this.component = component;
        }

        public String getValue() {
            if (component instanceof JTextField) {
                String value = ((JTextField) component).getText().trim();
                return value.isEmpty() ? null : value;
            }
            if (component instanceof JPasswordField) {
                return new String(((JPasswordField) component).getPassword());
            }
            return null;
        }

        public void setText(String text) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(text);
            }
        }
    }

    public static JPanel createFormPanel(String labelText, JComponent activeArea) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel(labelText);

        activeArea.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 2, 5);
        panel.add(label, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        panel.add(activeArea, gbc);

        Theme.applyThemeToPanel(panel);
        Theme.applyThemeToLabel(label);

        return panel;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        return button;
    }

    public static JLabel createLogoLabel() {
        return createLogoLabel(1);
    }

    public static JLabel createLogoLabel(int scale) {
        return createLogoLabel(200 * scale, 186 * scale);
    }

    public static JLabel createLogoLabel(int width, int height) {
        JLabel label = new JLabel();

        try {
            BufferedImage originalImage = ImageIO.read(Widget.class.getResource(AppConstants.Path.Assets.LOGO));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(scaledImage);
            label.setIcon(icon);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return label;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(createLogoLabel(), gbc);
        panel.add(createButton("Button text"), gbc);
        panel.add(createFormPanel("Label", new JTextField("Text field")), gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
