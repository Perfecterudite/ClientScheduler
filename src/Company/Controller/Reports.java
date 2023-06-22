package Company.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    //@FXML private TableView<Company.Model.Report> reportsTable;
    //@FXML private TableView<Company.Model.Report> reportsTypeTable;
    //@FXML private TableView<Company.Model.Report> reportsDivTable;
    @FXML private Button backBtn;
    @FXML private Button logoutBtn;

    public void backOnClick(ActionEvent actionEvent){

    }

    public void logoutOnClick(ActionEvent actionEvent){
    }

}
