package models.record;

import utils.Constants;

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

        return String.join(Constants.CSV_SEPARATOR, dataStrings);
    }
}
