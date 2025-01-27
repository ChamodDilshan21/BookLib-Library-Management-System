package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Employee;
import notification.Notification;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employee WHERE email='" + txtEmail.getText() + "'");
            if (resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                String key = "#v08n2N";
                basicTextEncryptor.setPassword(key);
                if (basicTextEncryptor.decrypt(employee.getPassword()).equals(txtPassword.getText())){
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
                    stage.show();
                }else {
                    Notification.showNotification("Login Failed!! Please Check Credentials Again..");
                }
            }else {
                Notification.showNotification("No Employee Found");
            }
        } catch (SQLException | IOException e) {
            Notification.showNotification(e);
        }

    }

}
