package controller.employee;

import javafx.collections.ObservableList;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    boolean addEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    Employee searchEmployee(String empId) throws SQLException;

    List<Employee> getAll() throws SQLException;

    boolean deleteEmployee(String empId);

    ObservableList<String> getEmployeeIds() throws SQLException;
}
