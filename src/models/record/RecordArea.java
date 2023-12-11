package models.record;

import java.util.Arrays;
import java.util.stream.Collectors;

import utils.Constants;

public record RecordArea(
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

            String cityIDsString = Arrays.stream(cityIDs)
            .map(Object::toString)
            .collect(Collectors.joining(Constants.CSV_SUB_SEPARATOR));

        String[] dataStrings = new String[] {
                Integer.toString(ID),
                areaName,
                streetName,
                streetNumber,
                CAP,
                townName,
                districtName,
                cityIDsString};

        return String.join(Constants.CSV_SEPARATOR, dataStrings);
    }
}
