package models.logic;

import java.util.ArrayList;
import java.util.List;

import models.CurrentOperator;
import models.data.DataHandler;
import models.data.DataQuery.QueryCondition;
import models.record.RecordOperator;

public class LogicOperator {

    private DataHandler dataHandler;

    public LogicOperator(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public void performLogin(String username, String password) {

        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (currentOperator.isUserLogged()) {
            currentOperator.performLogout();
        }

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("id and password cannot be empty");
        }

        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new QueryCondition("username", username));
        conditions.add(new QueryCondition("password", password));

        RecordOperator[] result = dataHandler.getOperatorBy(conditions);
        if (result.length == 1) {
            currentOperator.setCurrentOperator(
                    new RecordOperator(result[0].ID(),
                            result[0].nameSurname(),
                            result[0].taxCode(),
                            result[0].email(),
                            result[0].username(),
                            password,
                            result[0].areaID()));
        } else {
            currentOperator.performLogout();
            throw new IllegalArgumentException("username or password are incorrect");
        }
    }

    public void performRegistration(String nameSurname,
            String taxCode,
            String email,
            String username,
            String password,
            Integer areaID) {

        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (currentOperator.isUserLogged()) {
            currentOperator.performLogout();
        }

        if (!isValidNameSurname(nameSurname))
            throw new IllegalArgumentException("nameSurname not valid!");
        if (!isValidTaxCode(taxCode))
            throw new IllegalArgumentException("taxCode not valid!");
        if (!isValidEmail(email))
            throw new IllegalArgumentException("email not valid!");
        if (!isValidUsername(username))
            throw new IllegalArgumentException("username not valid!");
        if (!isValidPassword(password))
            throw new IllegalArgumentException("password!");

        RecordOperator newOperator = dataHandler.addNewRecord(nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID);
    }

    public boolean isValidNameSurname(String nameSurname) {
        return nameSurname.matches("^[a-zA-Z\\s]+$");
    }

    public boolean isValidTaxCode(String taxCode) {
        return taxCode.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9._-]{3,}$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!.])(.{8,})$");
    }

}
