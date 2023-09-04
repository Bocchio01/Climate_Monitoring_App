package src.models.record;

import src.utils.ENV;

public record RecordOperator(
        Integer ID,
        String nameSurname,
        String taxCode,
        String email,
        String username,
        String password,
        Integer areaID) {

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID == null ? ENV.EMPTY_STRING : areaID.toString()
        };

        return String.join(ENV.CSV_SEPARATOR, dataStrings);
    }
}
