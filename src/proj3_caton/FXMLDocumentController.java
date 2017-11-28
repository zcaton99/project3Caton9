package proj3_caton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author Josh
 */
public class FXMLDocumentController implements Initializable {
    private SalesAssociate steve;
    @FXML
    private TextField partInfo;
    @FXML
    private TextField testbpDS;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private TextArea display;
    @FXML
    private Button changescene;
    @FXML
    private Button Login;
    @FXML
    private Button Return;
    @FXML
    private Button rt;
    @FXML
    private TextField PartName;
    @FXML
    private TextField PartNumber;
    @FXML
    private TextField LoadFileName;
    @FXML
    private TextField Quantity;
    @FXML
    private Button logOut;
    @FXML
    private Button createAccountButton;
    @FXML
    private TextField DFile;
    @FXML
    private TextField TypeTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField deleteUserTextField;
    @FXML
    private TextField resetUserTextField;
    @FXML
    private TextField resetPasswordTextField;
    @FXML
    private TextField ordername;
    @FXML
    private TextField ordercount;
    @FXML
    private Button orderbutton;
    @FXML
    private TextField orderlowset;
    @FXML
    CheckBox nam;
    @FXML
    private CheckBox num;
    @FXML
    private CheckBox quant;
    @FXML
    private TextArea SAOut;

    //todo attempt further compression
    //This Employee is used to store the currently logged in employee
    private Employee usersName;//todo decide how we want to use the list of users that we have to create specific employees
    private ArrayList<BikePart> bpDS = new ArrayList<>();

