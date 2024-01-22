package co.mercy.hr.service;

import co.mercy.hr.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    Optional<Employee> getEmployee(Long id);

    List<Employee> getEmployeeByName(String fname, String lname);
    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long id);
}
