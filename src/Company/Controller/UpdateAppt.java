package Company.Controller;

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

public class UpdateAppt {

    @FXML private TextField updateAppointment_ID;
    @FXML private TextField updateTitle;
    @FXML private TextField updateLocation;
    @FXML private TextField updateType;
    @FXML private TextArea updateDescription;
    @FXML private DatePicker updateStartDate;
    @FXML private DatePicker updateEndDate;
    @FXML private ComboBox<LocalTime> updateStartTime;
    @FXML private ComboBox<LocalTime> updateEndTime;
    @FXML private ComboBox<Users> updateUser_ID;
    @FXML private ComboBox<Customers> updateCustomer_ID;
    @FXML private ComboBox<Contacts> updateContact;
    @FXML private Button updateSaveAddAppt;
    @FXML private Button updateCancelAppt;



    /** This method saves the appointment to the database, and directs back to the 'APPOINTMENTS' screen. It gives errors if invalid data is entered.
     *
     * @param event clicking the save button.
     * @throws IOException The exception that will be thrown in an error.
     */

   /** @FXML
    void updateSaveApptOnClick(ActionEvent event) throws IOException
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("The new appointment will be added to the calendar, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Customers customer_Id = updateCustomer_ID.getValue();
            String title = updateTitle.getText();
            String description = updateDescription.getText();
            String location = updateLocation.getText();
            Contacts contact = updateContact.getValue();
            String type = updateType.getText();
            LocalDate Stdate = updateStartDate.getValue();
            LocalDate Endate = updateEndDate.getValue();

            LocalTime st = updateStartTime.getValue();
            LocalTime et = updateEndTime.getValue();
            Users userId = updateUser_ID.getValue();
            int appointment_Id = Integer.parseInt(updateAppointment_ID.getText());

            if (contact!=null && !type.isEmpty() && Stdate!=null && Endate!=null && st!=null && et!=null && customer_Id != null && userId!=null)
            {

                Timestamp start = Timestamp.valueOf(LocalDateTime.of( Stdate, updateStartTime.getValue()));
                Timestamp end = Timestamp.valueOf(LocalDateTime.of( Endate, updateEndTime.getValue()));
                //int cId = Integer.parseInt(customer_Id);

                if (LocalDateTime.of(Endate, updateEndTime.getValue()).isAfter(LocalDateTime.of(Stdate, updateStartTime.getValue())))
                {
                    Appointments newAppointment = new Appointments(appointment_Id, title, description, location, contact.getContactID(), contact.getContactName(), type, start, end, cId, userId.getUserId());

                    if (DBAppt.checkForOverlappingAppointment(newAppointment))
                    {

                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setHeaderText("APPOINTMENT OVERLAP");
                        alert3.setContentText("Appointment overlaps with an existing appointment for the selected customer.");
                        alert3.showAndWait();
                    }
                    else {

                        DBAppt.updateAppointment(title, description, location, type, start, end, cId, userId.getUserId(), contact.getContactId(), appointment_Id);

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
    }**/

    /** This method cancels updating the appointment, and directs back to the 'APPOINTMENTS' screen.
     *
     * @param event clicking the cancel button.
     * @throws IOException The exception that will be thrown in an error.
     */
    @FXML
    void updateCancelApptOnClick(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("Are you sure you want to cancel updating the appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
           Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }



    Appointments appointment;

    /**
     * This method sends the appointment that is selected in the appointment table in the 'VIEW APPOINTMENTS' screen to the 'UPDATE APPOINTMENT' screen.
     *
     * @param appointment the appointment to send
     */
    /**public void sendAppointment(Appointments appointment)
    {

        this.appointment = appointment;

        appointmentIdText.setText(Integer.toString(appointment.getAppointmentId()));
        titleText.setText(appointment.getTitle());
        descriptionText.setText(appointment.getDescription());
        locationText.setText(appointment.getLocation());

        for (Contact c : contactComboBox.getItems()) {
            if (appointment.contactId == c.getContactId()) {
                contactComboBox.setValue(c);
                break;
            }
        }

        typeText.setText(appointment.getType());

        LocalTime setStart = appointment.getStart().toLocalDateTime().toLocalTime();
        startTimeComboBox.setValue(setStart);
        LocalTime setEnd = appointment.getEnd().toLocalDateTime().toLocalTime();
        endTimeComboBox.setValue(setEnd);

        LocalDate appointmentDate = appointment.getStart().toLocalDateTime().toLocalDate();
        datePicker.setValue(appointmentDate);

        customerId.setText(String.valueOf(appointment.getCustomerId()));

        for (User u : userIdComboBox.getItems())
        {
            if (appointment.userId == u.getUserId())
            {
                userIdComboBox.setValue(u);
                break;
            }
        }
    }**/


    /**
     * This method initializes the 'UPDATE APPOINTMENT' screen. It populates the customer table, populates the contact and user comboboxes, and handles the conversion of times between local time and EST.
     *
     * @param url the location.
     * @param resourceBundle the resources.
     */
   /** @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        customerTable.setItems(DBCustomers.getAllCustomers());

        contactComboBox.setItems(DBContacts.getAllContacts());
        userIdComboBox.setItems(DBUsers.getAllUsers());

        LocalTime appointmentStartTimeMinEST = LocalTime.of(8, 0);
        LocalDateTime startMinEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMinEST);
        ZonedDateTime startMinZDT = startMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMinLocal = startMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMin = startMinLocal.toLocalTime();

        LocalTime appointmentStartTimeMaxEST = LocalTime.of(21, 45);
        LocalDateTime startMaxEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMaxEST);
        ZonedDateTime startMaxZDT = startMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMaxLocal = startMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMax = startMaxLocal.toLocalTime();

        while (appointmentStartTimeMin.isBefore(appointmentStartTimeMax.plusSeconds(1)))
        {
            startTimeComboBox.getItems().add(appointmentStartTimeMin);
            appointmentStartTimeMin = appointmentStartTimeMin.plusMinutes(15);
        }

        LocalTime appointmentEndTimeMinEST = LocalTime.of(8, 15);
        LocalDateTime endMinEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMinEST);
        ZonedDateTime endMinZDT = endMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMinLocal = endMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMin = endMinLocal.toLocalTime();

        LocalTime appointmentEndTimeMaxEST = LocalTime.of(22, 0);
        LocalDateTime endMaxEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMaxEST);
        ZonedDateTime endMaxZDT = endMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMaxLocal = endMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMax = endMaxLocal.toLocalTime();

        while (appointmentEndTimeMin.isBefore(appointmentEndTimeMax.plusSeconds(1)))
        {
            endTimeComboBox.getItems().add(appointmentEndTimeMin);
            appointmentEndTimeMin = appointmentEndTimeMin.plusMinutes(15);
        }
    }**/
}
