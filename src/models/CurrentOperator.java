package models;

import models.record.RecordOperator;
import java.util.ArrayList;
import java.util.List;

public class CurrentOperator {
    private static CurrentOperator instance = null;
    private RecordOperator currentOperator = null;
    private List<CurrentUserChangeListener> listeners = new ArrayList<>();

    private CurrentOperator() {}

    public static CurrentOperator getInstance() {
        if (instance == null) {
            instance = new CurrentOperator();
        }
        return instance;
    }

    public void setCurrentOperator(RecordOperator operator) {
        if (operator != currentOperator) {
            currentOperator = operator;
            notifyCurrentUserChange();
        }
    }

    public RecordOperator getCurrentOperator() {
        return currentOperator;
    }

    public boolean isUserLogged() {
        return currentOperator != null;
    }

    public void performLogout() {
        setCurrentOperator(null);
    }

    
    // Observer pattern methods
    public interface CurrentUserChangeListener {
        void onCurrentUserChange(RecordOperator newOperator);
    }

    public void addCurrentUserChangeListener(CurrentUserChangeListener listener) {
        listeners.add(listener);
    }

    public void removeCurrentUserChangeListener(CurrentUserChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyCurrentUserChange() {
        for (CurrentUserChangeListener listener : listeners) {
            listener.onCurrentUserChange(currentOperator);
        }
    }
}
