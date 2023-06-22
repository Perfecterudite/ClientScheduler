package Company.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class addCustomer {
    @FXML private TextField customerID;
    @FXML private TextField customerName;
    @FXML private TextField address;
    @FXML private TextField postalCode;
    @FXML private TextField phoneNumber;
    @FXML private ComboBox<String> country;
    @FXML private ComboBox<String> stateProvince;
    @FXML private Button saveCustomer;
    @FXML private Button cancelCustomer;

    public void saveOnClick(ActionEvent actionEvent){
        //This is testing something

    }

    public void cancelOnClick(ActionEvent actionEvent){

    }
}
