package Company.Controller;

import javafx.event.ActionEvent;
//import Company.Model.Report;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeScreen {

    @FXML private TableColumn <?, ?> apptIDCol;
    @FXML private TableColumn <?, ?> titleCol;
    @FXML private TableColumn <?, ?> typeCol;
    @FXML private TableColumn <?, ?> desCol;
    @FXML private TableColumn <?, ?> locCol;
    @FXML private TableColumn <?, ?> startCol;
    @FXML private TableColumn <?, ?> endCol;
    @FXML private TableColumn <?, ?> contactCol;
    @FXML private TableColumn <?, ?> customer_IDCol;
    @FXML private TableColumn <?, ?> user_IDCol;
    @FXML private TableColumn <?, ?> postalCol;
    @FXML private TableColumn <?, ?> addressCol;
    @FXML private TableColumn <?, ?> NameCol;
    @FXML private TableColumn <?, ?> stateCol;
    @FXML private TableColumn <?, ?> numberCol;
    @FXML private TableColumn <?, ?> customerIDCol;
    //@FXML private TableView<Company.Model.Report> homeApptTable;
    //@FXML private TableView<Company.Model.Report> homeCustomerTable;
    @FXML private RadioButton radioCurrentWeek;
    @FXML private RadioButton radioCurrentMonth;
    @FXML private RadioButton radioAll;
    @FXML private Button addCustomer;
    @FXML private Button updateCustomer;
    @FXML private Button deleteCustomer;
    @FXML private Button addAppt;
    @FXML private Button updateAppt;
    @FXML private Button deleteAppt;
    @FXML private Button reports;
    @FXML private Button logout;


    public void reportOnClick(ActionEvent actionEvent){

    }

    public void logoutOnClick(ActionEvent actionEvent){

    }

    public void addApptOnClick(ActionEvent actionEvent){

    }

    public void updateApptOnClick(ActionEvent actionEvent){
    }
    public void deleteApptOnClick(ActionEvent actionEvent){

    }

    public void addCustomerOnClick(ActionEvent actionEvent){

    }

    public void updateCustomerOnClick(ActionEvent actionEvent){
    }
    public void deleteCustomerOnClick(ActionEvent actionEvent){

    }

}
