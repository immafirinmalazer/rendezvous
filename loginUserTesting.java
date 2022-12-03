import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUser_BlackBox_Testing {

    @Test
    void validate_transfer_TC1() {
        System.out.println("TEST1");
        assertEquals(true, RegisterUser.checkPassword("JohnSmith123"));
        assertEquals(true, RegisterUser.checkEmail("JohnSmith@gmail.com"));
    }

    @Test
    void validate_transfer_TC2() {
        System.out.println("TEST2");
        assertEquals(false, RegisterUser.checkPassword("John"));
        assertEquals(true, RegisterUser.checkEmail("JohnSmith@gmail.com"));
    }
    @Test
    void validate_transfer_TC3() {
        System.out.println("TEST3");
        assertEquals(true, RegisterUser.checkPassword("JohnSmith123"));
        assertEquals(false, RegisterUser.checkEmail("JohnSmirth"));
    }
    @Test
    void validate_transfer_TC4() {
        System.out.println("TEST4");
        assertEquals(false, RegisterUser.checkPassword("00000000"));
        assertEquals(true, RegisterUser.checkEmail("JohnSmith@gmail.com"));
    }
    @Test
    void validate_transfer_TC5() {
        System.out.println("TEST5");
        assertEquals(true, RegisterUser.checkPassword("JohnSmith123"));
        assertEquals(false, RegisterUser.checkEmail("JohnSmith@yahoo.com"));
    }


}