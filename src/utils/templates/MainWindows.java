package src.utils.templates;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import src.utils.Interfaces;

public class MainWindows extends JPanel implements Interfaces.UIWindows {

    private JScrollPane scrollPane = new JScrollPane();
    private JPanel contentPanel = new JPanel();
    private JLabel labelAppInfo = new JLabel("Operatore: /");
    private JLabel labelCurrentData = new JLabel();
    private JPanel bottomPanel = new JPanel();

    public MainWindows(CardLayout cardLayout) {
        super(new BorderLayout());

        contentPanel.setLayout(cardLayout);

        scrollPane.setViewportView(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(labelAppInfo, BorderLayout.WEST);
        bottomPanel.add(labelCurrentData, BorderLayout.EAST);

        bottomPanel.setBorder(new EmptyBorder(3, 7, 3, 7));

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        new Timer(1000, e -> setHour()).start();
    }

    public void setHour() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        labelCurrentData.setText(dateFormat.format(new Date()));
    }

    @Override
    public JPanel getMainPanel() {
        return this;
    }

    @Override
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public void setAppInfo(String text) {
        labelAppInfo.setText(text);
    }

}
