package Company.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateAppt {

    @FXML private TextField updateAppointment_ID;
    @FXML private TextField updateTitle;
    @FXML private TextField updateLocation;
    @FXML private TextField updateType;
    @FXML private TextArea updateDescription;
    @FXML private DatePicker updateStartDate;
    @FXML private DatePicker updateEndDate;
    @FXML private ComboBox<String> updateStartTime;
    @FXML private ComboBox<String> updateEndTime;
    @FXML private ComboBox<String> updateUser_ID;
    @FXML private ComboBox<String> updateCustomer_ID;
    @FXML private ComboBox<String> updateContact;
    @FXML private Button updateSaveAddAppt;
    @FXML private Button updateCancelAppt;


    public void updateSaveApptOnClick(ActionEvent actionEvent){

    }

    public void updateCancelApptOnClick(ActionEvent actionEvent){

    }
}
