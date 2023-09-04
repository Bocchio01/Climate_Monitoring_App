package src.models.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

import src.models.record.RecordCity;
import src.models.record.RecordOperator;
import src.models.record.RecordArea;
import src.models.record.RecordWeather;

public class DataQuery {

    private DataStorage dataStorage;

    public DataQuery(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public RecordCity getCityBy(Integer ID) {
        RecordCity[] matchingCity = getCityBy(new QueryCondition("ID", ID));
        if (matchingCity.length > 1)
        throw new IllegalArgumentException("Multiple cities with same ID");
        
        return matchingCity[0]; 
    }

    public RecordCity[] getCityBy(QueryCondition condition) {
        ArrayList<QueryCondition> conditions = new ArrayList<>();
        conditions.add(condition);
        return getCityBy(conditions);
    }

    public RecordCity[] getCityBy(List<QueryCondition> conditions) {
        List<RecordCity> matchingCity = filterData(dataStorage.cityMap.values(),
                conditions,
                this::checkCityCondition);
        return matchingCity.toArray(new RecordCity[0]);
    }

    public RecordOperator getOperatorBy(Integer ID) {
        RecordOperator[] matchingOperator = getOperatorBy(new QueryCondition("ID", ID));
        if (matchingOperator.length > 1)
            throw new IllegalArgumentException("Multiple operators with same ID");

        return matchingOperator[0];
    }

    public RecordOperator[] getOperatorBy(QueryCondition condition) {
        ArrayList<QueryCondition> conditions = new ArrayList<>();
        conditions.add(condition);
        return getOperatorBy(conditions);
    }

    public RecordOperator[] getOperatorBy(List<QueryCondition> conditions) {
        List<RecordOperator> matchingOperator = filterData(dataStorage.operatorMap.values(),
                conditions,
                this::checkOperatorCondition);
        return matchingOperator.toArray(new RecordOperator[0]);
    }

    public RecordArea getAreaBy(Integer ID) {
        RecordArea[] matchingArea = getAreaBy(new QueryCondition("ID", ID));
        if (matchingArea.length > 1)
            throw new IllegalArgumentException("Multiple areas with same ID");

        return matchingArea[0];
    }

    public RecordArea[] getAreaBy(QueryCondition condition) {
        ArrayList<QueryCondition> conditions = new ArrayList<>();
        conditions.add(condition);
        return getAreaBy(conditions);
    }

    public RecordArea[] getAreaBy(List<QueryCondition> conditions) {
        List<RecordArea> matchingArea = filterData(dataStorage.areaMap.values(),
                conditions,
                this::checkAreaCondition);
        return matchingArea.toArray(new RecordArea[0]);
    }

    public RecordWeather getWeatherBy(Integer ID) {
        RecordWeather[] matchingWeather = getWeatherBy(new QueryCondition("ID", ID));
        if (matchingWeather.length > 1)
            throw new IllegalArgumentException("Multiple weather records with same ID");

        return matchingWeather[0];
    }

    public RecordWeather[] getWeatherBy(QueryCondition condition) {
        ArrayList<QueryCondition> conditions = new ArrayList<>();
        conditions.add(condition);
        return getWeatherBy(conditions);
    }

    public RecordWeather[] getWeatherBy(List<QueryCondition> conditions) {
        List<RecordWeather> matchingWeather = filterData(dataStorage.weatherMap.values(),
                conditions,
                this::checkWeatherCondition);
        return matchingWeather.toArray(new RecordWeather[0]);
    }

    private <T> List<T> filterData(Collection<T> data,
            List<QueryCondition> conditions,
            BiFunction<T, QueryCondition, Boolean> conditionChecker) {
        List<T> matchingData = new ArrayList<>();

        for (T item : data) {

            boolean matches = false;

            for (QueryCondition condition : conditions) {
                if (condition.hasMultipleValues()) {
                    List<Object> values = (List<Object>) condition.getValue();

                    for (Object value : values) {
                        if (conditionChecker.apply(item, new QueryCondition(condition.getKey(), value))) {
                            matches = true;
                            break;
                        }
                    }
                } else if (conditionChecker.apply(item, condition)) {
                    matches = true;
                } else {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                matchingData.add(item);
            }

        }

        return matchingData;
    }

    private boolean checkCityCondition(RecordCity city, QueryCondition condition) {

        String key = condition.getKey();
        Object value = condition.getValue();

        if (key.equals("ID")) {
            Integer targetID = (Integer) value;
            return city.ID().equals(targetID);

        } else if (key.equals("name")) {
            String targetName = (String) value;
            return city.name().equals(targetName);

        } else if (key.equals("ASCIIName")) {
            String targetASCIIName = (String) value;
            return city.ASCIIName().equals(targetASCIIName);

        } else if (key.equals("countryCode")) {
            String targetCountryCode = (String) value;
            return city.countryCode().equals(targetCountryCode);

        } else if (key.equals("countryName")) {
            String targetCountryName = (String) value;
            return city.countryName().equals(targetCountryName);

        } else if (key.equals("latitude")) {
            double targetLatitude = (double) value;
            return Math.abs(city.latitude() - targetLatitude) < generateEpsilon(targetLatitude);

        } else if (key.equals("longitude")) {
            double targetLongitude = (double) value;
            return Math.abs(city.longitude() - targetLongitude) < generateEpsilon(targetLongitude);
        }

        throw new IllegalArgumentException("Invalid key");

    }

    private boolean checkOperatorCondition(RecordOperator operator, QueryCondition condition) {

        String key = condition.getKey();
        Object value = condition.getValue();

        if (key.equals("ID")) {
            Integer targetID = (Integer) value;
            return operator.ID().equals(targetID);

        } else if (key.equals("nameSurname")) {
            String targetNameSurname = (String) value;
            return operator.nameSurname().equals(targetNameSurname);

        } else if (key.equals("taxCode")) {
            String targetTaxCode = (String) value;
            return operator.taxCode().equals(targetTaxCode);

        } else if (key.equals("email")) {
            String targetEmail = (String) value;
            return operator.email().equals(targetEmail);

        } else if (key.equals("username")) {
            String targetUsername = (String) value;
            return operator.username().equals(targetUsername);

        } else if (key.equals("password")) {
            String targetPassword = (String) value;
            return operator.password().equals(targetPassword);

        } else if (key.equals("areaID")) {
            Integer targetAreaID = (Integer) value;
            return operator.areaID().equals(targetAreaID);
        }

        throw new IllegalArgumentException("Invalid key");

    }

    private boolean checkAreaCondition(RecordArea area, QueryCondition condition) {

        String key = condition.getKey();
        Object value = condition.getValue();

        if (key.equals("ID")) {
            Integer targetID = (Integer) value;
            return area.ID().equals(targetID);

        } else if (key.equals("areaName")) {
            String targetAreaName = (String) value;
            return area.areaName().equals(targetAreaName);

        } else if (key.equals("streetName")) {
            String targetStreetName = (String) value;
            return area.streetName().equals(targetStreetName);

        } else if (key.equals("streetNumber")) {
            String targetStreetNumber = (String) value;
            return area.streetNumber().equals(targetStreetNumber);

        } else if (key.equals("CAP")) {
            String targetCAP = (String) value;
            return area.CAP().equals(targetCAP);

        } else if (key.equals("townName")) {
            String targetTownName = (String) value;
            return area.townName().equals(targetTownName);

        } else if (key.equals("districtName")) {
            String targetDistrictName = (String) value;
            return area.districtName().equals(targetDistrictName);

        } else if (key.equals("cityID")) {
            Integer targetCityID = (Integer) value;
            Integer[] areaCityIDs = area.cityIDs();

            for (int i = 0; i < areaCityIDs.length; i++) {
                if (targetCityID.equals(areaCityIDs[i])) {
                    return true;
                }
            }
        }

        throw new IllegalArgumentException("Invalid key");

    }

    private boolean checkWeatherCondition(RecordWeather weather, QueryCondition condition) {

        String key = condition.getKey();
        Object value = condition.getValue();

        if (key.equals("ID")) {
            Integer targetID = (Integer) value;
            return weather.ID().equals(targetID);

        } else if (key.equals("areaID")) {
            Integer targetAreaID = (Integer) value;
            return weather.areaID().equals(targetAreaID);

        } else if (key.equals("cityID")) {
            Integer targetCityID = (Integer) value;
            return weather.cityID().equals(targetCityID);

        } else if (key.equals("date")) {
            String targetDate = (String) value;
            return weather.date().equals(targetDate);
        }

        throw new IllegalArgumentException("Invalid key");
    }

    public static double generateEpsilon(double value) {
        int decimalPlaces = computeDecimalPlaces(value);

        // Calculate epsilon as 1 / (10^decimalPlaces) for appropriate precision
        double epsilon = 1.0 / Math.pow(10, decimalPlaces);

        return epsilon;
    }

    public static int computeDecimalPlaces(double value) {
        String text = Double.toString(Math.abs(value));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = 0;

        if (integerPlaces > 0) {
            decimalPlaces = text.length() - integerPlaces - 1;
        }

        return decimalPlaces;
    }

    public static class QueryCondition {
        private String key;
        private Object value;

        public QueryCondition(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public boolean hasMultipleValues() {
            return value instanceof List;
        }
    }

}
