package GUI.layouts;

import javax.swing.*;

import java.awt.*;

public abstract class TwoColumns extends JPanel {

    public JPanel leftPanel;
    public JPanel rightPanel;

    protected GridBagConstraints mainPanelConstrains = new GridBagConstraints() {
        {
            gridx = GridBagConstraints.RELATIVE;
            gridy = 0;
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

    public TwoColumns() {
        setLayout(new GridBagLayout());
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());

        add(leftPanel, mainPanelConstrains);
        add(rightPanel, mainPanelConstrains);

    }

    protected void addLeft(Component component) {
        leftPanel.add(component, subPanelConstrains);
    }

    protected void addRight(Component component) {
        rightPanel.add(component, subPanelConstrains);
    }
}
