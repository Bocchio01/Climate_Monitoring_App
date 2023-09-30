package models.data;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import utils.ENV;
import models.file.FileHandler;
import models.record.RecordArea;
import models.record.RecordCity;
import models.record.RecordOperator;
import models.record.RecordWeather;
import models.record.RecordWeather.WeatherData;

public class DataStorage {

    public HashMap<Integer, RecordCity> cityMap;
    public HashMap<Integer, RecordOperator> operatorMap;
    public HashMap<Integer, RecordArea> areaMap;
    public HashMap<Integer, RecordWeather> weatherMap;

    public DataStorage() {

        cityMap = createCityMap();
        operatorMap = createOperatorMap();
        areaMap = createAreaMap();
        weatherMap = createWeatherMap();

    }

    public RecordCity getCityByID(Integer geonameID) {
        return cityMap.get(geonameID);
    }

    public RecordOperator getOperatorByID(Integer operatorID) {
        return operatorMap.get(operatorID);
    }

    public RecordArea getAreaByID(Integer areaID) {
        return areaMap.get(areaID);
    }

    public RecordWeather getWeatherByID(Integer weatherID) {
        return weatherMap.get(weatherID);
    }

    private HashMap<Integer, RecordCity> createCityMap() {
        HashMap<Integer, RecordCity> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.CITY);

            RecordCity[] cityCoords = new RecordCity[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                if (line.length == 7) {
                    cityCoords[i - 1] = new RecordCity(
                            Integer.parseInt(line[0]),
                            line[1],
                            line[2],
                            line[3],
                            line[4],
                            Double.parseDouble(line[5].replace(",", ".")),
                            Double.parseDouble(line[6].replace(",", ".")));
                } else if (line.length == 6) {
                    cityCoords[i - 1] = new RecordCity(
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

            for (RecordCity cityCoord : cityCoords) {
                map.put(cityCoord.ID(), cityCoord);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }

    }

    private HashMap<Integer, RecordOperator> createOperatorMap() {
        HashMap<Integer, RecordOperator> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.OPERATOR);

            RecordOperator[] operatorDatas = new RecordOperator[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                operatorDatas[i - 1] = new RecordOperator(
                        Integer.parseInt(line[0]),
                        line[1],
                        line[2],
                        line[3],
                        line[4],
                        line[5],
                        line[6].equals(ENV.EMPTY_STRING) ? null : Integer.parseInt(line[6]));
            }

            for (RecordOperator operatorData : operatorDatas) {
                map.put(operatorData.ID(), operatorData);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }
    }

    private HashMap<Integer, RecordArea> createAreaMap() {
        HashMap<Integer, RecordArea> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.AREA);

            RecordArea[] areaDatas = new RecordArea[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                areaDatas[i - 1] = new RecordArea(
                        Integer.parseInt(line[0]),
                        line[1],
                        line[2],
                        line[3],
                        line[4],
                        line[5],
                        line[6],
                        Arrays.stream(line[7].split(Pattern.quote(ENV.CSV_SUB_SEPARATOR))).map(Integer::parseInt)
                                .toArray(Integer[]::new));
            }

            for (RecordArea areaData : areaDatas) {
                map.put(areaData.ID(), areaData);
            }

            return map;

        } catch (IOException e) {
            // TODO: handle exception
            return null;
        }
    }

    private HashMap<Integer, RecordWeather> createWeatherMap() {
        HashMap<Integer, RecordWeather> map = new HashMap<>();

        try {
            List<String> lines = FileHandler.readFile(ENV.Path.Files.WEATHER);

            RecordWeather[] weatherDatas = new RecordWeather[lines.size() - 1];

            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i).split(ENV.CSV_SEPARATOR);

                List<WeatherData> weatherDataList = new ArrayList<>();

                for (int j = 4; j < line.length; j++) {
                    String[] data = line[j].split(Pattern.quote(ENV.CSV_SUB_SEPARATOR));
                    weatherDataList.add(new RecordWeather.WeatherData(
                            data[0].equals(ENV.EMPTY_STRING) ? null : Integer.parseInt(data[0]),
                            data[1].equals(ENV.EMPTY_STRING) ? null : data[1]));
                }

                weatherDatas[i - 1] = new RecordWeather(
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

            for (RecordWeather weatherData : weatherDatas) {
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
