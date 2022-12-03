public class RegisterUser {


    public static boolean checkPassword(String password) {
        if((password.length() < 8) || (password.length() > 12)) {
            System.out.println("Does not meet size criteria");
            return false;
        } else if (password.equals("00000000")) {
            System.out.println("Error in information");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String userMail){
        if((userMail.contains("@gmail.com"))){
            //System.out.println("Error in information.");
            return true;
        }else {
            return false;
        }
    }


}
