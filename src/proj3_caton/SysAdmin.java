package proj3_caton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zachcaton
 */
class SysAdmin extends Employee {
    //String firstName, String lstName, String userName, String Password, String Email, String phonenum
    
    public ArrayList<Employee> users = new ArrayList<>();
    public String fileName = "users.txt";
    //This must already exist or it creates null pointer exceptions
    public File f = new File(fileName);//This is the file that lists users
    boolean firstTime = true;
    
    
    SysAdmin() {
        super("a", "b", "d", "d", "e", "f", "g");
    }
    
    public Employee findByName(String name){
        for(Employee emply: users){
            if(emply.getFirstName().compareTo(name) ==0){
                return emply;
            }
        }
        return null;
    }
    
    /**
     * Adds user to user database (text file)
     *
     * @param fName       First name for the user
     * @param lName       Last name for the user
     * @param userType    The type of user
     * @param userName    what type of user this user is
     * @param password    this is users not encrypted password
     * @param email       this users email
     * @param pNumber     the phone number of this user
     */
    public void addUser(String fName, String lName, String userType, String userName, String password, String email, String pNumber) {
        
        
        //creating an employee from text fields
        String[] user = new String[7];
        user[0] = fName;
        user[1] = lName;
        user[2] = userType;
        user[3] = userName;
        user[4] = password;
        user[5] = email;
        user[6] = pNumber;
        Employee em = new Employee(user[0], user[1], user[2], user[3], user[4], user[5], user[6]);
        
        
        
        //f = new File(fileName);
        // first time is changed to false but when adding another user is goes back to true 
        //if first time adding user then adds them directly
        if (firstTime == true && users.isEmpty()){
        
            users.add(em);
            firstTime = false;
            System.out.println(firstTime + "1");
            
        // not first time but has no users in system (aka users are in file)    
        }else if(firstTime != true && users.isEmpty()){// end of first time if statement
            //checks to see if users is empty, if it is then it will read in users from the file
            
            Scanner inVF = null;
            //reading in users file
            try {
                inVF = new Scanner(f);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found in sysAdmin addUser, Please consult the Author");
            }
            //tests to see if user file has any users in it
            if(inVF.hasNext()) {
                //adds users back into program
                while (inVF.hasNext()) {
                    String line = inVF.nextLine();
                    String[] pA = line.split(",");
                    Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4], pA[5], pA[6]);
                    users.add(ba);
                }
            }
            
        }else if (firstTime == false && users.isEmpty() == false){//end of else if for first time
            System.out.println(firstTime + "2");
            Employee ep = findByName(user[0]);
        
            if (ep == null){
                users.add(em);
            }else{
                //names are the same
                System.out.println("already exists");
            }
        }
        System.out.println(users);
        //after everything is complete it writes the users back into the file
        try (PrintWriter p = new PrintWriter(f)) {
            for(Employee usr: users){
                //p.append(usr.toString());
                //p.println(usr.toString());
                p.println(usr.toString());
            }
            p.close();
        }catch (FileNotFoundException e){
            System.out.println("File not Found exception in the end of SysAdmin.addUser");
        }
        
    }
    

    /**
     * Deletes user by his/her username
     *
     * @param username The UserName of the user that you are deleting
     */
    public void deleteUser(String username) {
        //read from user.txt
        //then compare names and delete the correct one
        Scanner inVF = null;
        //f = new File(fileName);
        
        
        if (users.isEmpty()) {
            
            try {
                inVF = new Scanner(f);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found in sysAdmin addUser, Please consult the Author");
            }

            while (inVF.hasNext()) {
                String line = inVF.nextLine();
                String[] pA = line.split(",");
                
               Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4], pA[5], pA[6]);
               users.add(ba);
                 
            }
            for (int b = 0; b < users.size(); b++) {
                if (users.get(b).getUsername().compareTo(username) == 0) {

                    users.remove(b);
                }
            }
            //File f = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(f);
                for (Employee usr : users) {
                    p.println(usr.toString());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found in SysAdmin Remove user");
            }
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().compareTo(username) == 0) {
                    users.remove(i);
                }
            }
            //File f = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(f);//todo make sure that this doesnt need to append
                for (Employee usr : users) {
                    p.println(usr.toString());
                }
                p.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found in the end of SysAdmin Remove user");
            }
            if(users.isEmpty()){
                firstTime = true;
            }
        }

    }

    /**
     * Resets password by user name
     *
     * @param name    username of the user whose password you are changing
     * @param newPass the new password that you want to add
     * @throws FileNotFoundException Throws when the file of users is not found
     */
    public void resetPassword(String name, String newPass) throws FileNotFoundException {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().compareTo(name) == 0) {
                //change pass to new pass
                users.get(i).setPassword(newPass);
            }
        }

        //File f = new File(fileName);
        try (PrintWriter p = new PrintWriter(f)) {
            for (Employee usr : users) {
                p.println(usr.toString());
            }
            p.close();
        }

    }

}