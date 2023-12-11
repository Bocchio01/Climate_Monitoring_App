package models.record;

import utils.Constants;
import utils.Functions;

public record RecordOperator(
        Integer ID,
        String nameSurname,
        String taxCode,
        String email,
        String username,
        String password,
        Integer areaID) {

    public RecordOperator performHash() {
        return new RecordOperator(
                ID,
                nameSurname,
                taxCode,
                email,
                username,
                Functions.performHash(password),
                areaID);
    }

    @Override
    public String toString() {
        String[] dataStrings = new String[] {
                ID.toString(),
                nameSurname,
                taxCode,
                email,
                username,
                password,
                areaID == null ? Constants.EMPTY_STRING : areaID.toString()
        };

        return String.join(Constants.CSV_SEPARATOR, dataStrings);
    }
}
