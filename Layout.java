import javax.swing.*;

import java.awt.*;

public class Layout extends JFrame {

        // Creiamo i componenti

        JLabel ipLabel = new JLabel("Nome Utente", SwingConstants.LEFT);

        JLabel passwordLabel = new JLabel("Password", SwingConstants.LEFT);

        JLabel fileDaInviareLabel = new JLabel("File da inviare", SwingConstants.LEFT);

        JTextField ipText = new JTextField();

        JPasswordField passwordText = new JPasswordField();

        JTextField fileDaInviareText = new JTextField();

        JButton pulsante = new JButton("Cerca");

        // Definiamo un metodo che ci servirà per definire i limiti di layout

        void impostaLimite(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {

                gbc.gridx = gx;

                gbc.gridy = gy;

                gbc.gridwidth = gw;

                gbc.gridheight = gh;

                gbc.weightx = wx;

                gbc.weighty = wy;

        }

        public Layout() { // Il costruttore della nostra classe

                super("File transfer appz");

                setSize(300, 120);

                setDefaultCloseOperation(EXIT_ON_CLOSE);

                JPanel pannello = new JPanel();

                // impostiamo le proprietà dei componenti

                ipText.setEditable(true);

                fileDaInviareText.setEditable(true);

                passwordText.setEchoChar('*');

                // definiamo il gestore di layout

                GridBagLayout grigliaAvanzata = new GridBagLayout();

                GridBagConstraints limite = new GridBagConstraints();

                pannello.setLayout(grigliaAvanzata);

                // definiamo i limiti di ogni componente e lo aggiungiamo al pannello

                impostaLimite(limite, 0, 0, 1, 1, 35, 0); // etichetta IP host

                limite.fill = GridBagConstraints.NONE;

                limite.anchor = GridBagConstraints.EAST;

                grigliaAvanzata.setConstraints(ipLabel, limite);

                pannello.add(ipLabel);

                impostaLimite(limite, 1, 0, 1, 1, 65, 100); // campo IP host

                limite.fill = GridBagConstraints.HORIZONTAL;

                grigliaAvanzata.setConstraints(ipText, limite);

                pannello.add(ipText);

                impostaLimite(limite, 0, 1, 1, 1, 0, 0); // etichetta password

                limite.fill = GridBagConstraints.NONE;

                limite.anchor = GridBagConstraints.EAST;

                grigliaAvanzata.setConstraints(passwordLabel, limite);

                pannello.add(passwordLabel);

                impostaLimite(limite, 1, 1, 1, 1, 0, 100); // campo password

                limite.fill = GridBagConstraints.HORIZONTAL;

                grigliaAvanzata.setConstraints(passwordText, limite);

                pannello.add(passwordText);

                impostaLimite(limite, 0, 2, 1, 1, 0, 0); // etichetta File da inviare

                limite.fill = GridBagConstraints.NONE;

                limite.anchor = GridBagConstraints.EAST;

                grigliaAvanzata.setConstraints(fileDaInviareLabel, limite);

                pannello.add(fileDaInviareLabel);

                impostaLimite(limite, 1, 2, 1, 1, 0, 100); // campo File da inviare

                limite.fill = GridBagConstraints.HORIZONTAL;

                grigliaAvanzata.setConstraints(fileDaInviareText, limite);

                pannello.add(fileDaInviareText);

                impostaLimite(limite, 0, 3, 2, 1, 0, 50); // Pulsante

                limite.fill = GridBagConstraints.NONE;

                limite.anchor = GridBagConstraints.CENTER;

                grigliaAvanzata.setConstraints(pulsante, limite);

                pannello.add(pulsante);

                setContentPane(pannello); // rendiamo il pannello parte del nostro frame

                JLabel background = new JLabel(new ImageIcon("Immagini/logo3.png"));

                pannello.add(background);

                show(); // Visualizziamo il tutto!

        }

        public static void main(String argv[]) {

                Layout nf = new Layout();

        }

}
