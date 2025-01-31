package controller.user;

import Additional.InputValidator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import Additional.Notification;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class UpdateUserFormController implements Initializable {

    public JFXTextField txtSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtName;

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
    private DatePicker txtNewMemDate;

    @FXML
    private JFXTextField txtUpdatedContact;



    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (InputValidator.isValidContact(txtSearch.getText())) {
            ObservableList<User> userObservableList = FXCollections.observableArrayList();
            try {
                User user = new UserController().searchUser(txtSearch.getText());
                if (user!=null){
                    userObservableList.add(user);
                    tblUser.setItems(userObservableList);
                    btnUpdate.setDisable(false);
                }else {
                    Notification.showNotification("No user Found");
                }
            } catch (SQLException | ParseException e) {
                Notification.showNotification(e);
            }
        } else {
            Notification.showNotification("Enter User Contact Number to Search a User..");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValidInputs()){
            if (InputValidator.isValidContact(txtUpdatedContact.getText())){
                try {
                    User user = new User(
                            txtName.getText(),
                            txtUpdatedContact.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").parse(txtNewMemDate.getValue().toString()),
                            Double.parseDouble(txtBalance.getText())
                    );
                    boolean isUpdated = new UserController().updateUser(user,txtSearch.getText());
                    if (isUpdated){
                        Notification.showNotification("User Successfully Updated");
                        btnUpdate.setDisable(true);
                    } else {
                        Notification.showNotification("User Update Failed");
                    }
                } catch (ParseException | SQLException e) {
                    Notification.showNotification(e);
                }
            }else {
                Notification.showNotification("Please enter a valid contact number");
            }
        } else {
            Notification.showNotification("Please fill all the Fields to Update the User....");
        }
    }

    private boolean isValidInputs(){
        return !(txtName.getText().isEmpty() || txtUpdatedContact.getText().isEmpty() || txtNewMemDate.getValue()==null || txtBalance.getText().isEmpty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colMembershipDate.setCellValueFactory(new PropertyValueFactory<>("membershipDate"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

}
