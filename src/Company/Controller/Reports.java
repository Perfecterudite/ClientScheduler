package Company.Controller;

import Company.DAO.DBCountries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import Company.DAO.DBAppt;
import Company.DAO.DBContacts;
import Company.DAO.DBCustomers;
import Company.Model.Contacts;
import Company.Model.Country;
import Company.Model.Appointments;
//import Company.Model.ReportType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    @FXML private ComboBox <Contacts> selectContact;
    @FXML private TableColumn <?, ?> reportIDCol;
    @FXML private TableColumn <?, ?> reportTitleCol;
    @FXML private TableColumn <?, ?> reportTypeCol;
    @FXML private TableColumn <?, ?> reportDesCol;
    @FXML private TableColumn <?, ?> reportLocCol;
    @FXML private TableColumn <?, ?> reportStartCol;
    @FXML private TableColumn <?, ?> reportEndCol;
    @FXML private TableColumn <?, ?> reportContactCol;
    @FXML private TableColumn <?, ?> reportCusIDCol;
    @FXML private TableView<Appointments> reportsTable;
    @FXML private ComboBox<String> monthCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private Label reportResult;
    @FXML private Label custTotal;
    @FXML private Button backBtn;
    @FXML private Button logoutBtn;





    public void initialize(URL url, ResourceBundle resourceBundle) {

        reportIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        reportTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        reportDesCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        reportLocCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        reportTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportStartCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        reportEndCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        reportCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        reportContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        reportsTable.setItems(DBAppt.getAllAppointments());
        monthCombo.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        typeCombo.setItems(DBAppt.getAllTypes());
        selectContact.setItems(DBContacts.getAllContacts());


    }



    /**
     * This method the runs the first report, displaying the number of appointments by month and type.
     *
     * @param event clicking on the button for running the report.
     */
    @FXML
    void onActionRun(ActionEvent event)
    {
        String month = monthCombo.getValue();
        if (month == null)
        {
            return;
        }

        String type = typeCombo.getValue();
        if (type == null)
        {
            return;
        }

        int total = DBAppt.getMonthAndTypeCount(month, type);

        reportResult.setText(String.valueOf(total));
    }



    /**
     * This method the runs the second report, displaying the appointment information for a specific contact.
     *
     * Discussion of lambda - I implemented a lambda expression to facilitate the filtering of the appointments list by contact id to find all appointments that have the specific contact listed.
     *
     * @param event clicking on the button for running the report.
     */
    @FXML
    void onSelectContact(ActionEvent event)
    {
        Contacts contact = selectContact.getValue();

        if (contact == null)
        {
            return;
        }
        ObservableList<Appointments> aList = DBAppt.getAllAppointments();
        ObservableList<Appointments> cList = aList.filtered(ap ->
        {

            if (ap.getContactID() == contact.getContactID()) {
                return true;
            }
            return false;

        });

        reportsTable.setItems(cList);
    }

    /**
     * This method the runs the third report, displaying the total number of appointments.
     *
     * @param event clicking on the button for running the report.
     */
    @FXML
    void onTotalCustomerRun(ActionEvent event)
    {
        custTotal.setText(String.valueOf(DBCustomers.getAllCustomers().size()));
    }


    /**
     * Button to go back to main menu.
     * @throws IOException
     */
    @FXML
    public void backOnClick (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }


}
