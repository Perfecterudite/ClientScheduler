package Company.Controller;

import Company.DAO.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import Company.Model.Contacts;
import Company.Model.Customers;
import Company.Model.Users;
import Company.Model.Appointments;
import Company.DAO.DBAppt;
import Company.DAO.DBContacts;
import Company.DAO.DBUsers;
import java.io.IOException;
import java.util.ResourceBundle;

public class AddAppmt  implements Initializable{

    @FXML private TextField Appointment_ID;
    @FXML private TextField title;
    @FXML private TextField location;
    @FXML private TextField type;
    @FXML private TextArea description;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<LocalTime> startTime;
    @FXML private ComboBox<LocalTime> endTime;
    @FXML private ComboBox<Users> user_ID;
    @FXML private ComboBox<Customers> customer_ID;
    @FXML private ComboBox<Contacts> contact;
    @FXML private Button saveAddAppt;
    @FXML private Button cancelAppt;



    /** This method saves the appointment to the database, and directs back to the 'APPOINTMENTS' screen. It gives errors if invalid data is entered.
     *
     * clicking the save button.
     * @throws IOException The exception that will be thrown in an error.
     */

    public void saveApptOnClick(ActionEvent event) throws IOException{


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will add a new Appointment to the calendar");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            //int apptID = Appointment_ID.getText();
            Customers customer_Id = customer_ID.getValue();
            String titleTx = title.getText();
            String desc = description.getText();
            String loca = location.getText();
            Contacts contacts = contact.getValue();
            String typeTx = type.getText();
            LocalDate Sdate = startDate.getValue();
            LocalDate Edate = endDate.getValue();

            LocalTime st = startTime.getValue();
            LocalTime et = endTime.getValue();
            Users userId = user_ID.getValue();


            if (contacts!=null && !typeTx.isEmpty() && Sdate!=null && st!=null && et!=null && customer_Id != null && userId!=null)
            {

                Timestamp start = Timestamp.valueOf(LocalDateTime.of( Sdate, startTime.getValue()));
                Timestamp end = Timestamp.valueOf(LocalDateTime.of( Edate, endTime.getValue()));

                //LocalDateTime start = LocalDateTime.Of(Sdate, startTime.getValue());
                //LocalDateTime end = LocalDateTime.of( Edate, endTime.getValue());

                //LocalDateTime;
                //int cId = customer_Id;


                if (LocalDateTime.of(Edate, endTime.getValue()).isAfter(LocalDateTime.of(Sdate, startTime.getValue())))
                {

                    Appointments newAppointment = new Appointments(Integer.parseInt("0"), titleTx, desc, loca, typeTx, start, end, customer_Id.getCustomerID(), userId.getUserID(), contacts.getContactID());


                    if (DBAppt.checkForOverlappingAppointment(newAppointment))
                    {

                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setHeaderText("APPOINTMENT OVERLAP");
                        alert3.setContentText("Appointment overlaps with an existing appointment for the selected customer.");
                        alert3.showAndWait();
                    }
                    else {

                        DBAppt.addAppointment(titleTx, desc, loca, typeTx, start, end, customer_Id.getCustomerID(), userId.getUserID(), contacts.getContactID());


                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
                else
                {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setHeaderText("TIME ERROR");
                    alert3.setContentText("Appointment end time must be after appointment start time.");
                    alert3.showAndWait();
                }

            }
            else
            {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setHeaderText("INVALID ENTRIES");
                alert3.setContentText("Please enter a valid value for each required field!");
                alert3.showAndWait();
            }
        }
    }


    /** This method cancels adding the appointment, and directs back to the 'APPOINTMENTS' screen.
     *
     * clicking the cancel button.
     * @throws IOException The exception that will be thrown in an error.
     */

    public void cancelApptOnClick(ActionEvent event)throws IOException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will clear all fields and cancel adding an appointment, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();


        }

    }


    /**
     * This method initializes the 'ADD APPOINTMENT' screen. It populates the customer table, populates the contact and user comboboxes, and handles the conversion of times between local time and EST.
     *
     * @param url the location.
     * @param resourceBundle the resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        contact.setItems(DBContacts.getAllContacts());
        user_ID.setItems(DBUsers.getAllUsers());
        customer_ID.setItems(DBCustomers.getAllCustomers());




        LocalTime appointmentStartTimeMinEST = LocalTime.of(10, 0);
        LocalDateTime startMinEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMinEST);
        ZonedDateTime startMinZDT = startMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMinLocal = startMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMin = startMinLocal.toLocalTime();

        LocalTime appointmentStartTimeMaxEST = LocalTime.of(22, 40);
        LocalDateTime startMaxEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMaxEST);
        ZonedDateTime startMaxZDT = startMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMaxLocal = startMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMax = startMaxLocal.toLocalTime();

        while (appointmentStartTimeMin.isBefore(appointmentStartTimeMax.plusSeconds(1)))
        {
            startTime.getItems().add(appointmentStartTimeMin);
            appointmentStartTimeMin = appointmentStartTimeMin.plusMinutes(15);
        }


        LocalTime appointmentEndTimeMinEST = LocalTime.of(10, 15);
        LocalDateTime endMinEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMinEST);
        ZonedDateTime endMinZDT = endMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMinLocal = endMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMin = endMinLocal.toLocalTime();

        LocalTime appointmentEndTimeMaxEST = LocalTime.of(23, 0);
        LocalDateTime endMaxEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMaxEST);
        ZonedDateTime endMaxZDT = endMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMaxLocal = endMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMax = endMaxLocal.toLocalTime();

        while (appointmentEndTimeMin.isBefore(appointmentEndTimeMax.plusSeconds(1)))
        {
            endTime.getItems().add(appointmentEndTimeMin);
            appointmentEndTimeMin = appointmentEndTimeMin.plusMinutes(15);
        }





    }
}

