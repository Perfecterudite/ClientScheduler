package Company.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddAppmt {

    @FXML private TextField Appointment_ID;
    @FXML private TextField title;
    @FXML private TextField location;
    @FXML private TextField type;
    @FXML private TextArea description;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
    @FXML private ComboBox<String> user_ID;
    @FXML private ComboBox<String> customer_ID;
    @FXML private ComboBox<String> contact;
    @FXML private Button saveAddAppt;
    @FXML private Button cancelAppt;


    public void saveApptOnClick(ActionEvent actionEvent){

    }

    public void cancelApptOnClick(ActionEvent actionEvent){

    }
}

