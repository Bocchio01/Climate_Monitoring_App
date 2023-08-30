package src.GUI.city;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.*;

import src.GUI.Home;
import src.functions.CityFunctions;
import src.functions.VisualizerFunctions;
import src.utils.AppConstants;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

public class CityVisualizer extends JPanel implements PanelHandler.PanelCreator {

    public static String windowsTitle = "Visualizzatore dati per area";
    public static String ID = "CityVisualizer";

    private JPanel panelMain = new JPanel();
    private JTextArea textareaOperator = new JTextArea();
    private static JTextArea textareaDatas = new JTextArea();
    private JButton buttonToBack = Widget.createButton("Indietro");
    private JButton buttonToHome = new JButton();

    public CityVisualizer() {

    }

    private void initializeComponents() {
        textareaOperator.setEnabled(false);
        textareaDatas.setEnabled(false);
    }

    private void createLayout() {
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
            PanelHandler.changePanel(Home.ID);
        });

        buttonToBack.addActionListener(e -> {
            PanelHandler.changePanel(CityQuery.ID);
        });
    }

    public static void loadDatas(Integer cityID) {
        String[] dataFromFile = VisualizerFunctions.getDataFromFile(cityID);

        if (dataFromFile.length > 0) {
            // String[] messagesAveraged =
            // VisualizerFunctions.computeAverageMessages(dataFromFile);
            // textareaDatas.setText(String.join("\n", messagesAveraged));
            for (int i = 3; i < 3 + 7; i++) {
                textareaDatas.setText(VisualizerFunctions.computeConcatComment(dataFromFile, i) +
                        "\n" + textareaDatas.getText());
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "L'operatore non ha ancora inserito dati per la città selezionata.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE);
            PanelHandler.changePanel(CityQuery.ID);
        }
    }

    @Override
    public JPanel createPanel() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        });
    }
}
