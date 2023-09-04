package src.models.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import src.models.file.FileHandler;
import src.models.record.RecordArea;
import src.models.record.RecordCity;
import src.models.record.RecordOperator;
import src.models.record.RecordWeather;
import src.utils.ENV;

public class DataHandler extends DataQuery {

    private static DataStorage dataStorage = new DataStorage();

    public DataHandler() {
        super(dataStorage);
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

    public RecordOperator addNewRecord(String nameSurname,
            String taxCode,
            String email,
            String username,
            String password,
            Integer areaID) {

        RecordOperator operator = new RecordOperator(
                generatePrimaryKey(dataStorage.operatorMap),
                nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID);

        List<QueryCondition> conditions = new ArrayList<>();
        conditions.add(new QueryCondition("username", username));
        conditions.add(new QueryCondition("password", password));

        RecordOperator[] result = getOperatorBy(conditions);
        if (result.length > 0) {
            throw new IllegalArgumentException("user already exists");
        }

        try {
            FileHandler.appendToFile(ENV.Path.Files.OPERATOR, operator.toString());
            dataStorage.operatorMap.put(operator.ID(), operator);
            return operator;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public RecordArea addNewRecord(String areaName,
            String streetName,
            String streetNumber,
            String CAP,
            String townName,
            String districtName,
            Integer[] cityIDs) {

        RecordArea area = new RecordArea(
                generatePrimaryKey(dataStorage.areaMap),
                areaName,
                streetName,
                streetNumber,
                CAP,
                townName,
                districtName,
                cityIDs);

        List<QueryCondition> conditions = new ArrayList<>();

        conditions.add(new QueryCondition("areaName", areaName));
        conditions.add(new QueryCondition("streetName", streetName));
        conditions.add(new QueryCondition("streetNumber", streetNumber));
        conditions.add(new QueryCondition("CAP", CAP));
        conditions.add(new QueryCondition("townName", townName));
        conditions.add(new QueryCondition("districtName", districtName));
        conditions.add(new QueryCondition("cityID", cityIDs));

        RecordArea[] result = getAreaBy(conditions);
        if (result.length > 0) {
            throw new IllegalArgumentException("Area already exists");
        }

        try {
            FileHandler.appendToFile(ENV.Path.Files.AREA, area.toString());
            dataStorage.areaMap.put(area.ID(), area);
            return area;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public RecordWeather addNewRecord(
            Integer cityID,
            Integer areaID,
            String date,
            RecordWeather.WeatherData wind,
            RecordWeather.WeatherData humidity,
            RecordWeather.WeatherData pressure,
            RecordWeather.WeatherData temperature,
            RecordWeather.WeatherData precipitation,
            RecordWeather.WeatherData glacierElevation,
            RecordWeather.WeatherData glacierMass) {

        RecordWeather newWeather = new RecordWeather(
                generatePrimaryKey(dataStorage.weatherMap),
                cityID,
                areaID,
                date,
                wind,
                humidity,
                pressure,
                temperature,
                precipitation,
                glacierElevation,
                glacierMass);

        try {
            FileHandler.appendToFile(ENV.Path.Files.WEATHER, newWeather.toString());
            dataStorage.weatherMap.put(newWeather.ID(), newWeather);
            return newWeather;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void updateRecord(RecordCity city) {
        updateRecord(ENV.Path.Files.CITY, city.ID(), city);
        dataStorage.cityMap.put(city.ID(), city);
    }

    public void updateRecord(RecordOperator operator) {
        updateRecord(ENV.Path.Files.OPERATOR, operator.ID(), operator);
        dataStorage.operatorMap.put(operator.ID(), operator);
    }

    public void updateRecord(RecordArea area) {
        updateRecord(ENV.Path.Files.AREA, area.ID(), area);
        dataStorage.areaMap.put(area.ID(), area);
    }

    public void updateRecord(RecordWeather weather) {
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

        RecordCity[] cities = dat.getCityBy(conditions);
        for (RecordCity RecordCity : cities) {
            System.out.println(RecordCity.toString());
        }

    }
}
