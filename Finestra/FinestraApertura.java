package Finestra;

import javax.swing.*;
import java.awt.*;

public class FinestraApertura extends JFrame {

    JButton accedi = new JButton("Accedi");
    JButton registrati = new JButton("Registrati");
    JButton ospite = new JButton("Continua come ospite");

    void impostaLimite(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {

        gbc.gridx = gx;

        gbc.gridy = gy;

        gbc.gridwidth = gw;

        gbc.gridheight = gh;

        gbc.weightx = wx;

        gbc.weighty = wy;
    }

    public FinestraApertura() {

        super("Climate Control");

        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel pannello = new JPanel();

        GridBagLayout grigliaAvanzata = new GridBagLayout();

        GridBagConstraints limite = new GridBagConstraints();

        pannello.setLayout(grigliaAvanzata);

        impostaLimite(limite, 1, 0, 1, 1, 0, 50); // Pulsante Accedi

        limite.fill = GridBagConstraints.NONE;

        limite.anchor = GridBagConstraints.NORTH;

        grigliaAvanzata.setConstraints(accedi, limite);

        pannello.add(accedi);

        impostaLimite(limite, 1, 1, 1, 1, 0, 50); // Pulsante Registrati

        limite.fill = GridBagConstraints.NONE;

        limite.anchor = GridBagConstraints.CENTER;

        grigliaAvanzata.setConstraints(registrati, limite);

        pannello.add(registrati);

        impostaLimite(limite, 1, 2, 1, 1, 0, 50); // Pulsante Ospite

        limite.fill = GridBagConstraints.NONE;

        limite.anchor = GridBagConstraints.CENTER;

        grigliaAvanzata.setConstraints(ospite, limite);

        pannello.add(ospite);

        setContentPane(pannello);

        JLabel logo = new JLabel(new ImageIcon("log3.png"));

        pannello.add(logo);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FinestraApertura();
    }

}
