package Company.Controller;


import javafx.event.ActionEvent;
import Company.DAO.DBAppt;
import Company.DAO.DBUsers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Parent;


import Company.Appointments;
//import model.LogonSession;
import java.sql.SQLException;
import java.util.*;


import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;


import java.util.Optional;

import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class LoginPage{

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label locationName;
    @FXML private Label locationLabel;
    @FXML private Button loginBtn;
    private String confirmSure;
    private String confirmExit;
    private String invalidLoginData;
    private String pleaseEnterValid;


    /**
     * switchScreen
     * loads new stage
     * @param event Button Click
     * @param switchPath path to new stage
     * @throws IOException
     */
    public void switchScreen(ActionEvent event, String switchPath) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource(switchPath));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    /**
     * pressLogonButton
     * attempts logon
     *
     * @param event Button Click
     * @throws IOException
     * @throws SQLException
     */
    public void loginClick(ActionEvent event) throws IOException, SQLException {
        String userName = username.getText();
        String passWord = password.getText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        String s = dtf.format(ldt);

        int userId = DBUsers.validateUser(userName, passWord);

        FileWriter fWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fWriter);

        if (userId > 0)
        {

            outputFile.println(s + " " + username.getText() + " successfully logged in");
            outputFile.close();

            LocalDateTime now = LocalDateTime.now();

            ObservableList<Appointments> aList = DBAppt.getAllAppointments();
            ObservableList<Appointments> uList = aList.filtered(ap -> {   //lambda expression

                if (ap.getUserID() == userId)
                {
                    return true;
                }
                return false;

            });

            boolean name = false;

            for (Appointments a : uList)
            {

                {
                    if (a.getStart().isAfter(now) && a.getStart().isBefore(now.plusMinutes(15)))
                    {
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setHeaderText("UPCOMING APPOINTMENT");
                        alert3.setContentText("You have an appointment scheduled within the next 15 minutes: appointment " + a.getApptID() + " at " + a.getStart());
                        alert3.showAndWait();

                        name = true;
                    }
                }
            }

            if (!name)
            {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setHeaderText("NO UPCOMING APPOINTMENTS");
                alert3.setContentText("You have no appointments scheduled within the next 15 minutes.");
                alert3.showAndWait();
            }


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = FXMLLoader.load(getClass().getResource("View/homeScreen.fxml"));
            window.setScene((scene));
            window.show();
        }
        else
        {
            outputFile.println(s + " " + username.getText() + " unsuccessfully attempted to log in");
            outputFile.close();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(invalidLoginData);
            alert.setContentText(pleaseEnterValid);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                username.clear();
                password.clear();
            }
        }
    }


    /** This method exits the application.
     *
     * @param event clicking the exit button.
     */
    @FXML
    void onActionExitApplication(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(confirmSure);
        alert.setContentText(confirmExit);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ((Button)(event.getSource())).getScene().getWindow().hide();
        }
    }


    /**
     * This method initializes the 'LOGIN' screen. It utilizes a language resource bundle to allow for the entire screen to be translated to French, based on the locale of the local machine.
     *
     * @param url the location.
     * @param resourceBundle the resources.
     */
    //@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            ResourceBundle rb = ResourceBundle.getBundle("languages/language", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr"))
            {
                //titleLabel.setText(rb.getString("title"));
                usernameLabel.setText(rb.getString("usernameLabel"));
                passwordLabel.setText(rb.getString("passwordLabel"));
                //zoneIdLabel.setText(rb.getString("zoneIdLabel"));
                loginBtn.setText(rb.getString("loginBtn"));
                //switchLabelZoneId.setText((ZoneId.systemDefault()).getId());
                confirmSure = rb.getString("confirmSure");
                confirmExit = rb.getString("confirmExit");
                invalidLoginData = rb.getString("invalidLoginData");
                pleaseEnterValid = rb.getString("pleaseEnterValid");
            }
        }
        catch (Exception e)
        {
            System.out.println();
        }
    }
}