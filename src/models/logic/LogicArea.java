package models.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import models.CurrentOperator;
import models.data.DataHandler;
import models.record.RecordArea;
import models.record.RecordOperator;
import models.record.RecordWeather;
import models.record.RecordWeather.WeatherData;
import utils.Functions;

public class LogicArea {

    private DataHandler dataHandler;

    public LogicArea(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public void initNewArea(
            String areaName,
            String streetName,
            String streetNumber,
            String CAP,
            String townName,
            String districtName,
            Integer[] cityIDs) {

        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (currentOperator.isUserLogged() == false) {
            throw new RuntimeException("No user logged");
        }

        if (currentOperator.getCurrentOperator().areaID() != null) {
            throw new RuntimeException("User already has an area");
        }

        if (areaName.isBlank())
            throw new IllegalArgumentException("areaName not valid!");
        if (streetName.isBlank())
            throw new IllegalArgumentException("streetName not valid!");
        if (streetNumber.isBlank())
            throw new IllegalArgumentException("streetNumber not valid!");
        if (CAP.isBlank())
            throw new IllegalArgumentException("CAP not valid!");
        if (townName.isBlank())
            throw new IllegalArgumentException("townName not valid!");
        if (districtName.isBlank())
            throw new IllegalArgumentException("districtName not valid!");

        for (Integer cityID : cityIDs) {
            if (dataHandler.getCityBy(cityID) == null) {
                throw new IllegalArgumentException("cityNames not valid!");
            }
        }

        RecordArea newArea = dataHandler.addNewRecord(
                areaName,
                streetName,
                streetNumber,
                CAP,
                townName,
                districtName,
                cityIDs);

        RecordOperator updatedOperator = new RecordOperator(
                currentOperator.getCurrentOperator().ID(),
                currentOperator.getCurrentOperator().nameSurname(),
                currentOperator.getCurrentOperator().taxCode(),
                currentOperator.getCurrentOperator().email(),
                currentOperator.getCurrentOperator().username(),
                currentOperator.getCurrentOperator().password(),
                newArea.ID());

        dataHandler.updateRecord(updatedOperator);
        CurrentOperator.getInstance().setCurrentOperator(updatedOperator);

    }

    public void addDataToArea(
            Integer cityID,
            String date,
            Object[][] tableDatas) {

        CurrentOperator currentOperator = CurrentOperator.getInstance();

        if (currentOperator.isUserLogged() == false) {
            throw new RuntimeException("No user logged");
        }

        if (currentOperator.getCurrentOperator().areaID() == null) {
            throw new RuntimeException("User has no area");
        }

        if (date.isBlank() || !Functions.isDateValid(date))
            throw new IllegalArgumentException("date not valid!");

        // for (Object[] row : tableDatas) {
        //     if (row[0] == null || row[1] == null) {
        //         throw new IllegalArgumentException("tableDatas not valid!");
        //     }
        // }

        boolean allRowsNull = true;
        for (int i = 0; i < tableDatas.length; i++) {
            if (tableDatas[i][0] != null) {
                allRowsNull = false;
                break;
            }
        }
        if (allRowsNull) 
            throw new IllegalArgumentException("tableDatas not valid!");

        List<WeatherData> weatherDataList = new ArrayList<>();
        for (int i = 0; i < tableDatas.length; i++) {
            Integer integerValue = (Integer) tableDatas[i][0];
            String stringValue = (String) tableDatas[i][1];
            weatherDataList.add(new WeatherData(integerValue, stringValue));
        }

        RecordWeather newWeather = dataHandler.addNewRecord(
                cityID,
                currentOperator.getCurrentOperator().areaID(),
                date,
                weatherDataList.get(0),
                weatherDataList.get(1),
                weatherDataList.get(2),
                weatherDataList.get(3),
                weatherDataList.get(4),
                weatherDataList.get(5),
                weatherDataList.get(6));

    }

}
