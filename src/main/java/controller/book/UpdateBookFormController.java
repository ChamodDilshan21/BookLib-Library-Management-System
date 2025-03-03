package controller.book;

import util.InputValidator;
import util.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Book;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateBookFormController implements Initializable {
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox newcmbAvailability;

    @FXML
    private JFXComboBox newcmbGenre;

    @FXML
    private JFXTextField newtxtAuthor;

    @FXML
    private JFXTextField newtxtISBN;

    @FXML
    private JFXTextField newtxtTitle;

    @FXML
    private JFXTextField oldAuthor;

    @FXML
    private JFXTextField oldAvailability;

    @FXML
    private JFXTextField oldGenre;

    @FXML
    private JFXTextField oldISBN;

    @FXML
    private JFXTextField oldTitle;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (InputValidator.isValidNumber(txtSearch.getText())){
            try {
                Book book = new BookController().searchBookById(Integer.parseInt(txtSearch.getText()));
                if (book != null){
                    oldTitle.setText(book.getTitle());
                    oldAuthor.setText(book.getAuthor());
                    oldISBN.setText(book.getIsbn());
                    oldGenre.setText(book.getGenre());
                    oldAvailability.setText(book.getAvailability());
                    btnUpdate.setDisable(false);
                }else {
                    Notification.showNotification("No book found");
                }
            } catch (SQLException e) {
                Notification.showNotification(e);
            }
        }else {
            Notification.showNotification("Please enter a valid bookId");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isAllInputsFilled()){
            if (InputValidator.isValidISBN(newtxtISBN.getText())){
                try {
                    boolean isUpdated = new BookController().updateBook(new Book(
                            newtxtTitle.getText(),
                            newtxtAuthor.getText(),
                            newtxtISBN.getText(),
                            newcmbGenre.getValue().toString(),
                            newcmbAvailability.getValue().toString()
                    ),Integer.parseInt(txtSearch.getText()));
                    if (isUpdated){
                        btnUpdate.setDisable(true);
                        Notification.showNotification("Book update Successful");
                    }else {
                        Notification.showNotification("Book update Failed");
                    }
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
            } else {
                Notification.showNotification("Please enter a valid ISBN");
            }
        } else {
            Notification.showNotification("Please fill out all the fields");
        }
    }

    private void loadGenre(){
        newcmbGenre.setItems(FXCollections.observableArrayList(
                "Literature & Fiction","Romance","Poetry","Science & Technology",
                "History","Self-Help",
                "Philosophy","Psychology","Business & Economics",
                "Engineering & Applied Sciences","Medicine & Healthcare","Education",
                "Cultural Studies","Folklore & Mythology","Children’s Book", "Mystery & Thriller"
        ));
    }

    private void loadAvailability(){
        newcmbAvailability.setItems(FXCollections.observableArrayList("YES","NO"));
    }

    private boolean isAllInputsFilled(){
        return !(newtxtTitle.getText().isEmpty() || newtxtAuthor.getText().isEmpty()  || newtxtISBN.getText().isEmpty() || newcmbGenre.getValue()==null || newcmbAvailability.getValue()==null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAvailability();
        loadGenre();
    }
}
