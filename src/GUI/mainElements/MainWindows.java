package src.GUI.mainElements;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import src.models.CurrentOperator;
import src.models.CurrentOperator.CurrentUserChangeListener;
import src.models.record.RecordOperator;
import src.utils.Functions;
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

        new Timer(1000, e -> {
            String dateTime = Functions.getCurrentTimeDateString();
            labelCurrentData.setText(dateTime);
        }).start();

        CurrentOperator.getInstance().addCurrentUserChangeListener(new CurrentUserChangeListener() {
            @Override
            public void onCurrentUserChange(RecordOperator newOperator) {
                if (newOperator == null)
                    setAppInfo("Operatore: /");
                else
                    setAppInfo("Operatore: " + newOperator.username());
            }
        });
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
