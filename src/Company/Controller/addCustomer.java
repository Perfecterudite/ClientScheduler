package Company.Controller;


import Company.Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import Company.DAO.DBCountries;
import Company.DAO.DBCustomers;
import Company.DAO.DBDivisions;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Company.Model.Country;
import Company.Model.firstLevelDivision;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;


public class addCustomer implements Initializable{
    @FXML private TextField customerID;
    @FXML private TextField customerName;
    @FXML private TextField address;
    @FXML private TextField postalCode;
    @FXML private TextField phoneNumber;
    @FXML private ComboBox<Country> countryBox;
    @FXML private ComboBox<firstLevelDivision> stateProvince;
    @FXML private Label divisionSwitch;
    @FXML private Button saveCustomer;
    @FXML private Button cancelCustomer;

    public void saveOnClick(ActionEvent event) throws IOException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("The new customer will be added to the database, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            int ID = 0;
            for (Customers cust : DBCustomers.getAllCustomers()) {

                if (cust.getCustomerID() > ID)

                    ID = cust.getCustomerID();

            }

            customerID.setText(String.valueOf(++ID));
            String custName = customerName.getText();
            String addressTxt = address.getText();
            String postalCodeTxt = postalCode.getText();
            String phone = phoneNumber.getText();
            Country country = countryBox.getValue();
            firstLevelDivision division = stateProvince.getValue();

            if (!custName.isEmpty() && !addressTxt.isEmpty() && !postalCodeTxt.isEmpty() && !phone.isEmpty() && !(division == null))
            {
                DBCustomers.addCustomer(custName, addressTxt, postalCodeTxt, phone, division.getDivisionID());


                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object nscene= FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
                stage.setScene(new Scene((Parent) nscene));
                stage.show();
            }
            else
            {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setHeaderText("BLANK FIELDS");
                alert3.setContentText("Please enter a valid value for each field! All fields are required.");
                alert3.showAndWait();
            }
        }

    }

    public void cancelOnClick(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will clear all fields and cancel adding a customer, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }


   public void divisionOnClick(ActionEvent event) {
        Country country = countryBox.getSelectionModel().getSelectedItem();

        if (country.getCountryID() == 1)
        {
            divisionSwitch.setText("State:");
        }
        else if (country.getCountryID() == 2)
        {
            divisionSwitch.setText("Sub-division:");
        }
        else if (country.getCountryID() == 3)
        {
            divisionSwitch.setText("Province:");
        }


        if (country.getCountryID() == 1)
        {
            stateProvince.setItems(DBDivisions.getUSDivisions());
        }
        else if (country.getCountryID() == 2)
        {
            stateProvince.setItems(DBDivisions.getUKDivisions());
        }
        else if (country.getCountryID() == 3)
        {
            stateProvince.setItems(DBDivisions.getCADivisions());
        }
        else
        {
            stateProvince.isDisabled();
        }
    }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
           countryBox.setItems(DBCountries.getAllCountries());
       //Lambda Expression - Listener for combo box change
       countryBox.valueProperty().addListener((obs, oldVal, newVal) -> {
           if (newVal == null) {
               stateProvince.getItems().clear();
               stateProvince.setDisable(true);

           }
           else {
               stateProvince.setDisable(false);
           }
       });

   }
   
    /**
     * This method initializes the 'ADD CUSTOMER' screen. It populates the country combobox, and clears the contents of the division combobox.
     *
     * @param url the location.
     * @param resourceBundle the resources.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        countryBox.setItems(DBCountries.getAllCountries());
        stateProvince.getSelectionModel().clearSelection();
    }**/
}
