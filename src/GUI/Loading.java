package src.GUI;

import java.awt.*;

import javax.swing.*;

import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

public class Loading extends JPanel implements PanelHandler.PanelCreator {

    public static String windowsTitle = "Caricamento applicazione...";
    public static String ID = "Loading";

    private JPanel panelMain = new JPanel();
    private JLabel labelIconImage = Widget.createLogoLabel(2);
    private JLabel labelAppName = new JLabel();
    private JProgressBar progressBar = new JProgressBar();

    private static Timer timer;

    public Loading() {

    }

    private void initializeComponents() {

        labelAppName.setText("Monitoraggio Climatico");
        labelAppName.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setPreferredSize(new Dimension(400, 40));
        progressBar.setStringPainted(true);

        timer = new Timer(5, e -> {
            Integer progress = progressBar.getValue();
            if (progress < 100) {
                progressBar.setValue(progress + 1);
            } else {
                timer.stop();
                PanelHandler.changePanel(Home.ID);
            }
        });

    }

    private void createLayout() {
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;

        panelMain.add(labelIconImage, gbc);

        gbc.weighty = 0;
        panelMain.add(labelAppName, gbc);

        gbc.weighty = 1;
        panelMain.add(progressBar, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain, labelAppName });
    }

    public static void configureTimer() {
        timer.start();
    }

    @Override
    public JPanel createPanel() {
        initializeComponents();
        createLayout();
        applyTheme();
        // addActionEvent();

        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        });
    }
}
