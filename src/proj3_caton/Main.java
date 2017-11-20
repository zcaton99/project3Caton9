
package proj3_caton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    protected static ArrayList<Employee> empList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        SysAdmin bob = new SysAdmin();
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        File usersFile = new File("users.txt");
        if (!usersFile.exists()) {
            //if the file does not exsist in project it will then create it
            usersFile.createNewFile();
            System.out.println("added file");
        } else {
            genList();
        }

        launch(args);
    }

    static void genList() {
        empList.clear();
        SysAdmin bob = new SysAdmin();
        empList.add(bob);
        try {
            Scanner in = new Scanner(new File("users.txt"));
            if (in.hasNext())
                while (in.hasNext()) {
                    String a = in.nextLine();
                    System.out.println("This is a in genList: " +a);
                    String[] broken = a.split(",");
                    System.out.println("This is broken[2]: "+broken[2]);
                    if(broken[2].equalsIgnoreCase("salesassociate")){
                        empList.add(new SalesAssociate(broken[0], broken[1], broken[2], broken[3], broken[4], broken[5], broken[6]));
                    }
                    else if(broken[2].equalsIgnoreCase("warehouseManager")){
                        empList.add(new WarehouseManager(broken[0], broken[1], broken[2], broken[3], broken[4], broken[5], broken[6]));
                    }
                    else if(broken[2].equalsIgnoreCase("officemanager")){
                        empList.add(new officeManager(broken[0], broken[1], broken[2], broken[3], broken[4], broken[5], broken[6]));
                    }
                    else {
                        System.out.println("massive error in main, in.hasNext is true, but it was not cast as a specific employee");
                    }
                   //fname lname utype uname pword email pnumber
                }
//            for (Employee e:empList) {
//                System.out.println(e.toString()+"this is in genlist");
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}