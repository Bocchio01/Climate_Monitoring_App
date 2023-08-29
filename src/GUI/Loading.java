package src.GUI;

import java.awt.*;

import javax.swing.*;

import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;

public class Loading extends JFrame {

    private String windowsTitle = "Caricamento applicazione...";

    private JPanel panelMain = new JPanel();
    private JLabel labelIconImage = new JLabel();
    private JLabel labelAppName = new JLabel();
    private JProgressBar progressBar = new JProgressBar();

    private Timer timer;

    public Loading() {
        initializeComponents();
        createLayout();
        applyTheme();

        configureTimer();
    }

    private void initializeComponents() {
        // labelIconImage.setPreferredSize(new Dimension(400, 300));
        labelIconImage.setIcon(new ImageIcon(AppConstants.Path.Assets.LOADING));

        labelAppName.setText("Monitoraggio Climatico");
        labelAppName.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setPreferredSize(new Dimension(400, 40));
        progressBar.setStringPainted(true);

        timer = new Timer(5, e -> {
            int progress = progressBar.getValue();
            if (progress < 100) {
                progressBar.setValue(progress + 1);
            } else {
                timer.stop();
                dispose();
                FrameHandler.setFrame(new Home());
            }
        });

    }

    private void createLayout() {
        setTitle(windowsTitle);

        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;

        panelMain.add(labelIconImage, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;
        panelMain.add(labelAppName, gbc);

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridy = 2;

        panelMain.add(progressBar, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain, labelAppName });
    }

    private void configureTimer() {
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Loading loadingFrame = new Loading();
            loadingFrame.setSize(1200, 800);
            loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loadingFrame.setVisible(true);
            loadingFrame.setLocationRelativeTo(null);
        });
    }
}
