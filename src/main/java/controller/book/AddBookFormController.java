package controller.book;

import util.InputValidator;
import util.Notification;
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

public class AddBookFormController implements Initializable {

    @FXML
    private JFXComboBox cmbAvailability;

    @FXML
    private JFXComboBox cmbGenre;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtISBN;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (isAllInputsFilled()){
            if (InputValidator.isValidISBN(txtISBN.getText())){
                try {
                    boolean isAdded = new BookController().addBook(new Book(
                            txtTitle.getText(),
                            txtAuthor.getText(),
                            txtISBN.getText(),
                            cmbGenre.getValue().toString(),
                            cmbAvailability.getValue().toString()
                    ));
                    if (isAdded){
                        Notification.showNotification("Book Added Successfully");
                    }else {
                        Notification.showNotification("Book Insertion Failed");
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
        cmbGenre.setItems(FXCollections.observableArrayList(
                "Literature & Fiction","Romance","Poetry","Science & Technology",
                "History","Self-Help",
                "Philosophy","Psychology","Business & Economics",
                "Engineering & Applied Sciences","Medicine & Healthcare","Education",
                "Cultural Studies","Folklore & Mythology","Children’s Book", "Mystery & Thriller"
        ));
    }

    private void loadAvailability(){
        cmbAvailability.setItems(FXCollections.observableArrayList("YES","NO"));
    }

    private boolean isAllInputsFilled(){
        return !(txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty()  || txtISBN.getText().isEmpty() || cmbGenre.getValue()==null || cmbAvailability.getValue()==null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGenre();
        loadAvailability();
    }
}
