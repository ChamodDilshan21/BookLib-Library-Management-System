package controller.viewController;

import util.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class BookManagementFormController {

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/book_forms/add_book.fxml");

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
    void btnUpdateBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/book_forms/update_book.fxml");

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
    void btnSearchBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/book_forms/search_book.fxml");

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
    void btnDeleteBookOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("../../view/book_forms/delete_book.fxml");

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
