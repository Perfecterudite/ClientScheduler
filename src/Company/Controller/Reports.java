package Company.Controller;

import Company.DAO.DBCountries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import Company.DAO.DBAppt;
import Company.DAO.DBContacts;
//import Company.DAO.DBCountries;
import Company.Model.Contacts;
import Company.Model.Country;
import Company.Model.Appointments;
import Company.Model.Report;
import Company.Model.ReportType;
import Company.Model.ReportMonth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Month;
import java.util.Collections;

public class Reports{
    @FXML private ComboBox <String> selectContact;
    @FXML private TableColumn <?, ?> reportIDCol;
    @FXML private TableColumn <?, ?> reportTitleCol;
    @FXML private TableColumn <?, ?> reportTypeCol;
    @FXML private TableColumn <?, ?> reportDesCol;
    @FXML private TableColumn <?, ?> reportLocCol;
    @FXML private TableColumn <?, ?> reportStartCol;
    @FXML private TableColumn <?, ?> reportEndCol;
    @FXML private TableColumn <?, ?> reportContactCol;
    @FXML private TableColumn <?, ?> reportCusIDCol;
    @FXML private TableColumn <?, ?> apptMonthCol;
    @FXML private TableColumn <?, ?> apptTypeCol;
    @FXML private TableColumn <?, ?> totalApptCol;
    @FXML private TableColumn <?, ?> divisionNameCol;
    @FXML private TableColumn <?, ?> totalCusCol;
    @FXML private TableView<Appointments> reportsTable;
    @FXML private TableView<ReportType> reportsTypeTable;
    @FXML private TableView<ReportMonth> reportsDivTable;
    @FXML private Button backBtn;
    @FXML private Button logoutBtn;



    public void initialize() throws SQLException {

        reportIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        reportTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        reportDesCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        reportLocCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        reportTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportStartCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        reportEndCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        divisionNameCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        reportCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        reportContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        totalApptCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));
        apptMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        totalCusCol.setCellValueFactory(new PropertyValueFactory<>("customerTotal"));

        ObservableList<Contacts> contactsObservableList = DBContacts.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        selectContact.setItems(allContactsNames);

    }


    /**
     * Fill fxml form with appointment data by contact.
     */
    @FXML
    public void appointmentDataByContact() {
        //try {

            int contactID = 0;

            ObservableList<Appointments> getAllAppointmentData = DBAppt.getAllAppointments();
            ObservableList<Appointments> appointmentInfo = FXCollections.observableArrayList();
            ObservableList<Contacts> getAllContacts = DBContacts.getAllContacts();

            Appointments contactAppointmentInfo;

            String contactName = selectContact.getSelectionModel().getSelectedItem();

            for (Contacts contact: getAllContacts) {
                if (contactName.equals(contact.getContactName())) {
                    contactID = contact.getContactID();
                }
            }

            for (Appointments appointment: getAllAppointmentData) {
                if (appointment.getContactID() == contactID) {
                    contactAppointmentInfo = appointment;
                    appointmentInfo.add(contactAppointmentInfo);
                }
            }
            reportsTable.setItems(appointmentInfo);

        /**} catch (SQLException throwables) {
            throwables.printStackTrace();
        }**/
    }

    /**
     * Total number of customer appointments by type and month report.
     * @throws SQLException
     */
    public void appointmentTotalsTab() throws SQLException {
        try {
            ObservableList<Appointments> getAllAppointments = DBAppt.getAllAppointments();
            ObservableList<Month> appointmentMonths = FXCollections.observableArrayList();
            ObservableList<Month> monthOfAppointments = FXCollections.observableArrayList();

            ObservableList<String> appointmentType = FXCollections.observableArrayList();
            ObservableList<String> uniqueAppointment = FXCollections.observableArrayList();

            ObservableList<ReportType> reportType = FXCollections.observableArrayList();
            ObservableList<ReportMonth> reportMonths = FXCollections.observableArrayList();


            //IDE converted to Lambda
            getAllAppointments.forEach(appointments -> {
                appointmentType.add(appointments.getApptType());
            });

            //IDE converted to Lambda
            /**getAllAppointments.stream().map(appointment -> {
                return appointment.getStart().getMonth();
            }).forEach(appointmentMonths::add);**/

            //IDE converted to Lambda
            appointmentMonths.stream().filter(month -> {
                return !monthOfAppointments.contains(month);
            }).forEach(monthOfAppointments::add);

            for (Appointments appointments: getAllAppointments) {
                String appointmentsAppointmentType = appointments.getApptType();
                if (!uniqueAppointment.contains(appointmentsAppointmentType)) {
                    uniqueAppointment.add(appointmentsAppointmentType);
                }
            }

            for (Month month: monthOfAppointments) {
                int totalMonth = Collections.frequency(appointmentMonths, month);
                String monthName = month.name();
                ReportMonth appointmentMonth = new ReportMonth(monthName, totalMonth);
                reportMonths.add(appointmentMonth);
            }
            reportsDivTable.setItems(reportMonths);

            for (String type: uniqueAppointment) {
                String typeToSet = type;
                int typeTotal = Collections.frequency(appointmentType, type);
                ReportType appointmentTypes = new ReportType(typeToSet, typeTotal);
                reportType.add(appointmentTypes);
            }
            reportsTypeTable.setItems(reportType);

        } catch (Exception e) {
            e.printStackTrace();
        }
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




    public void logoutOnClick(ActionEvent actionEvent){
    }

}
