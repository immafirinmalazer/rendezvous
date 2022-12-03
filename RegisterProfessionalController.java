package RegisterProfessionalController;
// Carson Emery
// CS 3354.007
// 10/28/2022

import DBManager.DBManager;

public class RegisterProfessionalController {
    
    // Mockup database of company names already registered with the scheduler
    private String[] companyDatabase = {"Google", "Apple", "Microsoft"};
    
    private final int MIN_PASS_LENGTH = 8;
    private final int MAX_PASS_LENGTH = 12;
    
    private final String exceptAcctError = "Error: Account already registered with this Google account.";
    private final String invalidPassError = "Error: Password does not meet length requirements.";
    private final String exceptPassError = "Error: Password cannot include control characters or spaces.";
    private final String invalidRetypeError = "Error: Password and retyped passwords do not match.";
    private final String invalidCompanyError = "Error: Company name already taken.";
    
    private final String passwordPrompt = "Enter and retype password (8-12 long):";
    private final String coNamePrompt = "Enter company name:";
    private final String registrationSuccess = "Account has been registered!";
    private final DBManager db = new DBManager();
    
    // Checks if the Google account has an account with the scheduler yet
    public String checkIfRegistered(String email) {
        if (db.verifyGmail(email)) {
            return passwordPrompt;
        }
        return exceptAcctError;
    }
    
    // Makes sure the password meets the criteria, and the retyped password is the same
    public String validatePasswords(String pass, String retype) {
        // Verifies the password meets the length requirements
        if (pass.length() < MIN_PASS_LENGTH || pass.length() > MAX_PASS_LENGTH) {
            return invalidPassError;
        }
        
        // Makes sure there are no control characters or spaces/whitespace in the password
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isISOControl(pass.charAt(i)) || Character.isWhitespace(pass.charAt(i))) {
                return exceptPassError;
            }
        }
        
        // Verifies the retyped password is the same as the main password
        if (!pass.equals(retype)) return invalidRetypeError;
        
        return coNamePrompt;
    }
    
    // Makes sure the company name is not taken
    public String validateCoName(String company) {
        if (db.verifyCompany(company)) {
            return registrationSuccess;
        }
        
        return invalidCompanyError;
    }
    
}
