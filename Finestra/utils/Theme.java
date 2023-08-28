package Finestra.utils;

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
                applyThemeToContainer((JPanel) guiElement);
            else if (guiElement instanceof JLabel)
                applyThemeToLabel((JLabel) guiElement);
        }
    }

    public static void applyThemeToContainer(JPanel container) {
        if (isDarkMode()) {
            container.setBackground(darkGray);
        } else {
            container.setBackground(lightBlue);
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
