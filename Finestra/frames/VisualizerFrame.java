package Finestra.frames;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Finestra.functions.VisualizerFunctions;
import Finestra.utils.AppConstants;
import Finestra.utils.FrameHandler;
import Finestra.utils.Theme;

import javax.swing.*;

public class VisualizerFrame extends JFrame {

    private String windowsTitle = "Visualizzatore dati per area";

    private JPanel panelMain = new JPanel();
    private JTextArea textareaOperator = new JTextArea();
    private JTextArea textareaDatas = new JTextArea();
    private JButton buttonToBack = new JButton();
    private JButton buttonToHome = new JButton();

    public VisualizerFrame(String nameCity) {

        initializeComponents();
        createLayout();
        applyTheme();

        addActionEvent();
        loadDatas(nameCity);

    }

    private void initializeComponents() {

        textareaOperator.setEditable(false);
        textareaDatas.setEditable(false);

        buttonToHome.setIcon(new ImageIcon(AppConstants.PATH_IMG + "/icona_home.png"));
        buttonToHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToHome.setPreferredSize(AppConstants.WIDGET_DIMENSION);

        buttonToBack.setText("Indietro");
        buttonToBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToBack.setPreferredSize(AppConstants.WIDGET_DIMENSION);
    }

    private void createLayout() {
        setTitle(windowsTitle);

        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(textareaOperator, gbc);
        panelMain.add(textareaDatas, gbc);

        gbc.weighty = 1;
        panelMain.add(buttonToHome, gbc);
        panelMain.add(buttonToBack, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }

    private void addActionEvent() {

        buttonToHome.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new HomeFrame());
        });

        buttonToBack.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new FindFrame());
        });
    }

    private void loadDatas(String nameCity) {
        String[] dataFromFile = VisualizerFunctions.getDataFromFile(nameCity);

        if (dataFromFile.length > 0) {
            String[] messagesAveraged = VisualizerFunctions.computeAverageMessages(dataFromFile);
            textareaDatas.setText(String.join("\n", messagesAveraged));

        } else {

            JOptionPane.showMessageDialog(this,
                    "L'operatore non ha ancora inserito dati per la città selezionata.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE);

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    FrameHandler.setFrame(new FindFrame());
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizerFrame visualizerFrame = new VisualizerFrame("Milano");
            visualizerFrame.setSize(1200, 800);
            visualizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            visualizerFrame.setVisible(true);
            visualizerFrame.setLocationRelativeTo(null);
        });
    }
}
