package src.models.record;

import src.utils.ENV;

public record AreaRecord(
        Integer ID,
        String areaName,
        String streetName,
        String streetNumber,
        String CAP,
        String townName,
        String districtName,
        Integer[] cityIDs) {

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                areaName,
                streetName,
                streetNumber,
                CAP,
                townName,
                districtName,
                String.join(ENV.CSV_SUB_SEPARATOR, cityIDs.toString())
        };

        return String.join(ENV.CSV_SEPARATOR, dataStrings);
    }
}
