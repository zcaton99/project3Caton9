
package proj3_caton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  protected static ArrayList<Employee> empList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        SysAdmin bob = new SysAdmin();
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        File usersFile = new File("users.txt");
        if(!usersFile.exists()){
            //if the file does not exsist in project it will then create it
            usersFile.createNewFile();
            System.out.println("added file");
        }
        else{genList();}

        launch(args);
    }
    public static void genList(){
        empList.clear();
        SysAdmin bob = new SysAdmin();
        empList.add(bob);
        try {
            Scanner in = new Scanner(new File("users.txt"));
            if(in.hasNext())
            while (in.hasNext()) {
                String a = in.nextLine();
                String[] broken = a.split(",");
                for (int i = 0; i < broken.length; i++) {
                    System.out.println(broken[i]+" this is the broken array in genlist");
                }
                Employee ba = new Employee(broken[0], broken[1], broken[2],broken[3], broken[4], broken[5],broken[6]);//fname lname utype uname pword email pnumber
                empList.add(ba);
                System.out.println(empList.get(0).getUsername());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //comment 
    }
}