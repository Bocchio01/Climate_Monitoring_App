package Finestra.utils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Finestra.frames.AreaOperatore;
import Finestra.frames.Cerca;
import Finestra.frames.CreaCentro;
import Finestra.frames.Data;
import Finestra.frames.HomePage;
import Finestra.frames.Login;
import Finestra.frames.RegisterFrame;
import Finestra.frames.Tabella;

public class SetFrameFunc {

    private static void setCommonFrameProperties(JFrame e, String title) {

        e.setTitle(title);
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

    public static void setFrame(JFrame e) {

        setCommonFrameProperties(e, "Schermata Iniziale");

    }

    public static void setFrame(HomePage e) {

        setCommonFrameProperties(e, "Home Page");

    }

    static void setFrame(AreaOperatore e) {

        setCommonFrameProperties(e, "Area Operatore");

    }

    static void setFrame(Cerca e) {

        setCommonFrameProperties(e, "Cerca");

    }

    static void setFrame(Tabella e) {

        setCommonFrameProperties(e, "Tabella");

    }

    static void setFrame(RegisterFrame e) {

        setCommonFrameProperties(e, "Registrazione");

    }

    static void setFrame(Login e) {

        setCommonFrameProperties(e, "Login");

    }

    static void setFrame(Data e) {

        setCommonFrameProperties(e, "Data");

    }

    static void setFrame(CreaCentro e) {

        setCommonFrameProperties(e, "CreaCentro");

    }

}
