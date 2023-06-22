package Company;

import javafx.application.Application;
import Company.ConnectionDB.DatabaseConn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainLogin.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        DatabaseConn.startConnection();
        launch(args);
        DatabaseConn.endConnection();
    }
}
