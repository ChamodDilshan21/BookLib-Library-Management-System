package controller.book;

import util.InputValidator;
import util.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Book;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteBookFormController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtAvailability;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtGenre;

    @FXML
    private JFXTextField txtISBN;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    void btnSearchOnAction (ActionEvent event) {
        if (InputValidator.isValidNumber(txtBookId.getText())){
            try {
                Book book = new BookController().searchBookById(Integer.parseInt(txtBookId.getText()));
                if (book != null){
                    txtTitle.setText(book.getTitle());
                    txtAuthor.setText(book.getAuthor());
                    txtISBN.setText(book.getIsbn());
                    txtGenre.setText(book.getGenre());
                    txtAvailability.setText(book.getAvailability());
                    btnDelete.setDisable(false);
                } else {
                    Notification.showNotification("No book found");
                }
            } catch (SQLException e) {
                Notification.showNotification(e);
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Are you sure you want to delete this book..?");
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean isDeleted = new BookController().deleteBook(txtBookId.getText());
                if (isDeleted) {
                    Notification.showNotification("Book deleted successfully");
                    btnDelete.setDisable(true);
                } else {
                    Notification.showNotification("Book delete failed");
                }
            }
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }
}
