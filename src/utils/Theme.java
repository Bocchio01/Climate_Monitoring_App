package src.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Theme {

    private static boolean darkMode = true;
    private final static Color lightBlue = new Color(153, 255, 255);
    private final static Color darkGray = new Color(49, 51, 56);

    private static List<JLabel> labels = new ArrayList<>();
    private static List<JPanel> panels = new ArrayList<>();

    public static void toggleTheme() {
        darkMode = !darkMode;
        applyTheme();
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void registerLabel(JLabel label) {
        labels.add(label);
    }

    public static void registerPanel(JPanel panel) {
        panels.add(panel);
    }

    public static void applyTheme() {

        for (JLabel label : labels) {
            applyThemeToLabel(label);
        }

        for (JPanel panel : panels) {
            applyThemeToPanel(panel);
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
