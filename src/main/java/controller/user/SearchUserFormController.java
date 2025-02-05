package controller.user;

import Additional.InputValidator;
import Additional.Notification;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchUserFormController implements Initializable {

    public Label lblSearchBy;
    @FXML
    private JFXComboBox cmbFilter;

    @FXML
    private TableColumn colBalance;

    @FXML
    private TableColumn colContact;

    @FXML
    private TableColumn colMembershipDate;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colUserId;

    @FXML
    private TableView tblUser;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ObservableList<User> userList;
        switch (cmbFilter.getValue().toString()) {
            case "Contact No":
                if (InputValidator.isValidContact(txtSearch.getText())) {
                    try {
                        userList = FXCollections.observableArrayList(new UserController().searchUserByContact(txtSearch.getText()));
                        tblUser.setItems(userList);
                    } catch (SQLException | ParseException e) {
                        Notification.showNotification(e);
                    }
                } else {
                    Notification.showNotification("Please enter a valid contact number");
                }
                break;

            case "Name":
                try {
                    userList = FXCollections.observableArrayList(new UserController().searchUserByName(txtSearch.getText()));
                    tblUser.setItems(userList);
                } catch (SQLException | ParseException e) {
                    Notification.showNotification(e);
                }
                break;

            case "Membership Date":
                if (InputValidator.isValidDate(txtSearch.getText())) {
                    try {
                        userList = FXCollections.observableArrayList(new UserController().searchUserByMembershipDate(txtSearch.getText()));
                        tblUser.setItems(userList);
                    } catch (SQLException | ParseException e) {
                        Notification.showNotification(e);
                    }
                } else {
                    Notification.showNotification("Please enter the date in valid format(yyyy-MM-dd)");
                }
                break;
        }
    }

    @FXML
    void cmbFilterOnAction(ActionEvent event) {
        switch (cmbFilter.getValue().toString()) {
            case "Contact No":
                lblSearchBy.setText("Enter user contact number to search :");
                break;

            case "Name":
                lblSearchBy.setText("Enter user name to search :");
                break;

            case "Membership Date":
                lblSearchBy.setText("Enter user Membership Date to search :");
                break;
        }
    }

    private void loadFilters() {
        cmbFilter.setItems(FXCollections.observableArrayList("Contact No", "Name", "Membership Date"));
        cmbFilter.setValue("Contact No");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFilters();
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colMembershipDate.setCellValueFactory(new PropertyValueFactory<>("membershipDate"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    public void viewAllUsersOnAction(ActionEvent actionEvent) throws SQLException, ParseException {
        ObservableList<User> userList = FXCollections.observableArrayList(new UserController().getAllUsers());
        tblUser.setItems(userList);
    }
}
