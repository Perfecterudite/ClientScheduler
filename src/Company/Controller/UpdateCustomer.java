package Company.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UpdateCustomer {

    @FXML private TextField updateCustomerID;
    @FXML private TextField updateName;
    @FXML private TextField updateAddress;
    @FXML private TextField updatePostal;
    @FXML private TextField updateNumber;
    @FXML private ComboBox<String> updateCountry;
    @FXML private ComboBox<String> updateState;
    @FXML private Button updateSave;
    @FXML private Button updateCancel;

    public void updateSaveOnClick(ActionEvent actionEvent){

    }

    public void updateCancelOnClick(ActionEvent actionEvent){

    }
}
