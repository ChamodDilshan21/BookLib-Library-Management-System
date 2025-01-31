package controller.viewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import Additional.Notification;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/home_page.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnUserManageOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/user_management_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnBookManageOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/book_management_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnBorrowBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/borrow_book_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/return_book_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/report_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnRegNewEmployeeOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/reg_new_emp_form.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            Notification.showNotification(e);
        }

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

}
