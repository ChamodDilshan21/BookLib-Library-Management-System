package controller.user;

import Additional.InputValidator;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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

    }

    @FXML
    void cmbFilterOnAction(ActionEvent event) {
        switch (cmbFilter.getValue().toString()){
            case "Contact No" :
                lblSearchBy.setText("Enter user contact number to search :");
                break;

            case "Name" :
                lblSearchBy.setText("Enter user name to search :");
                break;

            case "Membership Date" :
                lblSearchBy.setText("Enter user Membership Date to search :");
                break;
        }
    }

    private void loadFilters(){
        cmbFilter.setItems(FXCollections.observableArrayList("Contact No","Name","Membership Date"));
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
}
