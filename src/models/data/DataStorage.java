package src.models.data;

import java.io.*;
import java.util.*;

import src.utils.ENV;
import src.models.file.FileHandler;
import src.models.record.AreaRecord;
import src.models.record.CityRecord;
import src.models.record.OperatorRecord;
import src.models.record.WeatherRecord;
import src.models.record.WeatherRecord.WeatherData;

public class DataStorage {

    public HashMap<Integer, CityRecord> cityMap;
    public HashMap<Integer, OperatorRecord> operatorMap;
    public HashMap<Integer, AreaRecord> areaMap;
    public HashMap<Integer, WeatherRecord> weatherMap;

    public DataStorage() {

        cityMap = createCityMap();
        operatorMap = createOperatorMap();
        areaMap = createAreaMap();
        weatherMap = createWeatherMap();

    }

    public CityRecord getCityByID(Integer geonameID) {
        return cityMap.get(geonameID);
    }

    public OperatorRecord getOperatorByID(Integer operatorID) {
        return operatorMap.get(operatorID);
    }

    public AreaRecord getAreaByID(Integer areaID) {
        return areaMap.get(areaID);
    }

    public WeatherRecord getWeatherByID(Integer weatherID) {
        return weatherMap.get(weatherID);
    }

    private HashMap<Integer, CityRecord> createCityMap() {
        HashMap<Integer, CityRecord> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.CITY);

            CityRecord[] cityCoords = new CityRecord[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                if (line.length == 7) {
                    cityCoords[i - 1] = new CityRecord(
                            Integer.parseInt(line[0]),
                            line[1],
                            line[2],
                            line[3],
                            line[4],
                            Double.parseDouble(line[5].replace(",", ".")),
                            Double.parseDouble(line[6].replace(",", ".")));
                } else if (line.length == 6) {
                    cityCoords[i - 1] = new CityRecord(
                            Integer.parseInt(line[0]),
                            line[1],
                            line[2],
                            line[3],
                            "unset",
                            Double.parseDouble(line[4].replace(",", ".")),
                            Double.parseDouble(line[5].replace(",", ".")));
                } else
                    System.out.println("Error: " + line[0]);
            }

            for (CityRecord cityCoord : cityCoords) {
                map.put(cityCoord.ID(), cityCoord);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }

    }

    private HashMap<Integer, OperatorRecord> createOperatorMap() {
        HashMap<Integer, OperatorRecord> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.OPERATOR);

            OperatorRecord[] operatorDatas = new OperatorRecord[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                operatorDatas[i - 1] = new OperatorRecord(
                        Integer.parseInt(line[0]),
                        line[1],
                        line[2],
                        line[3],
                        line[4],
                        line[5],
                        line[6].equals(ENV.EMPTY_STRING) ? null : Integer.parseInt(line[6]));
            }

            for (OperatorRecord operatorData : operatorDatas) {
                map.put(operatorData.ID(), operatorData);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }
    }

    private HashMap<Integer, AreaRecord> createAreaMap() {
        HashMap<Integer, AreaRecord> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.AREA);

            AreaRecord[] areaDatas = new AreaRecord[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                areaDatas[i - 1] = new AreaRecord(
                        Integer.parseInt(line[0]),
                        line[1],
                        line[2],
                        line[3],
                        line[4],
                        line[5],
                        line[6],
                        Arrays.stream(line[7].split(ENV.CSV_SUB_SEPARATOR)).map(Integer::parseInt)
                                .toArray(Integer[]::new));
            }

            for (AreaRecord areaData : areaDatas) {
                map.put(areaData.ID(), areaData);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }
    }

    private HashMap<Integer, WeatherRecord> createWeatherMap() {
        HashMap<Integer, WeatherRecord> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.WEATHER);

            WeatherRecord[] weatherDatas = new WeatherRecord[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                List<WeatherData> weatherDataList = new ArrayList<>();

                for (int j = 4; j < line.length; j++) {
                    String[] data = line[j].split(ENV.CSV_SUB_SEPARATOR);
                    weatherDataList.add(new WeatherRecord.WeatherData(Integer.parseInt(data[0]), data[1]));
                }

                weatherDatas[i - 1] = new WeatherRecord(
                        Integer.parseInt(line[0]),
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[2]),
                        line[3],
                        weatherDataList.get(0),
                        weatherDataList.get(1),
                        weatherDataList.get(2),
                        weatherDataList.get(3),
                        weatherDataList.get(4),
                        weatherDataList.get(5),
                        weatherDataList.get(6));
            }

            for (WeatherRecord weatherData : weatherDatas) {
                map.put(weatherData.ID(), weatherData);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }

    }

    public static void main(String[] args) {
        DataStorage appData = new DataStorage();

        System.out.println(appData.getCityByID(3178229));
        // System.out.println(appData.getOperatorByID(1));
        // System.out.println(appData.getAreaByID(1));
        // System.out.println(appData.getWeatherByID(1));
    }
}
