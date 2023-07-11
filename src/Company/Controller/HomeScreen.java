package Company.Controller;

import Company.Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.ZoneOffset;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import Company.Model.Customers;
import Company.DAO.DBCustomers;
import Company.DAO.DBAppt;


import java.util.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeScreen implements Initializable {

    @FXML private TableView<Appointments> appointmentTable;
    @FXML private TableColumn <Appointments, Integer> apptIDCol;
    @FXML private TableColumn <Appointments, String> titleCol;
    @FXML private TableColumn <Appointments, String> typeCol;
    @FXML private TableColumn <Appointments, String> desCol;
    @FXML private TableColumn <Appointments, String> locCol;
    @FXML private TableColumn <Appointments, Timestamp> startCol;
    @FXML private TableColumn <Appointments, Timestamp> endCol;
    @FXML private TableColumn <Appointments, Integer> contactCol;

    @FXML private TableView<Customers> customerTable;
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
        Parent scene = FXMLLoader.load(getClass().getResource("reports.fxml"));
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
        Parent scene = FXMLLoader.load(getClass().getResource("addAppts.fxml"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        radioAll.setSelected(true);

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        customer_IDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        user_IDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        appointmentTable.setItems(DBAppt.getAllAppointments());




        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));

        customerTable.setItems(DBCustomers.getAllCustomers());
    }

}
