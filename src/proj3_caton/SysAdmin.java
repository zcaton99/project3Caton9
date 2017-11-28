package proj3_caton;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zachcaton
 */
class SysAdmin extends Employee {
    //String firstName, String lstName, String userName, String Password, String Email, String phonenum
    
    public ArrayList<Employee> users = new ArrayList<>();
    //This must already exist or it creates null pointer exceptions
    private File userFile = new File("users.txt");//This is the file that lists users
    boolean firstTime = true;
    //todo make it so that first time isnt always false?
    
    SysAdmin() {
        super("a", "b", "SystemAdmin", "d", "d", "ff", "g");
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
        Employee em = new Employee(user[0], user[1], userType, user[3], user[4], user[5], user[6]);
     //   System.out.println(em.getUserType()+" this is em's user type");
        
        
        
        //userFile = new File(fileName);
        // first time is changed to false but when adding another user is goes back to true 
        //if first time adding user then adds them directly
        //however if the program is closed and reopened then this will run again and override the file information
                
        
        
//        if (firstTime && users.isEmpty()){
//            users.add(em);// System.out.println("user was added on the 64th line");
//            firstTime = false;
//            System.out.println(firstTime + "The first time that a user is added to the file in sys admin 99");
//            
//        // not first time but has no users in system (aka users are in file)    
//        }
        if(firstTime && users.isEmpty()){
            
            Scanner in = null;
            try {
                    in = new Scanner(userFile);
            } catch (FileNotFoundException e) {
                 System.out.println("File Not Found in sysAdmin addUser, Please consult the Author");
            }
                //tests to see if user file has any users in it
            if(in.hasNext()) {
                    //adds users back into program
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] pA = line.split(",");
                    Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4], pA[5], pA[6]);
                    users.add(ba);
                    firstTime = false;
                    System.out.println(firstTime + "1");
                    System.out.println(users.toString());
                }
                users.add(em);
                
            }else{
                users.add(em);
                firstTime = false;
                System.out.println(firstTime + "2");
                System.out.println(users.toString());
            }
            
            
        }else if(!firstTime && users.isEmpty()){// end of first time if statement
            //checks to see if users is empty, if it is then it will read in users from the file
            Scanner inVF = null;
            //reading in users file
            try {
                inVF = new Scanner(userFile);
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
            
        }else if (!firstTime && users.isEmpty() == false){//end of else if for first time
            System.out.println(firstTime + " This is the first time, and is always false at this point");
            Employee ep = findByName(user[0]);
        
            if (ep == null){
                users.add(em);
            }else{
                //names are the same
                System.out.println("already exists");
            }
        }
      //  System.out.println(users+" this is users");
        //after everything is complete it writes the users back into the file
        try {
            PrintWriter p = new PrintWriter(new FileWriter(userFile));//todo removing the appending from this thing to see if it removes duplication
            for(Employee usr: users){
                //p.append(usr.toString());
                //p.println(usr.toString());
                p.println(usr.toString());
            }
            p.close();
        }catch (FileNotFoundException e){
            System.out.println("File not Found exception in the end of SysAdmin.addUser");
        }
        catch(IOException d){
            d.printStackTrace();
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
        //userFile = new File(fileName);
        
        
        if (users.isEmpty()) {
            
            try {
                inVF = new Scanner(userFile);
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
            //File userFile = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(userFile);
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
            //File userFile = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(userFile);//todo make sure that this doesnt need to append
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

        //File userFile = new File(fileName);
        try (PrintWriter p = new PrintWriter(userFile)) {
            for (Employee usr : users) {
                p.println(usr.toString());
            }
            p.close();
        }

    }

}