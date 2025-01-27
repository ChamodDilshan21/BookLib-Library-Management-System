package controller.employee;

import javafx.collections.ObservableList;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    boolean addEmployee(Employee employee) throws SQLException;

    boolean updateEmployee(Employee employee) throws SQLException;

    Employee searchEmployee(String empId) throws SQLException;

    List<Employee> getAllEmployees() throws SQLException;

    boolean deleteEmployee(String empId) throws SQLException;

}
