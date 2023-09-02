package src.models.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import src.models.data.DataQuery.QueryCondition;
import src.models.file.FileHandler;
import src.models.record.AreaRecord;
import src.models.record.CityRecord;
import src.models.record.OperatorRecord;
import src.models.record.WeatherRecord;
import src.utils.ENV;

public class DataHandler {

    private DataStorage dataStorage;
    public DataQuery dataQuery;

    public DataHandler() {
        dataStorage = new DataStorage();
        dataQuery = new DataQuery(dataStorage);
    }

    public Integer generatePrimaryKey(HashMap<Integer, ?> map) {
        int highestKey = 0;

        for (int key : map.keySet()) {
            if (key > highestKey) {
                highestKey = key;
            }
        }

        return highestKey + 1;
    }

    public void addNewRecord(CityRecord city) {
        // Check if city already exists
        try {
            FileHandler.appendToFile(ENV.Path.Files.CITY, city.toString());
            dataStorage.cityMap.put(city.ID(), city);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public OperatorRecord addNewRecord(String nameSurname,
            String taxCode,
            String email,
            String username,
            String password,
            Integer areaID) {

        OperatorRecord operator = new OperatorRecord(
                generatePrimaryKey(dataStorage.operatorMap),
                nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID);

        try {
            FileHandler.appendToFile(ENV.Path.Files.OPERATOR, operator.toString());
            dataStorage.operatorMap.put(operator.ID(), operator);
            return operator;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void addNewRecord(AreaRecord area) {
        try {
            FileHandler.appendToFile(ENV.Path.Files.AREA, area.toString());
            dataStorage.areaMap.put(area.ID(), area);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void addNewRecord(WeatherRecord weather) {
        try {
            FileHandler.appendToFile(ENV.Path.Files.AREA, weather.toString());
            dataStorage.weatherMap.put(weather.ID(), weather);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void updateRecord(CityRecord city) {
        updateRecord(ENV.Path.Files.CITY, city.ID(), city);
        dataStorage.cityMap.put(city.ID(), city);
    }

    public void updateRecord(OperatorRecord operator) {
        updateRecord(ENV.Path.Files.OPERATOR, operator.ID(), operator);
        dataStorage.operatorMap.put(operator.ID(), operator);
    }

    public void updateRecord(AreaRecord area) {
        updateRecord(ENV.Path.Files.AREA, area.ID(), area);
        dataStorage.areaMap.put(area.ID(), area);
    }

    public void updateRecord(WeatherRecord weather) {
        updateRecord(ENV.Path.Files.WEATHER, weather.ID(), weather);
        dataStorage.weatherMap.put(weather.ID(), weather);
    }

    private void updateRecord(String filePath, int ID, Object object) {
        try {
            List<String> lines = FileHandler.readFile(filePath);

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                if (Integer.parseInt(line[0]) == ID) {
                    lines.set(i, object.toString());
                    break;
                }
            }

            FileHandler.writeFile(filePath, lines);

        } catch (IOException e) {
            // TODO: handle exeprion
        }
    }

    public static void main(String[] args) {

        DataHandler dat = new DataHandler();

        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new QueryCondition("geonameID", Arrays.asList(3178229, 1680243)));
        conditions.add(new QueryCondition("name", "Unzad"));

        CityRecord[] cities = dat.dataQuery.getCityBy(conditions);
        for (CityRecord cityRecord : cities) {
            System.out.println(cityRecord.toString());
        }

    }
}
