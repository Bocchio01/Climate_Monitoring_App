package models.record;

import models.record.RecordWeather.WeatherData;
import utils.Constants;

public record RecordWeather(
        Integer ID,
        Integer cityID,
        Integer areaID,
        String date,
        WeatherData wind,
        WeatherData humidity,
        WeatherData pressure,
        WeatherData temperature,
        WeatherData precipitation,
        WeatherData glacierElevation,
        WeatherData glacierMass) {

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                cityID.toString(),
                areaID.toString(),
                date,
                wind.toString(),
                humidity.toString(),
                pressure.toString(),
                temperature.toString(),
                precipitation.toString(),
                glacierElevation.toString(),
                glacierMass.toString()
        };

        return String.join(Constants.CSV_SEPARATOR, dataStrings);
    }

    public record WeatherData(
            Integer score,
            String comment) {

        @Override
        public String toString() {
            return (score != null ? Integer.toString(score)
                    : Constants.EMPTY_STRING) +
                    Constants.CSV_SUB_SEPARATOR +
                    comment;
        }
    }
}
