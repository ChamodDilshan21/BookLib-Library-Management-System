package controller.viewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Employee;
import util.Notification;
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
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword("#v08n2N");
                if (basicTextEncryptor.decrypt(employee.getPassword()).equals(txtPassword.getText())){
                    Stage stage = new Stage();
                    System.out.println(employee.getRole().equals("Admin"));
                    System.out.println(employee.getRole());
                    if(employee.getRole().equals("Admin")){
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin_dashboard_form.fxml"))));
                    }else {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/default_dashboard_form.fxml"))));
                    }

                    stage.setResizable(false);
                    stage.getIcons().add(new Image("img/BookLib_logo.png"));
                    stage.setTitle("Dashboard");
                    stage.show();
                    Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                    currentStage.close();
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
