package src.GUI.city;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import src.GUI.Home;
import src.functions.VisualizerFunctions;
import src.utils.GUIHandler;
import src.utils.Theme;
import src.utils.Widget;

public class CityVisualizer extends JPanel implements GUIHandler.Panel {

    public static String ID = "CityVisualizer";
    public GUIHandler panelHandler;

    private JTextArea textareaOperator = new JTextArea();
    private static JTextArea textareaDatas = new JTextArea();
    private JButton buttonToBack = new Widget.Button("Indietro");

    public CityVisualizer() {
    }

    private void initializeComponents() {
        textareaOperator.setEnabled(false);
        textareaDatas.setEnabled(false);
    }

    private void createLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(textareaOperator, gbc);
        add(textareaDatas, gbc);

        gbc.weighty = 1;
        add(buttonToBack, gbc);

    }

    private void addActionEvent() {

        buttonToBack.addActionListener(e -> {
            panelHandler.goToPanel(CityQuery.ID, null);

        });
    }

    public void loadDatas(Integer cityID) {
        String[] dataFromFile = VisualizerFunctions.getDataFromFile(cityID);

        if (dataFromFile.length > 0) {
            for (int i = 3; i < 3 + 7; i++) {
                textareaDatas.setText(VisualizerFunctions.computeConcatComment(dataFromFile, i) +
                        "\n" + textareaDatas.getText());
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "L'operatore non ha ancora inserito dati per la città selezionata.",
                    "Dati mancanti",
                    JOptionPane.WARNING_MESSAGE);
            panelHandler.goToPanel(CityQuery.ID, null);
        }
    }

    @Override
    public CityVisualizer createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        initializeComponents();
        createLayout();
        Theme.registerPanel(this);
        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        textareaOperator.setText("");
        textareaDatas.setText("");

        if (args != null && args.length > 0) {
            loadDatas((Integer) args[0]);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Errore nell'apertura della pagina.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            panelHandler.goToPanel(Home.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIHandler panelHandler = new GUIHandler();
            CityVisualizer cityVisualizer = new CityVisualizer();

            panelHandler.addPanel(cityVisualizer.createPanel(panelHandler));
            cityVisualizer.onOpen(args);
        });
    }
}
