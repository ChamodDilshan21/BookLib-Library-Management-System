package controller.user;

import model.Employee;
import model.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface UserServices {
    boolean addUser(User user) throws SQLException;

    boolean updateUser(User user, String oldContact) throws SQLException;

    User searchUser(String contact) throws SQLException, ParseException;

    List<User> getAllUsers() throws SQLException, ParseException;

    boolean deleteUser(String contact) throws SQLException;

}
