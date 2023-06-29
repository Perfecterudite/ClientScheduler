package Company.Controller;


import Company.Model.Appointments;
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
        //try {
            Parent scene = FXMLLoader.load(getClass().getResource(switchPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //Scene = new Scene(scene);
            stage.setScene(new Scene(scene));
            //Scene scene = new Scene(parent);

            //window.setScene(scene);
            stage.show();

       /** }catch (Exception e){
            System.out.println("can't load scene");
        }**/


    }

    /**
     * pressLogonButton
     * attempts logon
     *
     * @param event Button Click
     * @throws IOException
     * @throws SQLException
     */
    public void loginClick(ActionEvent event) throws SQLException, IOException {
        try {
            String userName = username.getText();
            String passWord = password.getText();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt = LocalDateTime.now();
            String s = dtf.format(ldt);

            int userId = DBUsers.validateUser(userName, passWord);

            FileWriter flWriter = new FileWriter("login_activity.txt", true);
            PrintWriter outputFile = new PrintWriter(flWriter);


            if (userId > 0) {

                /**FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("View/homeScreen.fxml"));
                Parent scene = loader.load();
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(scene));
                stage.show();**/

                //switchScreen(event, ".../View/homeScreen.fxml ");


                outputFile.println(s + " " + username.getText() + " successfully logged in");
                outputFile.close();

                LocalDateTime now = LocalDateTime.now();

                ObservableList<Appointments> aList = DBAppt.getAllAppointments();
                ObservableList<Appointments> uList = aList.filtered(ap -> {   //lambda expression

                    if (ap.getUserID() == userId) {
                        return true;
                    }
                    return false;

                });

                boolean name = false;

                for (Appointments a : uList) {
                    if (a.getStart().isAfter(now) && a.getStart().isBefore(now.plusMinutes(15))) {
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setHeaderText("UPCOMING APPOINTMENT");
                        alert3.setContentText("You have an appointment scheduled within the next 15 minutes: appointment " + a.getApptID() + " at " + a.getStart());
                        alert3.showAndWait();

                        name = true;
                    }
                }

                //}

                if (!name) {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setHeaderText("NO UPCOMING APPOINTMENTS");
                    alert3.setContentText("You have no appointments scheduled within the next 15 minutes.");
                    alert3.showAndWait();
                }


                /**FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("homeScreen.fxml"));
                 Parent scene = loader.load();
                 Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                 stage.setScene(new Scene(scene));
                 stage.show();


                 /**
                 Parent root = FXMLLoader.load(getClass().getResource("View/mainLogin.fxml"));
                 primaryStage.setTitle("Scheduler");
                 primaryStage.setScene(new Scene(root, 800, 575));
                 primaryStage.show();



                Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();
                 Scene scene = FXMLLoader.load(getClass().getResource("View/homeScreen.fxml"));
                 window.setScene((scene));
                 window.show();**/
                switchScreen(event, "homeScreen.fxml");


            } else if(userId < 0) {
                outputFile.println(s + " " + username.getText() + " unsuccessfully attempted to log in");
                outputFile.close();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Login Data");
                alert.setContentText("Please Enter Valid Username and Password!");


                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    username.clear();
                    password.clear();
                }
            }
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
                   //}catch(){


    /** This method exits the application.
     *
     * @param event clicking the exit button.
     */
    @FXML
    public void onActionExitApplication(ActionEvent event)
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
                //invalidLoginData = rb.getString("invalidLoginData");
                //pleaseEnterValid = rb.getString("pleaseEnterValid");
            }
        }
        catch (Exception e)
        {
            System.out.println();
        }
    }
}