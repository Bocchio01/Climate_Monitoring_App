package src.utils;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Theme {

    private static boolean darkMode = true;
    private static Color lightBlue = new Color(153, 255, 255);
    private static Color darkGray = new Color(49, 51, 56);

    public static boolean toggleTheme() {
        darkMode = !darkMode;
        return darkMode;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void applyTheme(Object[] guiElements) {
        for (Object guiElement : guiElements) {
            if (guiElement instanceof JPanel)
                applyThemeToPanel((JPanel) guiElement);
            else if (guiElement instanceof JLabel)
                applyThemeToLabel((JLabel) guiElement);
        }
    }

    public static void applyThemeToPanel(JPanel panel) {
        if (isDarkMode()) {
            panel.setBackground(darkGray);
        } else {
            panel.setBackground(lightBlue);
        }
    }

    public static void applyThemeToLabel(JLabel label) {
        if (isDarkMode()) {
            label.setForeground(Color.WHITE);
        } else {
            label.setForeground(Color.BLACK);
        }

    }
}
