package Finestra.frames;

import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

public class AreaOperatore extends JFrame implements ActionListener {
    // Oggetti da inserire nel frame

    JPanel container = new JPanel();

    JButton registratiButton = new JButton("Registrati");
    JButton accediButton = new JButton("Accedi");
    JButton indietroButton = new JButton("Indietro");
    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    Cursor cursoreReg = registratiButton.getCursor();
    Cursor cursoreAcc = accediButton.getCursor();
    Cursor cursoreInd = indietroButton.getCursor();

    public AreaOperatore() {

        // Formazione del frame+componenti

        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);

    }

    public void setLocationAndSize() {

        // Set posizioni degli oggetti nel frame

        registratiButton.setBounds(300, 270, 200, 30);
        logo.setBounds(290, 50, 200, 186);
        accediButton.setBounds(300, 320, 200, 30);
        indietroButton.setBounds(670, 500, 80, 30);

        // homeButton.setContentAreaFilled(false); TRASPARENTE O NO???

        registratiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accediButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(registratiButton);
        container.add(logo);
        container.add(accediButton);
        container.add(indietroButton);
        add(container);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        registratiButton.addActionListener(this);
        accediButton.addActionListener(this);
        indietroButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone area operatore
        if (e.getSource() == registratiButton) {
            // Apre la finestra per l'accesso
            dispose();
            SetFrameFunc.setFrame(new RegisterFrame());

        }

        // Bottone Ospite
        if (e.getSource() == accediButton)

        {
            // Apre la finestra per la ricerca della zona
            dispose();
            SetFrameFunc.setFrame(new Login());

        }

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            SetFrameFunc.setFrame(new HomePage());

        }

    }

}
