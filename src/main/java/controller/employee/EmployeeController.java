package controller.employee;

import db.DBConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService{
    @Override
    public boolean addEmployee(Employee employee) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee('name','email','contact','password') VALUES(?,?,?,?)");
        preparedStatement.setObject(1,employee.getName());
        preparedStatement.setObject(2,employee.getEmail());
        preparedStatement.setObject(3,employee.getContact());
        preparedStatement.setObject(4,employee.getPassword());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate("UPDATE employee SET name='?',email='?',contact='?' WHERE id='" + employee.getEmpId() + "'")>0;
    }

    @Override
    public Employee searchEmployee(String empId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer WHERE id='" + empId + "'");
        if(resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            employeeList.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }

        return employeeList;
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM employee WHERE empId='" + empId + "'")>0;
    }
}
