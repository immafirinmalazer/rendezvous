package ScheduleController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleControllerTest {
    private static final String[] VALID_DAYS = new String[]{"T", "W"};
    private static final String VALID_START_TIME = "08:30";
    private static final String VALID_END_TIME = "15:45";
    private static final String SUCCESS_MSG = "Appointment Successfully Scheduled";
    private static final String ERROR_MSG = "Error";
    private static final String ERROR_NAME = "!!!";
    private ScheduleController cont;

    @BeforeEach
    void init() {
        cont = new ScheduleController();
    }

    @AfterEach
    void destroy() {
        cont = null;
    }

    @Test
    void schedule_T1() {
        assertEquals(cont.schedule(VALID_START_TIME, VALID_END_TIME, VALID_DAYS, "John", "Doe"), SUCCESS_MSG);
    }

    @Test
    void schedule_T2() {
        assertEquals(cont.schedule(VALID_START_TIME, VALID_END_TIME, new String[0], "John", "Doe"), ERROR_MSG);
    }

    @Test
    void schedule_T3() {
        assertEquals(cont.schedule(VALID_START_TIME, VALID_END_TIME, VALID_DAYS, ERROR_NAME, "DOE"), ERROR_MSG);
    }

    @Test
    void schedule_T4() {
        assertEquals(cont.schedule(VALID_START_TIME, VALID_END_TIME, VALID_DAYS, "John", ERROR_NAME), ERROR_MSG);
    }

    @Test
    void schedule_T5() {
        assertTrue(cont.validateAppointment(VALID_START_TIME, VALID_END_TIME, VALID_DAYS));
    }

    @Test
    void schedule_T6() {
        assertTrue(cont.validateName("John", "Doe"));
    }

    @Test
    void schedule_T7() {
        assertFalse(cont.validateAppointment("5:30", VALID_END_TIME, VALID_DAYS));
    }

    @Test
    void schedule_T8() {
        assertFalse(cont.validateAppointment(VALID_START_TIME, "9:30", VALID_DAYS));
    }

    @Test
    void schedule_T9() {
        assertEquals(cont.schedule(VALID_START_TIME, VALID_END_TIME, VALID_DAYS, ERROR_NAME, ERROR_NAME), ERROR_MSG);
    }


    @Test
    void validateInput_Range1() {
        assertTrue(cont.validateAppointment("05:30", "15:30", VALID_DAYS));
    }

    @Test
    void validateInput_Range2() {
        assertFalse(cont.validateAppointment("05:30", "05:30", VALID_DAYS));
    }

    @Test
    void validateInput_Range3() {
        assertFalse(cont.validateAppointment("05:30", "04:30", VALID_DAYS));
    }


}
