package Company.Controller;

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
import Company.Model.Customers;
import Company.Model.firstLevelDivision;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {

    @FXML private TextField updateCustomerID;
    @FXML private TextField updateName;
    @FXML private TextField updateAddress;
    @FXML private TextField updatePostal;
    @FXML private TextField updateNumber;
    @FXML private ComboBox<Country> updateCountry;
    @FXML private ComboBox<firstLevelDivision> updateState;
    @FXML private Button updateSave;
    @FXML private Button updateCancel;
    @FXML private Label updateLabel;


    /** This method saves the customer to the database, and directs back to the 'CUSTOMERS' screen. It gives errors if invalid data is entered.
     *
     * @param event clicking the save button.
     * @throws IOException The exception that will be thrown in an error.
     */
    @FXML
    void updateSaveOnClick(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("The customer will be updated in the database, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {

            String customerName = updateName.getText();
            String address = updateAddress.getText();
            String postalCode = updatePostal.getText();
            String phone = updateNumber.getText();
            Country country = updateCountry.getValue();
            firstLevelDivision division = updateState.getValue();
            //int updateCustID = Integer.parseInt(updateCustomerID.getText());

            if (!customerName.isEmpty() && !address.isEmpty() && !postalCode.isEmpty() && !phone.isEmpty() && !(division == null))
            {
                DBCustomers.updateCustomer(customerName, address, postalCode, phone, division.getDivisionID(), customer.getCustomerID());

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
                stage.setScene(new Scene(scene));
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


    /** This method cancels updating the customer, and directs back to the 'CUSTOMERS' screen.
     *
     * @param event clicking the cancel button.
     * @throws IOException The exception that will be thrown in an error.
     */
    @FXML
    void updateCancelOnClick(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ARE YOU SURE?");
        alert.setContentText("This will clear all fields and cancel updating a customer, are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
           Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }


    /**
     * This method determines the content of the divisions label and populates the divisions combobox, based on the country combobox selection.
     *
     * @param event making a selection in the country combobox.
     */
    @FXML
    void onUpdateDivision(ActionEvent event)
    {

        Country country = updateCountry.getSelectionModel().getSelectedItem();

        if (country.getCountryID() == 1)
        {
            updateLabel.setText("State:");
        }
        else if (country.getCountryID() == 2)
        {
            updateLabel.setText("Sub-division:");
        }
        else if (country.getCountryID() == 3)
        {
            updateLabel.setText("Province:");
        }

        if (country.getCountryID() == 1)
        {
            updateState.setItems(DBDivisions.getUSDivisions());
        }
        else if (country.getCountryID() == 2)
        {
            updateState.setItems(DBDivisions.getUKDivisions());
        }
        else if (country.getCountryID() == 3)
        {
            updateState.setItems(DBDivisions.getCADivisions());
        }
        else
        {
            updateState.isDisabled();
        }
    }


    Customers customer;

    /**
     * This method sends the customer that is selected in the customer table in the 'VIEW CUSTOMERS' screen to the 'UPDATE CUSTOMERS' screen.
     *
     * @param customer the customer to send
     */
    public void sendCustomer(Customers customer) {

        this.customer = customer;

        //updateCustomerID.setText(Integer.toString(customer.getCustomerID()));
        updateName.setText(customer.getCustomerName());
        updateAddress.setText(customer.getCustomerAddress());

        for (Country c : updateCountry.getItems())
        {
            if(customer.getCustomerCountryID() == c.getCountryID())
            {
                updateCountry.setValue(c);
                break;
            }
        }

        Country country = updateCountry.getSelectionModel().getSelectedItem();

        if (country.getCountryID() == 1)
        {
            updateLabel.setText("State:");
        }
        else if (country.getCountryID() == 2)
        {
            updateLabel.setText("Sub-division:");
        }
        else if (country.getCountryID() == 3)
        {
            updateLabel.setText("Province:");
        }

        if (country.getCountryID() == 1)
        {
            updateState.setItems(DBDivisions.getUSDivisions());
        }
        else if (country.getCountryID() == 2)
        {
            updateState.setItems(DBDivisions.getUKDivisions());
        }
        else if (country.getCountryID() == 3)
        {
            updateState.setItems(DBDivisions.getCADivisions());
        }
        else
        {
            updateState.isDisabled();
        }

        for(firstLevelDivision d : updateState.getItems())
        {
            if(customer.getDivisionID() == d.getDivisionID())
            {
                updateState.setValue(d);
                break;
            }
        }
        updatePostal.setText(customer.getCustomerPostalCode());
        updateNumber.setText(customer.getCustomerPhoneNumber());
    }


    /**
     * This method initializes the 'ADD CUSTOMER' screen. It populates the country combobox.
     *
     * @param url the location.
     * @param resourceBundle the resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        updateCountry.setItems(DBCountries.getAllCountries());
    }
}
