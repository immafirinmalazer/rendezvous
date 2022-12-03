package SetHoursController;

import DBManager.DBManager;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class SetHoursController {
    private final DBManager db = new DBManager();

    private static final String[] VALID_DAYS = {"M", "T", "W", "TH", "F", "S", "SU"};
    private static final String SUCCESS_MSG = "Saved Hours Successfully";
    private static final String ERROR_MSG = "Invalid Input";

    public SetHoursController() {}

    public String setHours(String start, String end, String[] days) {
        if (validateInput(start, end, days)) {
            db.setHours(start, end, days);
            db.saveUserAcct();
            return SUCCESS_MSG;
        }
        return ERROR_MSG;
    }

    public boolean validateInput(String start, String end, String[] days) {
        boolean daysValid; // all days in array are valid
        boolean rangeValid; // start time comes before end time

        daysValid = Arrays.stream(days).allMatch(e -> Arrays.asList(VALID_DAYS).contains(e));

        LocalTime startTime, endTime;
        try {
            startTime = LocalTime.parse(start);
        } catch (DateTimeParseException e) {
            return false;
        }

        try {
            endTime = LocalTime.parse(end);
        } catch (DateTimeParseException e) {
            return false;
        }

        rangeValid = startTime.isBefore(endTime);

        return days.length > 0 && daysValid && rangeValid;
    }
}
