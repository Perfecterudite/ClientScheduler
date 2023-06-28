package Company.Controller;

import javafx.event.ActionEvent;
import Company.Model.Report;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeScreen {

    @FXML private TableColumn <?, ?> apptIDCol;
    @FXML private TableColumn <?, ?> titleCol;
    @FXML private TableColumn <?, ?> typeCol;
    @FXML private TableColumn <?, ?> desCol;
    @FXML private TableColumn <?, ?> locCol;
    @FXML private TableColumn <?, ?> startCol;
    @FXML private TableColumn <?, ?> endCol;
    @FXML private TableColumn <?, ?> contactCol;
    @FXML private TableColumn <?, ?> customer_IDCol;
    @FXML private TableColumn <?, ?> user_IDCol;
    @FXML private TableColumn <?, ?> postalCol;
    @FXML private TableColumn <?, ?> addressCol;
    @FXML private TableColumn <?, ?> NameCol;
    @FXML private TableColumn <?, ?> stateCol;
    @FXML private TableColumn <?, ?> numberCol;
    @FXML private TableColumn <?, ?> customerIDCol;
    //@FXML private TableView<Company.Model.Report> homeApptTable;
    //@FXML private TableView<Company.Model.Report> homeCustomerTable;
    @FXML private RadioButton radioCurrentWeek;
    @FXML private RadioButton radioCurrentMonth;
    @FXML private RadioButton radioAll;
    @FXML private Button addCustomer;
    @FXML private Button updateCustomer;
    @FXML private Button deleteCustomer;
    @FXML private Button addAppt;
    @FXML private Button updateAppt;
    @FXML private Button deleteAppt;
    @FXML private Button reports;
    @FXML private Button logout;


    public void reportOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("View/reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void logoutOnClick(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will log you out, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("View/mainLogin.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void addApptOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("addAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void updateApptOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("updateAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void deleteApptOnClick(ActionEvent event) throws IOException{

    }

    public void addCustomerOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void updateCustomerOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("updateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void deleteCustomerOnClick(ActionEvent event) throws IOException{

    }

}
