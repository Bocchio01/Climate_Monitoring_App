package src.models.record;

import src.utils.ENV;

public record RecordCity(
        Integer ID,
        String name,
        String ASCIIName,
        String countryCode,
        String countryName,
        double latitude,
        double longitude) {

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                name,
                ASCIIName,
                countryCode,
                countryName,
                String.valueOf(latitude),
                String.valueOf(longitude)
        };

        return String.join(ENV.CSV_SEPARATOR, dataStrings);
    }
}
