package controller.viewController;

import Additional.InputValidator;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Employee;
import Additional.Notification;
import org.jasypt.util.text.BasicTextEncryptor;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegNewEmpFormController implements Initializable {

    @FXML
    private JFXComboBox cmbRole;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (isAllInputsFilled()){
            if (isCorrectPassword()){
                if (InputValidator.isValidContact(txtContact.getText())){
                    if (InputValidator.isValidEmail(txtEmail.getText())){
                        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                        basicTextEncryptor.setPassword("#v08n2N");
                        try {
                            Employee employee = new Employee(
                                    txtName.getText(),
                                    txtEmail.getText(),
                                    txtContact.getText(),
                                    basicTextEncryptor.encrypt(txtPassword.getText()),
                                    cmbRole.getValue().toString()
                            );
                            Connection connection = DBConnection.getInstance().getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee(name,email,contact,password,role) VALUES(?,?,?,?,?)");
                            preparedStatement.setObject(1,employee.getName());
                            preparedStatement.setObject(2,employee.getEmail());
                            preparedStatement.setObject(3,employee.getContact());
                            preparedStatement.setObject(4,employee.getPassword());
                            preparedStatement.setObject(5,employee.getRole());
                            if (preparedStatement.executeUpdate()>0){
                                Notification.showNotification("New Employee Registered Successfully.!");
                            }else {
                                Notification.showNotification("Registration Failed.!");
                            }
                        } catch (SQLException e) {
                            Notification.showNotification(e);
                        }
                    }else {
                        Notification.showNotification("Please enter a valid email");
                    }
                } else {
                    Notification.showNotification("Please enter a valid phone number");
                }
            }else {
                Notification.showNotification("Password didn't match! Please check again");
            }
        }else {
            Notification.showNotification("Please fill all the fields to proceed");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRoles();
    }

    private void loadRoles(){
        ObservableList<String> roleList = FXCollections.observableArrayList("Admin","Default");
        cmbRole.setItems(roleList);
    }

    private boolean isAllInputsFilled(){
        return !(txtName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtContact.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty() || cmbRole.getValue()==null);
    }

    private boolean isCorrectPassword(){
        return txtPassword.getText().equals(txtConfirmPassword.getText());
    }
}
