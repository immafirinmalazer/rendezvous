
import RegisterClientController.RegisterClientController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author kanga
 */
public class RegisterClientAccount_Test {
    
    public RegisterClientAccount_Test() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void registerClient_TC1() { //basic successful case
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "passPASS555#",  "passPASS555#", "John", "Doe"),
                "Client Account Registration Successful"  );
    }

    @Test
    public void registerClient_TC2() { //gmail contains whitespace
        assertEquals( RegisterClientController.registerUser("new user @gmail.com", "passPASS555#",  "passPASS555#", "John", "Doe"),
                "Gmail contains invalid characters"  );
    }
    
    @Test
    public void registerClient_TC3() { //name contains non-letter characters
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "passPASS555#",  "passPASS555#", "John2093", "Doe"),
                "First name contains non-letter characters"  );
    }
    
    @Test
    public void registerClient_TC4() { //password is missing a necessary character
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "notpassPASS#",  "notpassPASS#", "John", "Doe"),
                "Password does not contain necessary additional characters"  );
    }

    @Test
    public void registerClient_TC5() { //password does not match retyped password
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "passPASS555#",  "notpassPA55#", "John", "Doe"),
                "Password and retyped password do not match"  );
    }
        
    @Test
    public void registerClient_TC6() { //gmail already belongs to a registered user
        assertEquals( RegisterClientController.registerUser("olduser@gmail.com", "passPASS555#",  "passPASS555#", "John", "Doe"),
                "Gmail is already in use"  );
    }
       
    @Test
    public void registerClient_TC7() { //First name doesn't meet length requirement
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "passPASS555#",  "passPASS555#", "J", "Doe"),
                "First name does not meet length requirements"  );
    }
    
    @Test
    public void registerClient_TC8() { //Password doesn't meet length requirement
        assertEquals( RegisterClientController.registerUser("newuser@gmail.com", "5",  "5", "John", "Doe"),
                "Password does not meet length requirements"  );
    }
    
    @Test
    public void registerClient_TC9() { //A non-gmail entry is entered
        assertEquals( RegisterClientController.registerUser("newuser@yahoo.com", "passPASS555#",  "passPASS555#", "John", "Doe"),
                "Entry is not a gmail"  );
}

}
