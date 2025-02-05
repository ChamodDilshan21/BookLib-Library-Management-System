package controller.user;

import javafx.collections.ObservableList;
import model.Employee;
import model.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface UserServices {
    boolean addUser(User user) throws SQLException;

    boolean updateUser(User user, String oldContact) throws SQLException;

    List<User> searchUserByContact(String contact) throws SQLException, ParseException;

    List<User> searchUserByName(String name) throws SQLException, ParseException;

    List<User> searchUserByMembershipDate(String date) throws SQLException, ParseException;

    List<User> getAllUsers() throws SQLException, ParseException;

    boolean deleteUser(String contact) throws SQLException;

}
