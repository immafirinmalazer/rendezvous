package SetHoursController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetHoursControllerTest {
    private SetHoursController cont;
    private static final String[] VALID_DAYS = new String[]{"M", "TH"};
    private static final String VALID_START_TIME = "08:30";
    private static final String VALID_END_TIME = "15:45";
    private static final String SUCCESS_MSG = "Saved Hours Successfully";
    private static final String ERROR_MSG = "Invalid Input";

    @BeforeEach
    void init() {
        cont = new SetHoursController();
    }

    @AfterEach
    void destroy() {
        cont = null;
    }

    @Test
    void setHours_T1() {
        assertEquals(cont.setHours(VALID_START_TIME, VALID_END_TIME, VALID_DAYS), SUCCESS_MSG);
    }
    @Test
    void setHours_T2() {
        assertEquals(cont.setHours(VALID_START_TIME, VALID_END_TIME, new String[0]), ERROR_MSG);
    }

    @Test
    void validateInput_ValidConstants() {
        assertTrue(cont.validateInput(VALID_START_TIME, VALID_END_TIME, VALID_DAYS));
    }
    @Test
    void validateInput_Start1() {
        assertFalse(cont.validateInput("5:30", VALID_END_TIME, VALID_DAYS));
    }
    @Test
    void validateInput_Start2() {
        assertTrue(cont.validateInput("05:30", VALID_END_TIME, VALID_DAYS));
    }
    @Test
    void validateInput_Start3() {
        assertFalse(cont.validateInput("05:70", VALID_END_TIME, VALID_DAYS));
    }
    @Test
    void validateInput_Start4() {
        assertFalse(cont.validateInput("25:30", VALID_END_TIME, VALID_DAYS));
    }
    @Test
    void validateInput_End1() {
        assertFalse(cont.validateInput(VALID_START_TIME, "9:30", VALID_DAYS));
    }
    @Test
    void validateInput_End2() {
        assertTrue(cont.validateInput(VALID_START_TIME, "15:30", VALID_DAYS));
    }
    @Test
    void validateInput_End3() {
        assertFalse(cont.validateInput(VALID_START_TIME, "15:70", VALID_DAYS));
    }
    @Test
    void validateInput_End4() {
        assertFalse(cont.validateInput(VALID_START_TIME, "25:30", VALID_DAYS));
    }
    @Test
    void validateInput_Days1() {
        String[] days = {"M", "T", "W", "TH", "F", "S", "SU"};
        assertTrue(cont.validateInput(VALID_START_TIME, VALID_END_TIME, days));
    }
    @Test
    void validateInput_Days2() {
        String[] days = {};
        assertFalse(cont.validateInput(VALID_START_TIME, VALID_END_TIME, days));
    }
    @Test
    void validateInput_Days3() {
        String[] days = {"M", "T", "W", "TH", "F", "S", "X"};
        assertFalse(cont.validateInput(VALID_START_TIME, VALID_END_TIME, days));
    }
    @Test
    void validateInput_Days4() {
        String[] days = {"X"};
        assertFalse(cont.validateInput("5:30", "10:30", days));
    }

    @Test
    void validateInput_Range1() {
        assertTrue(cont.validateInput("05:30", "15:30", VALID_DAYS));
    }

    @Test
    void validateInput_Range2() {
        assertFalse(cont.validateInput("05:30", "05:30", VALID_DAYS));
    }

    @Test
    void validateInput_Range3() {
        assertFalse(cont.validateInput("05:30", "04:30", VALID_DAYS));
    }
}