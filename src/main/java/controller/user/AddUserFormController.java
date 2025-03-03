package controller.user;

import util.InputValidator;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.User;
import util.Notification;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddUserFormController implements Initializable {

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtMembershipDate;

    @FXML
    private JFXTextField txtName;


    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (isAllInputsFilled()){
            if (InputValidator.isValidContact(txtContact.getText())){
                try {
                    boolean isAdded = new UserController().addUser(new User(
                            txtName.getText(),
                            txtContact.getText(),
                            txtMembershipDate.getText(),
                            Double.parseDouble(txtBalance.getText())
                    ));
                    if (isAdded){
                        Notification.showNotification("User Added Successfully.!");
                    } else {
                        Notification.showNotification("User Registration Failed.!");
                    }
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
            }else {
                Notification.showNotification("Please enter valid contact number");
            }
        } else {
            Notification.showNotification("Please fill all the fields to proceed");
        }
    }

    private void setDateAndBalance() {
        txtMembershipDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        txtBalance.setText("0.0");
    }

    private boolean isAllInputsFilled(){
        return !(txtName.getText().isEmpty() || txtContact.getText().isEmpty()  || txtMembershipDate.getText().isEmpty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateAndBalance();
    }

}
