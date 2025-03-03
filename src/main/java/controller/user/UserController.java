package controller.user;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserController implements UserServices {
    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name,contact,membershipDate,balance) VALUES (?,?,?,?);");
        preparedStatement.setObject(1,user.getName());
        preparedStatement.setObject(2,user.getContact());
        preparedStatement.setObject(3,user.getMembershipDate());
        preparedStatement.setObject(4,user.getBalance());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateUser(User user, String oldContact) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name=?,contact=?,membershipDate=?,balance=? WHERE contact=?;");
        preparedStatement.setObject(1,user.getName());
        preparedStatement.setObject(2,user.getContact());
        preparedStatement.setObject(3,user.getMembershipDate());
        preparedStatement.setObject(4,user.getBalance());
        preparedStatement.setObject(5,oldContact);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public List<User> searchUserByContact(String contact) throws SQLException, ParseException {
        List<User> userList= new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE contact='" + contact + "'");
        while(resultSet.next()) {
            userList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(4))),
                    resultSet.getDouble(5)
            ));
        }
        return userList;
    }

    @Override
    public List<User> searchUserByName(String name) throws SQLException, ParseException {
        List<User> userList= new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE name LIKE '%"+name+"%';");
        while(resultSet.next()) {
            userList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(4))),
                    resultSet.getDouble(5)
            ));
        }
        return userList;
    }

    @Override
    public List<User> searchUserByMembershipDate(String date) throws SQLException, ParseException {
        List<User> userList= new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE membershipDate='" + date + "'");
        while(resultSet.next()) {
            userList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(4))),
                    resultSet.getDouble(5)
            ));
        }
        return userList;
    }

    @Override
    public List<User> getAllUsers() throws SQLException, ParseException {
        List<User> userList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(4))),
                    resultSet.getDouble(5)
            ));
        }

        return userList;
    }

    @Override
    public boolean deleteUser(String contact) throws SQLException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM user WHERE contact='" + contact + "'")>0;
    }
}
