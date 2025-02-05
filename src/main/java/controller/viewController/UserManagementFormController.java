package controller.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import Additional.Notification;

import java.io.IOException;
import java.net.URL;

public class UserManagementFormController {

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnAddUserOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/user_forms/add_user.fxml");

        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadFormContent.getChildren().clear();
            loadFormContent.getChildren().add(load);
        } catch (IOException e) {
            Notification.showNotification(e);
        }
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/user_forms/update_user.fxml");

        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadFormContent.getChildren().clear();
            loadFormContent.getChildren().add(load);
        } catch (IOException e) {
            Notification.showNotification(e);
        }
    }

    @FXML
    void btnSearchUserOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/user_forms/search_user.fxml");

        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadFormContent.getChildren().clear();
            loadFormContent.getChildren().add(load);
        } catch (IOException e) {
            Notification.showNotification(e);
        }
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/user_forms/delete_user.fxml");

        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadFormContent.getChildren().clear();
            loadFormContent.getChildren().add(load);
        } catch (IOException e) {
            Notification.showNotification(e);
        }
    }



}
