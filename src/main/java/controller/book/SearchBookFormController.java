package controller.book;

import util.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchBookFormController implements Initializable {
    @FXML
    private JFXComboBox cmbFilter;

    @FXML
    private JFXComboBox cmbSearch;

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colAvailability;

    @FXML
    private TableColumn colBookId;

    @FXML
    private TableColumn colGenre;

    @FXML
    private TableColumn colISBN;

    @FXML
    private TableColumn colTitle;

    @FXML
    private Label lblSearchBy;

    @FXML
    private TableView tblBook;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton viewAllBooks;

    private String SQL;

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ObservableList<Book> bookList = null;
        switch (cmbFilter.getValue().toString()){
            case "Title":
                try {
                    bookList = FXCollections.observableArrayList(new BookController().searchBookByTitle(txtSearch.getText()));
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
                break;

            case "Author":
                try {
                    bookList = FXCollections.observableArrayList(new BookController().searchBookByAuthor(cmbSearch.getValue().toString()));
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
                break;

            case "Genre":
                try {
                    bookList = FXCollections.observableArrayList(new BookController().searchBookByGenre(cmbSearch.getValue().toString()));
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
                break;

            case "Availability":
                try {
                    bookList = FXCollections.observableArrayList(new BookController().searchBookByAvailability(cmbSearch.getValue().toString()));
                } catch (SQLException e) {
                    Notification.showNotification(e);
                }
                break;
        }
        tblBook.setItems(bookList);
    }

    @FXML
    void cmbFilterOnAction(ActionEvent event) {
        switch (cmbFilter.getValue().toString()) {
            case "Title":
                lblSearchBy.setText("Enter the title of the book to search :");
                cmbSearch.setVisible(false);
                txtSearch.setVisible(true);
                break;

            case "Author":
                SQL = "SELECT DISTINCT author FROM book";
                loadSearchFilters();
                lblSearchBy.setText("Select the author of the book to search :");
                cmbSearch.setVisible(true);
                txtSearch.setVisible(false);
                break;

            case "Genre":
                SQL = "SELECT DISTINCT genre FROM book";
                loadSearchFilters();
                lblSearchBy.setText("Select the genre of the book to search :");
                cmbSearch.setVisible(true);
                txtSearch.setVisible(false);
                break;

            case "Availability":
                SQL = "SELECT DISTINCT availability FROM book";
                loadSearchFilters();
                lblSearchBy.setText("Select the availability of the book to search :");
                cmbSearch.setVisible(true);
                txtSearch.setVisible(false);
                break;
        }
    }

    @FXML
    void viewAllBooksOnAction(ActionEvent event) {
        try {
            ObservableList<Book> bookList = FXCollections.observableArrayList(new BookController().getAllBooks());
            tblBook.setItems(bookList);
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }

    private void loadFilters() {
        cmbFilter.setItems(FXCollections.observableArrayList("Title", "Author", "Genre", "Availability"));
        cmbFilter.setValue("Title");
    }

    private void loadSearchFilters() {
        ObservableList<String> searchFiltersList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            while (resultSet.next()){
                searchFiltersList.add(resultSet.getString(1));
            }
            cmbSearch.setItems(searchFiltersList);
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbSearch.setVisible(false);
        txtSearch.setVisible(true);
        loadFilters();
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }
}
