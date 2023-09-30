package GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Theme {

    private boolean darkMode = true;
    private final static Color lightBlue = new Color(153, 255, 255);
    private final static Color darkGray = new Color(49, 51, 56);

    private List<JLabel> labels = new ArrayList<>();
    private List<JPanel> panels = new ArrayList<>();

    public void toggleTheme() {
        darkMode = !darkMode;
        applyTheme();
    }

    public boolean isDarkTheme() {
        return darkMode;
    }

    public void registerLabel(JLabel label) {
        labels.add(label);
    }

    public void registerPanel(JPanel panel) {
        panels.add(panel);
    }

    public void applyTheme() {

        for (JLabel label : labels) {
            applyThemeToLabel(label);
        }

        for (JPanel panel : panels) {
            applyThemeToPanel(panel);
        }
    }

    public void applyThemeToPanel(JPanel panel) {
        if (isDarkTheme()) {
            panel.setBackground(darkGray);
        } else {
            panel.setBackground(lightBlue);
        }
    }

    public void applyThemeToLabel(JLabel label) {
        if (isDarkTheme()) {
            label.setForeground(Color.WHITE);
        } else {
            label.setForeground(Color.BLACK);
        }

    }
}
