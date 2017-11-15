
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
        try {
            Scanner in = new Scanner(new File("users.txt"));
            while (in.hasNext()) {
                String a = in.next();
                String[] broken = a.split(",");
                String[] utype = broken[2].split(broken[0]);
                System.out.println(utype[0]);
                Employee ba = new Employee(broken[0], broken[1], broken[2],utype[0], broken[4], broken[5], broken[6]);
                empList.add(ba);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}