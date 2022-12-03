import RegisterProfessionalController.RegisterProfessionalController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterProfessionalTest {
    private RegisterProfessionalController system;
    
    private final String exceptAcctError = "Error: Account already registered with this Google account.";
    private final String invalidPassError = "Error: Password does not meet length requirements.";
    private final String exceptPassError = "Error: Password cannot include control characters or spaces.";
    private final String invalidRetypeError = "Error: Password and retyped passwords do not match.";
    private final String invalidCompanyError = "Error: Company name already taken.";
    
    private final String passwordPrompt = "Enter and retype password (8-12 long):";
    private final String coNamePrompt = "Enter company name:";
    private final String registrationSuccess = "Account has been registered!";
    
    @BeforeEach
    void setUp() {
        system = new RegisterProfessionalController();
    }
    
    @AfterEach
    void tearDown() {}
    
    @Test
    void allValid() {           // Test Case 1: All inputs are valid
        String msg = system.checkIfRegistered("johndavis@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("ValidPass1", "ValidPass1");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Rendezvous");
        assertEquals(msg, registrationSuccess);
    }
    
    @Test
    void invalidCompany() {     // Test Case 2: Company name is invalid
        String msg = system.checkIfRegistered("johndavis@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("ValidPass1", "ValidPass1");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Google");
        assertEquals(msg, invalidCompanyError);
    }
    
    @Test
    void invalidRetyped() {     // Test Case 3: Retyped password is invalid
        String msg = system.checkIfRegistered("johndavis@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("ValidPass1", "OtherPass1");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Rendezvous");
        assertEquals(msg, invalidRetypeError);
    }
    
    @Test
    void invalidPass() {        // Test Case 5: Password is invalid
        String msg = system.checkIfRegistered("johndavis@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("123", "123");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Rendezvous");
        assertEquals(msg, invalidPassError);
    }
    
    @Test
    void exceptPass() {         // Test Case 9: Password is exceptional
        String msg = system.checkIfRegistered("johndavis@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("Space  Pass1", "Space  Pass1");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Rendezvous");
        assertEquals(msg, exceptPassError);
    }
    
    @Test
    void exceptionalGAcct() {       // Test Case 25: Google account already registered to system
        String msg = system.checkIfRegistered("abc1234@gmail.com");
        if (msg.equals(passwordPrompt)) msg = system.validatePasswords("ValidPass1", "ValidPass1");
        if (msg.equals(coNamePrompt)) msg = system.validateCoName("Rendezvous");
        assertEquals(msg, exceptAcctError);
    }
    
}