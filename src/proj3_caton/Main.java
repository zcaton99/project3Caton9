
package proj3_caton;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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
        launch(args);
        //this is very new
    }
}