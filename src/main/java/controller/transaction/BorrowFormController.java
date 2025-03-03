package controller.transaction;

import util.InputValidator;
import util.Notification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.book.BookController;
import controller.user.UserController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Book;
import model.Borrow;
import model.BorrowDetail;
import model.User;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BorrowFormController implements Initializable {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnClearCart;

    @FXML
    private JFXButton btnConfirmBorrow;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private JFXComboBox cmbBookCode;

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
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView tblBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtGenre;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtUserId;

    private List<Book> cartBooks = new ArrayList<>();

    private Book selectedBook;

    @FXML
    void btnSearchUserOnAction(ActionEvent event) {
        if (InputValidator.isValidContact(txtContact.getText())) {
            try {
                List<User> userList = new UserController().searchUserByContact(txtContact.getText());
                if (!userList.isEmpty()) {
                    txtUserId.setText(userList.getFirst().getUserId());
                    txtName.setText(userList.getFirst().getName());
                    txtBalance.setText(userList.getFirst().getBalance().toString());
                    cmbBookCode.setDisable(false);
                    cartBooks.clear();
                } else {
                    Notification.showNotification("No user found..!");
                }
            } catch (SQLException | ParseException e) {
                Notification.showNotification(e);
            }
        } else {
            Notification.showNotification("Please enter a valid contact number!");
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (!isInTheCart()) {
            if (cartBooks.size() < 3) {
                cartBooks.add(selectedBook);
                ObservableList<Book> bookList = FXCollections.observableArrayList();
                bookList.addAll(cartBooks);
                tblBook.setItems(bookList);
                btnClearCart.setDisable(false);
                btnRemoveItem.setDisable(false);
                btnConfirmBorrow.setDisable(false);
            } else {
                Notification.showNotification("You can only borrow up to three books at a time");
            }
        } else {
            Notification.showNotification("This book is already in the cart");
        }
    }

    @FXML
    void btnRemoveItemOnAction(ActionEvent event) {
        if (!tblBook.getSelectionModel().isEmpty()) {
            int selectedIndex = tblBook.getSelectionModel().getSelectedIndex();
            tblBook.getItems().remove(selectedIndex);
            cartBooks.remove(selectedIndex);
        }
    }

    @FXML
    void btnClearCartOnAction(ActionEvent event) {
        cartBooks.clear();
        tblBook.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void btnConfirmBorrowOnAction(ActionEvent event) {
        if (!tblBook.getItems().isEmpty()) {
            try {
                String borrowId = new BorrowController().getNextBorrowId();


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String returnDate = LocalDate.parse(lblDate.getText(), formatter).plusDays(7).format(formatter);

                List<BorrowDetail> borrowDetailList = new ArrayList<>();
                cartBooks.forEach(book -> borrowDetailList.add(new BorrowDetail(borrowId, book.getBookId())));

                boolean isBorrowPlaced = new BorrowController().placeBorrow(
                        new Borrow(
                                borrowId,
                                Integer.parseInt(txtUserId.getText()),
                                lblDate.getText(),
                                returnDate,
                                borrowDetailList
                        )
                );

                if (isBorrowPlaced){
                    Notification.showNotification("Borrow Placed Successfully..! Return date for the book is - "+returnDate);
                } else {
                    Notification.showNotification(("Borrow Failed..!"));
                }

            } catch (SQLException e) {
                Notification.showNotification(e);
            }
        } else {
            Notification.showNotification("Cart is empty...");
        }

    }

    public void cmbBookCodeOnAction(ActionEvent actionEvent) {
        try {
            selectedBook = new BookController().searchBookById((Integer) cmbBookCode.getValue());
            txtTitle.setText(selectedBook.getTitle());
            txtAuthor.setText(selectedBook.getAuthor());
            txtGenre.setText(selectedBook.getGenre());
            btnAddToCart.setDisable(false);
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }

    private boolean isInTheCart() {
        for (Book book : cartBooks) {
            if (Objects.equals(book.getBookId(), selectedBook.getBookId())) {
                return true;
            }
        }
        return false;
    }

    private void setDateAndTime() {
        Date date = new Date();
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadBooks() throws SQLException {
        List<Book> availableBooks = new BookController().getAvailableBooks();
        ObservableList<Integer> bookIdList = FXCollections.observableArrayList();
        availableBooks.forEach(book -> bookIdList.add(book.getBookId()));
        cmbBookCode.setItems(bookIdList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateAndTime();
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        try {
            loadBooks();
        } catch (SQLException e) {
            Notification.showNotification(e);
        }
    }


}
