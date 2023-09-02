package src.models.record;

import src.utils.ENV;

public record WeatherRecord(
        Integer ID,
        Integer areaID,
        Integer cityID,
        String date,
        WeatherData wind,
        WeatherData humidity,
        WeatherData pressure,
        WeatherData temperature,
        WeatherData precipitation,
        WeatherData glacierElevation,
        WeatherData massOfGlaciers) {

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                areaID.toString(),
                cityID.toString(),
                date,
                wind.toString(),
                humidity.toString(),
                pressure.toString(),
                temperature.toString(),
                precipitation.toString(),
                glacierElevation.toString(),
                massOfGlaciers.toString()
        };

        return String.join(ENV.CSV_SEPARATOR, dataStrings);
    }

    public record WeatherData(
            Integer score,
            String comment) {

        @Override
        public String toString() {
            return Integer.toString(score) + ENV.CSV_SUB_SEPARATOR + comment;
        }
    }
}
