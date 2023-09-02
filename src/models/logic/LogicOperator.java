package src.models.logic;

import java.util.ArrayList;
import java.util.List;

import src.models.data.DataHandler;
import src.models.data.DataQuery.QueryCondition;
import src.models.record.OperatorRecord;

public class LogicOperator {

    private OperatorRecord currentOperator = null;
    private DataHandler dataHandler;

    public LogicOperator(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    private void setCurrentOperator(OperatorRecord operator) {
        currentOperator = operator;
    }

    public OperatorRecord getCurrentOperator() {
        return currentOperator;
    }

    public boolean isUserLogged() {
        return currentOperator != null;
    }

    public void performLogout() {
        setCurrentOperator(null);
    }

    public void performLogin(String username, String password) {

        if (isUserLogged()) {
            performLogout();
        }

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("id and password cannot be empty");
        }

        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new QueryCondition("username", username));
        conditions.add(new QueryCondition("password", password));

        OperatorRecord[] result = dataHandler.dataQuery.getOperatorBy(conditions);
        if (result.length == 1) {
            setCurrentOperator(result[0]);
        } else {
            performLogout();
            throw new IllegalArgumentException("username or password are incorrect");
        }
    }

    public void performRegistration(String nameSurname,
            String taxCode,
            String email,
            String username,
            String password,
            Integer areaID) {

        if (isUserLogged()) {
            performLogout();
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

        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new QueryCondition("username", username));
        conditions.add(new QueryCondition("password", password));

        OperatorRecord[] result = dataHandler.dataQuery.getOperatorBy(conditions);
        if (result.length > 0) {
            performLogout();
            throw new IllegalArgumentException("user already exists");
        }

        OperatorRecord newOperator = dataHandler.addNewRecord(nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID);
        // setCurrentOperator(newOperator);
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
        return password.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!])(.{8,})$");
    }

}
