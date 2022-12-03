package DBManager;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DBManager {
    private String gmail = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String companyName = "";
    private String type = "";
    private final ArrayList<Object[]> hours = new ArrayList<>();
    
    public void setGmail(String userGmail) {
        gmail = userGmail;
    }
    
    public void setPassword(String userPwd) {
        password = userPwd;
    }
    
    public void setFirstName(String userFN) {
        firstName = userFN;
    }
    
    public void setLastName(String userLN) {
        lastName = userLN;
    }
    
    public void setCompanyName(String userCo) {
        companyName = userCo;
    }
    
    public void setUserType(String userType) {
        type = userType;
    }

    public void setHours(String start, String end, String[] days) {
        hours.add(new Object[]{start, end, days});
    }
    public void saveUserAcct() {
        try {
            File file = new File("RDV_database.txt");
            if (!file.exists()) file.createNewFile();

            PrintWriter outFile = new PrintWriter("RDV_database.txt");
            
            outFile.print(type + " ");
            outFile.print(gmail + " ");
            outFile.print(password + " ");
            
            if (type.equals("client")) {
                outFile.print(firstName + " ");
                outFile.print(lastName + "\n");
            }
            else {
                outFile.print(companyName + " ");
                for (Object[] hour : hours) {
                    outFile.print(hour[0] + " ");   // start
                    outFile.print(hour[1] + " ");   // end
                    String[] days = (String[])hour[2];  // days
                    for (String day : days) {
                        outFile.print(day + " ");
                    }
                }
            }
            
            outFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Returns true if the gmail account is unused, returns false otherwise.
    public boolean verifyGmail(String account) {
        try {
            File file = new File("RDV_database.txt");
            if (!file.exists()) return true;   // If the file doesn't exist, the user's data can't be stored in it
            
            Scanner fileScan = new Scanner(file);
            
            // Checks all of the gmail addresses in the database
            while (fileScan.hasNextLine()) {
                fileScan.next();
                if (account.equals(fileScan.next())) {
                    return false;
                }
                fileScan.nextLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    // Returns true if the gmail account is unused, returns false otherwise.
    public boolean verifyCompany(String company) {
        try {
            File file = new File("RDV_database.txt");
            if (!file.exists()) return true;   // If the file doesn't exist, the user's data can't be stored in it
            
            Scanner fileScan = new Scanner(file);
            
            // Checks all of the gmail addresses in the database
            while (fileScan.hasNextLine()) {
                if (fileScan.next().equals("professional")) {
                    fileScan.next();
                    fileScan.next();
                    if (company.equals(fileScan.next())) {
                        return false;
                    }
                }
                if (fileScan.hasNext()) fileScan.nextLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
