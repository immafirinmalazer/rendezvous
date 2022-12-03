package RegisterClientAccount;

public class RegisterClientAccount {
    public static final int MIN_LENGTH_GMAIL = 16;
    public static final int MAX_LENGTH_GMAIL = 40;
    
    public static final int MIN_LENGTH_PWD = 8;
    public static final int MAX_LENGTH_PWD = 12;
    
    public static final int MIN_LENGTH_FIRSTNAME = 2;
    public static final int MAX_LENGTH_FIRSTNAME = 20;
    
    public static final int MIN_LENGTH_LASTNAME = 2;
    public static final int MAX_LENGTH_LASTNAME = 20;
    
    public static String gmailAddressStr = "@gmail.com";
    public static String[] registeredGmails = {"abc123@gmail.com", "stephiehurtz@gmail.com", "tommy89@gmail.com", "olduser@gmail.com"};
    public static char[] badChars = {' ', ',', '/', '\\', '&', '=', '_', '-', '+', '<', '>'};
    public static String[] reservedGmails = {"postmaster@gmail.com", "abuse@gmail.com"};
    
    public static String lastTenChars;
    
    public static boolean checkGmailLength(String argGmailID){
        if(MIN_LENGTH_GMAIL <= argGmailID.length()  &&  argGmailID.length() <= MAX_LENGTH_GMAIL)
            return true;
        else
            return false;
    }//end checkGmailLength()
    
    public static boolean checkIfGmail(String argGmailID){
        lastTenChars = argGmailID.substring(argGmailID.length() - 10,argGmailID.length());
        
        if(!lastTenChars.equals(gmailAddressStr))
            return false;
        else
            return true;
    }//end checkIfGmail()
    
    public static boolean checkGmailRegistered(String argGmailID){
        for(String id : registeredGmails) {
            if(argGmailID.equals(id))
                return true;
        }
        return false;
    }//end checkGmailRegistered
    
    public static boolean checkGmailBadChars(String argGmailID){
        for(char c : badChars){
            if(argGmailID.indexOf(c) >= 0)
                return true;
        }
        
        return false;
    }//end checkGmailBadChars
    
    public static boolean checkPwdLen(String argPWD) {
        if(MIN_LENGTH_PWD <= argPWD.length()  &&  argPWD.length() <= MAX_LENGTH_PWD)
            return true;
        else
            return false;
    }// end checkPwdLen
    
    public static boolean checkPwdValidChars(String argPwd) {
        boolean validLetter = false;
        boolean validNumber = false;
        boolean validOtherChar = false;
        for (char c : argPwd.toCharArray()) {
            if (Character.isISOControl(c) || Character.isSpaceChar(c)) 
                return false;
            else if (Character.isDigit(c)) 
                validNumber = true;
             else if (Character.isLetter(c)) 
                validLetter = true;
             else 
                validOtherChar = true;
            
        }
        return( validLetter && validOtherChar && validNumber );
    }//end checkPwdValidChars
    
    public static boolean matchPwd(String pwd, String retyped){
        if( pwd.equals(retyped))
            return true;
        else
            return false;
    }//end matchPwd
    
    public static boolean checkFirstNameLen(String firstName) {
        if(MIN_LENGTH_FIRSTNAME <= firstName.length()  &&  firstName.length() <= MAX_LENGTH_FIRSTNAME)
            return true;
        else
            return false;
    }//end checkFirstNameLen
    
    public static boolean checkLastNameLen(String lastName) {
        if(MIN_LENGTH_LASTNAME <= lastName.length()  &&  lastName.length() <= MAX_LENGTH_LASTNAME)
            return true;
        else
            return false;
    }//end checkLastNameLen
    
    public static boolean checkFirstNameAlpha(String firstName) {
        for (char c : firstName.toCharArray()) {
            if( !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z'))
                return false;
        }
        return true;
    }//end checkFirstNameAlpha
    
    public static boolean checkLastNameAlpha(String lastName) {
        for (char c : lastName.toCharArray()) {
            if( !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z'))
                return false;
        }
        return true;
    }//end checkLastNameAlpha
    
    public static String registerUser(String gmailID, String pwd, String repwd, String firstName, String lastName){
        if( !checkGmailLength( gmailID ) ) {
            return "Gmail does not meet length requirements";
        }
        if( checkGmailRegistered( gmailID ) ) {
            return "Gmail is already in use";
        }
        if( checkGmailBadChars( gmailID ) ){
            return "Gmail contains invalid characters";
        }
        if( !checkIfGmail( gmailID ) ){
            return "Entry is not a gmail";
        }
        if( !checkPwdLen( pwd ) ){
            return "Password does not meet length requirements";
        }
        if( !checkPwdValidChars( pwd ) ){
            return "Password does not contain necessary additional characters";
        }
        if( !matchPwd( pwd, repwd ) ){
            return "Password and retyped password do not match";
        }
        if( !checkFirstNameLen( firstName ) ){
            return "First name does not meet length requirements";
        }
        if( !checkLastNameLen( lastName ) ){
            return "Last name does not meet length requirements";
        }
        if( !checkFirstNameAlpha( firstName ) ){
            return "First name contains non-letter characters";
        }
        if( !checkLastNameAlpha( lastName ) ){
            return "Last name contains non-letter characters";
        }
        
        return "Client Account Registration Successful";
    }
}
