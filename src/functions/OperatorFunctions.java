package src.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.utils.AppConstants;

public class OperatorFunctions {

    private static Boolean isUserLogged = false;
    private static String[] currentUserData = new String[6];

    public static boolean performLogin(String id, String password) {

        try {
            FileReader fin = new FileReader(AppConstants.Path.Files.OPERATOR_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] userDataFromFile = line.split("[,;]");

                if (checkCredentials(
                        new String[] { id, userDataFromFile[AppConstants.Index.ID] },
                        new String[] { password, userDataFromFile[AppConstants.Index.PWD] }) &&
                        setCurrentUserData(userDataFromFile)) {

                    rfbuffer.close();
                    return true;
                }

            }

            rfbuffer.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        performLogout();
        return false;

    }

    private static boolean setCurrentUserData(String[] userDataFromFile) {

        try {

            currentUserData[AppConstants.Index.NAME] = userDataFromFile[AppConstants.Index.NAME];
            currentUserData[AppConstants.Index.TAXCODE] = userDataFromFile[AppConstants.Index.TAXCODE];
            currentUserData[AppConstants.Index.EMAIL] = userDataFromFile[AppConstants.Index.EMAIL];
            currentUserData[AppConstants.Index.ID] = userDataFromFile[AppConstants.Index.ID];
            currentUserData[AppConstants.Index.PWD] = userDataFromFile[AppConstants.Index.PWD];
            currentUserData[AppConstants.Index.AREA] = !userDataFromFile[AppConstants.Index.AREA]
                    .equals(AppConstants.EMPTY_STRING) ? userDataFromFile[AppConstants.Index.AREA]
                            : null;

            isUserLogged = true;
            return true;

        } catch (Exception e) {
            // TODO: handle exception
        }

        performLogout();
        return false;
    }

    public static void performLogout() {
        isUserLogged = false;
        currentUserData = new String[6];

        return;
    }

    public static boolean isUserExist(String id, String password) {

        try {
            FileReader fin = new FileReader(AppConstants.Path.Files.OPERATOR_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String idFromFile = line.split("[,;]")[AppConstants.Index.ID];
                String passwordFromFile = line.split("[,;]")[AppConstants.Index.PWD];

                if (id.equals(idFromFile) ||
                        checkCredentials(
                                new String[] { id, idFromFile },
                                new String[] { password, passwordFromFile })) {
                    rfbuffer.close();
                    return true;
                }

            }

            rfbuffer.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    public static boolean updateUserData(Integer index, String newValue) {

        if (!isUserLogged()) {
            return false;
        }

        try {
            FileReader fin = new FileReader(AppConstants.Path.Files.OPERATOR_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            StringBuilder fileContent = new StringBuilder();

            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] dataFromFile = line.split(",");

                if (checkCredentials(
                        new String[] { getCurrentUserID(), dataFromFile[AppConstants.Index.ID] },
                        new String[] { getCurrentUserPassword(), dataFromFile[AppConstants.Index.PWD] })) {

                    dataFromFile[index] = newValue;

                    fileContent.append(String.join(",", dataFromFile)).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }

            }

            fin.close();

            FileWriter fout = new FileWriter(AppConstants.Path.Files.OPERATOR_DATA, false);
            BufferedWriter wfbuffer = new BufferedWriter(fout);

            wfbuffer.write(fileContent.toString());
            wfbuffer.close();

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }

    private static boolean checkCredentials(String[] ids, String[] passwords) {
        return (ids[0].equals(ids[1]) && passwords[0].equals(passwords[1]));
    }

    public static boolean isUserLogged() {
        return isUserLogged;
    }

    public static String getCurrentUserName() {
        return currentUserData[AppConstants.Index.NAME];
    }

    public static String getCurrentUserTaxCode() {
        return currentUserData[AppConstants.Index.TAXCODE];
    }

    public static String getCurrentUserEmail() {
        return currentUserData[AppConstants.Index.EMAIL];
    }

    public static String getCurrentUserID() {
        return currentUserData[AppConstants.Index.ID];
    }

    public static String getCurrentUserPassword() {
        return currentUserData[AppConstants.Index.PWD];
    }

    public static String getCurrentUserArea() {
        return currentUserData[AppConstants.Index.AREA];
    }

    public static boolean performRegistration(String[] dataInserted) {

        if (isValidInput(dataInserted) == false ||
                isUserExist(dataInserted[AppConstants.Index.ID], dataInserted[AppConstants.Index.PWD])) {
            return false;
        }

        String dati = String.join(",", dataInserted);

        try {
            FileWriter fileWriter = new FileWriter(AppConstants.Path.Files.OPERATOR_DATA, true);
            fileWriter.write(dati + "\n");
            fileWriter.close();
            return true;

        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    public static boolean isValidInput(String[] dataInserted) {

        String title = "Dato inserito non corretto", message = "";

        if (dataInserted[0].isEmpty()) {
            message = "Inserisci un nome e cognome";

        } else if (dataInserted[1].isEmpty()) {
            message = "Inserisci un codice fiscale";

        } else if (!isValidFromRegex(dataInserted[1], "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")) {
            message = "Inserisci un codice fiscale valido";

        } else if (dataInserted[2].isEmpty()) {
            message = "Inserisci una email";

        } else if (!isValidFromRegex(dataInserted[2], "^[A-Za-z0-9+_.-]+@(.+)$")) {
            message = "Email non corretta";

        } else if (dataInserted[3].isEmpty()) {
            message = "Inserisci un id";

        } else if (dataInserted[4].isEmpty()) {
            message = "Inserisci una password";

        } else if (!isValidFromRegex(dataInserted[4], "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(.{8,})$")) {
            message = "La password non ha il formato corretto.";
        }

        if (!message.equals("")) {
            // JOptionPane.showMessageDialog(null, message, title,
            // JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean isValidFromRegex(String value, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        // return matcher.matches();
        return true;
    }
}