    /**
     * This is the handler for the sales associate sell function
     *
     * @param event the button click
     * @throws Exception any number of exceptions could be thrown here
     * @author Joseph Bermingham
     */
    @FXML
    void Sell(ActionEvent event) throws Exception {
        try {
            //steve.Sell();
            System.out.println("This is the sales assc selling things: " + SalesAssc.toString());
            if ((PartName.getText().isEmpty())) {
                SAOut.appendText(SalesAssc.Sell("", Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText())));

            } else if ((PartNumber.getText().isEmpty())) {
                SAOut.appendText(SalesAssc.Sell(PartName.getText(), -1, Integer.parseInt(Quantity.getText())));
            } else if (!(PartNumber.getText().isEmpty() && PartName.getText().isEmpty())) {
                SAOut.appendText(SalesAssc.Sell(PartName.getText(), Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText())));
            } else {
                SAOut.appendText("Please put in either a name or a number");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the handler for the sales associate adding something to their van
     *
     * @param event the button being clicked
     * @author Joseph Bermingham
     */
    @FXML
    void LoadFIle(ActionEvent event) {
        System.out.println(SalesAssc.toString());
        try {
           SAOut.appendText(SalesAssc.LoadFile(LoadFileName.getText()));
        } catch (java.io.IOException e) {
            //e.printStackTrace();
            System.out.println("File Not Found");
        }

    }

    /**
     * This is the handler for the print invoice command. it only needs to call invoice.close on the Sales associate you want an invoice from
     *
     * @param event on button press
     */

    @FXML
    void PrintInvoice(ActionEvent event) {
        System.out.println("This is PrintInvoice");
        SalesAssc.closeinvoice("Clients Name");
    }

    /**
     * @author Josh Butler
     * This is how the fxml changes scenes from any user with a return button back to the login screen
     */
    @FXML
    private void changeScene(ActionEvent event) throws IOException {
        usersName = null;//should reset
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == Return) {
            //get reference to the button's stage
            stage = (Stage) Return.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        } else {
            System.out.println("Error");
        }
        //create a new scene with root and set the stage
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception in line 145 of controller");
        }
    }

    /**
     * THis is the action that occurs when you click the logon button
     *
     * @param event the click
     * @throws IOException When there is an io exception
     * @author Josh Butler,Joseph Bermingham
     */

    private static SalesAssociate SalesAssc;
    private static officeManager OfficeMan;//todo implement office manager fxml bond
    private final SysAdmin sysAdmin = new SysAdmin();
    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;
        //todo have these actually create a user and set the employee field to their actual job so we can access it
        String username = Username.getText();
        String password = Password.getText();

        if (username.equals("om")) {
            stage = (Stage) Login.getScene().getWindow();       //will be deleting this if statement
            root = FXMLLoader.load(getClass().getResource("OfficeManager.fxml")); //only here so i can test officemanager while i figure out how to create user
        }
        for (Employee e : Main.empList) {
            //testline statements that are no longer needed
//            System.out.println("i entered the foreach loop " + e.toString());
//            System.out.println("this is username,pwd " + username + ", " + password);
//            System.out.println("This is what e has" + e.getUsername() + ", " + e.getPassword(true));
            if (e.getUsername().equalsIgnoreCase(username) && e.getPassword(true).equalsIgnoreCase(password)) {
                System.out.println(e.getUserType() + " this is a printline test statement");
                if (e.getUserType().equalsIgnoreCase("systemAdmin")) {
                    stage = (Stage) Login.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("SysAdmin.fxml"));
                }
                if (e.getUserType().equalsIgnoreCase("officeManager")) {
                    //get reference to the button's stage
                    OfficeMan = new officeManager(e.toString());
                    stage = (Stage) Login.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficeManager.fxml"));
                }
                if (e.getUserType().equalsIgnoreCase("salesAssociate")) {
                    stage = (Stage) Login.getScene().getWindow();
                    //load up OTHER FXML document
                    SalesAssc = new SalesAssociate(e.toString());
                    System.out.println(SalesAssc + " This is sales associate creatin in login");
                    root = FXMLLoader.load(getClass().getResource("SalesAssociate.fxml"));
                    //  System.out.println(root.getId() + " the sales assc root id");
                }
                if (e.getUserType().equalsIgnoreCase("WarehouseManager")) {
                    stage = (Stage) Login.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("WarehouseManager.fxml"));
                }
            }
        }
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException excep) {
            // excep.printStackTrace();
            System.out.println("no user by that name found");
        }
    }




    /**
     * @param event the button is clicked
     * @author Zach Canton
     * edited by joseph bermingham
     */
    @FXML
    void createAccountButton(ActionEvent event) {

        String firstName = NameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userType = TypeTextField.getText();
        System.out.println(userType + " usertype in the create account button");
        String username = lastNameTextField.getText() + NameTextField.getText(); //tells use the type of employee someone is
        String pass = passwordTextField.getText();
        String email = emailTextField.getText();
        String phoneNum = phoneNumberTextField.getText();

        sysAdmin.addUser(firstName, lastName, userType, username, pass, email, phoneNum);


    }

    /**
     * This should be the button that deletes an account that you have already added
     *
     * @param event when the button is clicked
     * @author zach caton
     */
    @FXML
    void deleteAccountButton(ActionEvent event) {
        String usernameDelete = deleteUserTextField.getText();
        //SysAdmin delAdmin = new SysAdmin();
        sysAdmin.deleteUser(usernameDelete);
    }

    /**
     * Changes the password of a user
     *
     * @param event The button is pressed
     * @author zach caton
     */
    @FXML
    void resetPasswordButton(ActionEvent event) throws FileNotFoundException {
        String usernameReset = resetUserTextField.getText();
        String passwordReset = resetPasswordTextField.getText();
        //SysAdmin resAdmin = new SysAdmin();
        sysAdmin.resetPassword(usernameReset, passwordReset);
    }

    /**
     * findByName compares all BikeParts in the warehouse data set and returns a BikePart if its name is equal to the name given.
     *
     * @param name The name of the part you are trying to find
     * @return returns BikePart b
     */
    private BikePart findByName(String name) {
        for (BikePart b : bpDS) //bpDS is the static arraylist of bikeparts
            if (b.getName().equals(name))
                return b;
        return null;
    }

    @FXML
    private void LoadToMain() {
        WarehouseManager a = new WarehouseManager();
        a.addfile(DFile.getText());
    }

    /**
     * readBPDS reads in the initial warehouse data set from a file given by the user.
     * It also displays all BikeParts in the warehouse and denotes what each value is.
     *
     * @param event on button press
     * @throws FileNotFoundException not sure why alll of the methods throw some thing
     * @throws IOException           i want to talk about why we do this. i can see it as really smart or not at all smart
     */
    @FXML
    private void testBPDS(ActionEvent event) throws IOException {
        String s = testbpDS.getText();
        File f = new File(s);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) { //reads the file given by the user in the first textfield
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pa = line.split(",");
                int result = Integer.parseInt(pa[1]);
                double result2 = Double.parseDouble(pa[2]);
                double result3 = Double.parseDouble(pa[3]);
                Boolean result4 = Boolean.valueOf(pa[4]);
                int result5 = Integer.parseInt(pa[5]);
                BikePart b = new BikePart(pa[0], result, result2, result3, result4, result5);

                BikePart found = findByName(pa[0]);
                if (findByName(pa[0]) == null) {         //ensures that bikeparts with unique names are added to array
                    bpDS.add(b);
                } else {                              //doesn't actually affect file, as no prints are made, only displays and initializes BPDS.
                    found.setPrice(b.getPrice());
                    found.setSalesPrice(b.getSale());
                    found.setonSale(b.getonSale());
                    found.setQuantity(b.getQuantity());
                }
                display.appendText("Part Name: " + pa[0] + "," + " Part Number: " + result + "," + " Price: $" + result2 + "," + " Sales Price: $" + result3 + "," + " On Sale: " + result4 + "," + " Quantity: " + result5 + "\n");
            }
        } catch (FileNotFoundException fnfe) {
            display.appendText(fnfe.getMessage());
        }
    }


    /**
     * @param event on button press
     * @author Josh Butler
     * Displays a specific BikePart by comparing user input to BikePart name, number or a quantity parameter (e.g. >3 or <15).
     */
    @FXML
    public void examineButtonMethod(ActionEvent event) throws IOException {
        officeManager om = new officeManager("a", "b", "bb", "c", "d", "email", "867-867-5309");
        File f = new File(testbpDS.getText());
        officeManager.bpDS.clear();
        om.updateBPDS(f);
        display.appendText("\n");
        if (num.isSelected())
            display.appendText((om.examineButtonMethodNum(Integer.parseInt((partInfo.getText())), officeManager.bpDS))); // if user opts to sort by number
        else if (quant.isSelected()) {  //had to put implementation here because returning once is not the goal for when asking for any parts less than or greater
            String text = partInfo.getText();
            String text2 = partInfo.getText();
            text = text.replaceAll("[<]", ""); // after we have determined if the user has typed a less than or greater than symbol, we ignore the symbols for use.
            text2 = text.replaceAll("[>]", "");
            if ((partInfo.getText().contains(">"))) {
                for (BikePart bp : officeManager.bpDS) {
                    if (bp.getQuantity() > Integer.parseInt(text2)) {
                        if (bp.getonSale())
                            display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                        else
                            display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
                    }
                }
            } else if ((partInfo.getText().contains("<"))) {
                for (BikePart bp : officeManager.bpDS) {
                    if (bp.getQuantity() < Integer.parseInt(text)) {
                        if (bp.getonSale())
                            display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                        else
                            display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
                    }
                }
            } else
                display.appendText((om.examineButtonMethodQuant(Integer.parseInt((text)), officeManager.bpDS)));
        } else
            display.appendText((om.examineButtonMethodname(partInfo.getText(), officeManager.bpDS))); //if user does not choose to sort by quantity or number, this is the default [name]
    }

    /**
     * @param event //todo addInv the ability to create a file of the needed parts
     * @throws java.lang.InterruptedException
     * @throws java.io.IOException
     * @author Josh Butler
     * this method goes through the bike part warehouse and looks for parts with less than a hardcoded quantity (10)
     */
    @FXML
    public void checkQuant(ActionEvent event) throws InterruptedException, IOException {
        officeManager om = new officeManager("a", "b", "bb", "c", "d", "email", "867-867-5309");
        File f = new File(testbpDS.getText());
        officeManager.bpDS2.clear();
        officeManager.bpDS.clear();
        om.updateBPDS(f);
        display.appendText("\n");

        for (BikePart bp : officeManager.bpDS) {
            if (bp.getQuantity() <= 10 && !Arrays.asList(officeManager.bpDS2).contains(bp)) { //checks to see if the value already exists in the array, if not it can be added. (prevents duplicates)
                officeManager.bpDS2.add(bp);
                if (orderlowset.getText().equals("")) {
                    display.appendText("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " it is recommended to order more." + "\n");
                }
            }
        }
        if (officeManager.bpDS2.isEmpty()) {
            display.appendText("\n" + "No parts are below 10, you can check for other values by using the examine function and using the < symbol.");
        }
        if (!officeManager.bpDS2.isEmpty() && (orderlowset.getText().equals(""))) {
            display.appendText("\n" + "1. You may order a set number of all low parts by entering" + "\n" + "the amount in the \"Order Low\" field and selecting \"Check Qauntities\" again." + "\n" + "2. Alternatively, you may order individually to set different amounts per item." + "\n");
        }
        if (!officeManager.bpDS2.isEmpty() && (!orderlowset.getText().equals(""))) {
            display.appendText("Bought " + orderlowset.getText() + " of each part below 10." + "\n");
        }
        if (!orderlowset.getText().isEmpty()) {
            for (BikePart bp : officeManager.bpDS2) {
                om.orderParts(bp, Integer.parseInt(orderlowset.getText()));
            }
        }
    }

    /**
     * @param event on button press
     * @author Josh Butler
     * increments quantity of ordered part
     */
    @FXML
    public void order(ActionEvent event) throws IOException { //TODO: edit so that bpDS is updated without having to reload file, (clear bpds, insert code from testbpds, exlude prints.)
        officeManager om = new officeManager("a", "b", "bb", "c", "d", "email", "867-867-5309");
        File f = new File(testbpDS.getText());
        officeManager.bpDS.clear();
        om.updateBPDS(f);
        for (BikePart bp : officeManager.bpDS) {
            if (bp.getNumber() == Integer.parseInt(ordername.getText())) {
                display.appendText("\n" + "Bought " + ordercount.getText() + " " + bp.getName() + "\n");
                om.orderParts(bp, Integer.parseInt(ordercount.getText()));
            }
        }
    }

    /**
     * @param event on button press
     * @author Josh Butler
     * exits the program on click
     */
    @FXML
    public void Quit(ActionEvent event) {
        System.exit(1);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}