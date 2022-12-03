package ScheduleController;

import DBManager.DBManager;
import java.time.LocalTime;
import java.util.Arrays;
import java.time.format.DateTimeParseException;

public class ScheduleController {
    private final DBManager db = new DBManager();

    private static final String[] VALID_DAYS = {"M", "T", "W", "TH", "F", "S", "SU"};
    private static final String SUCCESS_MSG = "Appointment Successfully Scheduled";
    private static final String Error_MSG = "Error";
    private static final String firstName= new String();
    private static final String lastName = new String();

    public ScheduleController() {}

    public String schedule(String start, String end, String[] days, String firstName, String lastName) {
        if (validateAppointment(start, end, days)) {
            if (validateName(firstName, lastName)) {
                db.setAppointments(start, end, days, firstName, lastName);
                db.saveUserAcct();
                return SUCCESS_MSG;
            }
        }
        return Error_MSG;
    }

    public boolean validateName(String firstName, String lastName) {
        for (int i = 0; i < firstName.length(); i++) {
            char ch = firstName.charAt(i);
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                return false;
            }
        }
        for (int j = 0; j < lastName.length(); j++) {
            char ch = lastName.charAt(j);
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')){
                return false;
            }
        }
        return true;
    }

    public boolean validateAppointment(String start, String end, String[] days) {
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
