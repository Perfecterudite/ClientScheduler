package Company.Controller;

import Company.ConnectionDB.DatabaseConn;
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private TableColumn <Appointments, LocalDateTime> startCol;
    @FXML private TableColumn <Appointments, LocalDateTime> endCol;
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
    @FXML private Button exit;


    public void reportOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void exitOnClick(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will exit the screen, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        /**if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("View/mainLogin.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }**/

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ((Button)(event.getSource())).getScene().getWindow().hide();
        }

    }
    @FXML
    public void onViewAll(ActionEvent event)
    {
        appointmentTable.setItems(DBAppt.getAllAppointments());
    }

    /**
     * This method shows current month appointments in the table.
     *
     * @param event clicking on the 'CURRENT MONTH' radio button.
     */
    @FXML
    public void onViewByMonth(ActionEvent event)
    {
        appointmentTable.setItems(DBAppt.getMonthAppointments());
    }

    /**
     * This method shows current week appointments in the table.
     *
     * @param event clicking on the 'CURRENT WEEK' radio button.
     */
    @FXML
    public void onViewByWeek(ActionEvent event)
    {
        appointmentTable.setItems(DBAppt.getWeekAppointments());
    }

    public void addApptOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("addAppts.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();



    }

    public void updateApptOnClick(ActionEvent event) throws IOException{

        if (appointmentTable.getSelectionModel().isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PLEASE SELECT AN APPOINTMENT");
            alert.setContentText("No appointment was selected to update.");

            Optional<ButtonType> result = alert.showAndWait();
        }

        else
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateAppt.fxml"));
            loader.load();

            UpdateAppt ADMController = loader.getController();
            ADMController.sendAppointment(appointmentTable.getSelectionModel().getSelectedItem());


            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    public void deleteApptOnClick(ActionEvent event) throws IOException{

        if (appointmentTable.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PLEASE SELECT AN APPOINTMENT");
            alert.setContentText("No appointment was selected to delete.");

            Optional<ButtonType> result = alert.showAndWait();
        }
        else
        {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("ARE YOU SURE?");
            alert.setContentText("The appointment will be deleted from the database, are you sure you want to continue? This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                int appointmentId = appointmentTable.getSelectionModel().getSelectedItem().getApptID();

                String typeMessage = appointmentTable.getSelectionModel().getSelectedItem().getApptType();

                String idMessage = String.valueOf(appointmentTable.getSelectionModel().getSelectedItem().getApptID());

                DBAppt.deleteAppointment(appointmentId);

                appointmentTable.setItems(DBAppt.getAllAppointments());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("DELETED");
                alert2.setContentText("You have successfully deleted appointment " + idMessage + ", a " + typeMessage + " appointment.");

                alert2.showAndWait();
            }
            else
            {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("NOT DELETED");
                alert3.setContentText("The selected appointment was not deleted.");

                alert3.showAndWait();
            }
        }
    }

    public void addCustomerOnClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void updateCustomerOnClick(ActionEvent event) throws IOException{

        if (customerTable.getSelectionModel().isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PLEASE SELECT A CUSTOMER.");
            alert.setContentText("No customer was selected to update.");

            Optional<ButtonType> result = alert.showAndWait();
        }

        else
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateCustomer.fxml"));
            loader.load();

            UpdateCustomer ADMController = loader.getController();
            ADMController.sendCustomer(customerTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }
    public void deleteCustomerOnClick(ActionEvent event) throws IOException{

        if (customerTable.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PLEASE SELECT A CUSTOMER.");
            alert.setContentText("No customer was selected to delete.");

            Optional<ButtonType> result = alert.showAndWait();
        }

        else
        {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("ARE YOU SURE?");
            alert.setContentText("The customer will be deleted from the database, are you sure you want to continue? This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                int customerId = customerTable.getSelectionModel().getSelectedItem().getCustomerID();

                DBCustomers.deleteCustomer(customerId);

                customerTable.setItems(DBCustomers.getAllCustomers());



                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("DELETED");
                alert2.setContentText("The selected customer was successfully deleted.");

                alert2.showAndWait();
            }
            else
            {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("NOT DELETED");
                alert3.setContentText("The selected customer was not deleted.");

                alert3.showAndWait();
            }
        }
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
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
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
