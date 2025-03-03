package controller.user;

import util.InputValidator;
import util.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;

public class DeleteUserFormController {

    public JFXButton btnDelete;
    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtMembershipDate;

    @FXML
    private JFXTextField txtName;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        if (InputValidator.isValidContact(txtContact.getText())) {
            try {
                ObservableList<User> userList = FXCollections.observableArrayList(new UserController().searchUserByContact(txtContact.getText()));
                if (!userList.isEmpty()) {
                    txtName.setText(userList.getFirst().getName());
                    txtMembershipDate.setText(userList.getFirst().getMembershipDate().toString());
                    txtBalance.setText(userList.getFirst().getBalance().toString());
                    btnDelete.setDisable(false);
                } else {
                    Notification.showNotification("No user found");
                }
            } catch (SQLException | ParseException e) {
                Notification.showNotification(e);
            }
        } else {
            Notification.showNotification("Please enter a valid contact number");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Are you sure you want to delete this user..?");
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean isDeleted = new UserController().deleteUser(txtContact.getText());
                if (isDeleted) {
                    Notification.showNotification("User deleted successfully");
                    btnDelete.setDisable(true);
                } else {
                    Notification.showNotification("User delete failed");
                }
            }
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }
}