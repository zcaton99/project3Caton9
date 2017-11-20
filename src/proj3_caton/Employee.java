package proj3_caton;

public class Employee {
    private String firstName;
    private String lastName;
    private String userType;
    private String username;
    private String phoneNumber;
    private String password;
    private String email;


    String getUserType(){
        return userType;
    }
    
    String getPhoneNumber(){
        return phoneNumber;
    }
    
    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getUsername() {
        return username;
    }

    String getPassword(boolean a) {
        if (a)
            return password;
        else
            return "";
    }

    String getEmail() {
        return email;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public Employee(String fName, String lName, String uType, String uName, String Pword, String Email, String pNumber) {
        this.firstName = fName;
        this.lastName = lName;
        this.userType = uType;
        this.username = uName;
        this.password = Pword;
        this.email = Email;
        this.phoneNumber = pNumber;
    }
    public Employee(String lengthy){
        String[] user = lengthy.split(",");
        this.firstName = user[0];
        this.lastName = user[1];
        this.userType = user[2];
        this.username = user[3];
        this.password = user[4];
        this.email = user[5];
        this.phoneNumber = user[6];

    }

    public Employee() {
        this.firstName = "John";
        this.lastName = "doe";
        this.username = "S";
        this.password = "A";
        this.userType = "SalesAssociate";
        this.email = "J@J.com";
        this.phoneNumber = "867-867-5309";
    }
    

    public String toStringTest(){
        return "First Name: " +firstName + ", Last Name: " + lastName + " User Type:"+userType+", Username:" +
                username+ ", Password:" + password + ", Email:" + email + ", Phone Number:" + phoneNumber;
    }
    @Override
    public String toString(){
        return firstName+","+lastName+","+userType+","+username+","+password+","+email+","+phoneNumber;
    }
    
}


