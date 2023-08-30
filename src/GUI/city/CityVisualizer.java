package src.GUI.city;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import src.GUI.Home;
import src.functions.CityFunctions;
import src.functions.VisualizerFunctions;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;

public class CityVisualizer extends JFrame {

    private static String windowsTitle = "Visualizzatore dati per area";

    private JPanel panelMain = new JPanel();
    private JTextArea textareaOperator = new JTextArea();
    private JTextArea textareaDatas = new JTextArea();
    private JButton buttonToBack = new JButton();
    private JButton buttonToHome = new JButton();

    public CityVisualizer(Integer cityID) {

        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        loadDatas(cityID);

    }

    private void initializeComponents() {

        textareaOperator.setEditable(false);
        textareaDatas.setEditable(false);

        buttonToHome.setIcon(new ImageIcon(AppConstants.Path.Assets.HOME));
        buttonToHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToHome.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonToBack.setText("Indietro");
        buttonToBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToBack.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);
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
            FrameHandler.setFrame(new Home());
        });

        buttonToBack.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new CityQuery());
        });
    }

    private void loadDatas(Integer cityID) {
        String[] dataFromFile = VisualizerFunctions.getDataFromFile(cityID);

        if (dataFromFile.length > 0) {
            // String[] messagesAveraged =
            // VisualizerFunctions.computeAverageMessages(dataFromFile);
            // textareaDatas.setText(String.join("\n", messagesAveraged));
            for (int i = 3; i < 3+7; i++) {
                textareaDatas.setText(VisualizerFunctions.computeConcatComment(dataFromFile, i) +
                        "\n" +textareaDatas.getText());
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "L'operatore non ha ancora inserito dati per la città selezionata.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE);
            dispose();
            FrameHandler.setFrame(new CityQuery());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CityVisualizer visualizerFrame = new CityVisualizer(CityFunctions.getCityIDByName("Milano"));
            visualizerFrame.setSize(1200, 800);
            visualizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            visualizerFrame.setVisible(true);
            visualizerFrame.setLocationRelativeTo(null);
        });
    }
}
