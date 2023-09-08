package src.models.record;

import src.models.record.RecordWeather.WeatherData;
import src.utils.ENV;

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

        return String.join(ENV.CSV_SEPARATOR, dataStrings);
    }

    public record WeatherData(
            Integer score,
            String comment) {

        @Override
        public String toString() {
            return (score != null ? Integer.toString(score)
                    : ENV.EMPTY_STRING) +
                    ENV.CSV_SUB_SEPARATOR +
                    comment;
        }
    }
}