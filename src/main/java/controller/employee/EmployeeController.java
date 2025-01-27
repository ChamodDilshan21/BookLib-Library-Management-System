package controller.employee;

import javafx.collections.ObservableList;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController implements EmployeeService{
    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public Employee searchEmployee(String empId) throws SQLException {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean deleteEmployee(String empId) {
        return false;
    }

    @Override
    public ObservableList<String> getEmployeeIds() throws SQLException {
        return null;
    }
}
