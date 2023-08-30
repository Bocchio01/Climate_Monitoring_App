package src.utils.templates;

import javax.swing.*;

import src.utils.Theme;

import java.awt.*;

public abstract class TwoRows extends JPanel {

    public JPanel topPanel;
    public JPanel bottomPanel;

    protected GridBagConstraints mainPanelConstrains = new GridBagConstraints() {
        {
            gridx = 0;
            gridy = GridBagConstraints.RELATIVE;
            weightx = 1;
            weighty = 1;
            anchor = GridBagConstraints.CENTER;
            fill = GridBagConstraints.BOTH;
        }
    };

    protected GridBagConstraints subPanelConstrains = new GridBagConstraints() {
        {
            gridx = 0;
            gridy = GridBagConstraints.RELATIVE;
            weightx = 1;
            weighty = 1;
            anchor = GridBagConstraints.CENTER;
        }
    };

    public TwoRows() {
        setLayout(new GridBagLayout());
        topPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel(new GridBagLayout());

        add(topPanel, mainPanelConstrains);
        add(bottomPanel, mainPanelConstrains);

        Theme.registerPanel(topPanel);
        Theme.registerPanel(bottomPanel);

    }

    protected void addTop(Component component) {
        topPanel.add(component, subPanelConstrains);
    }

    protected void addBottom(Component component) {
        bottomPanel.add(component, subPanelConstrains);
    }
}
