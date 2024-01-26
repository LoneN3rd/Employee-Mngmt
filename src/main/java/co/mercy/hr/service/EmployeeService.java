package co.mercy.hr.service;

import co.mercy.hr.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees(int pageNumber, int pageSize);
    Optional<Employee> getEmployee(Long id);
    List<Employee> getEmployeesByFnameOrLname(String fname, String lname, int pageNumber, int pageSize);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Integer deleteEmployeeById(Long id);
    List<Employee> getEmployeesByDepartment(String department, int pageNumber, int pageSize);
    List<Employee> getEmployeesByFnameAndLname(String fname, String lname, int pageNumber, int pageSize);
    List<Employee> getEmployeeByAddressContaining(String keyword, int pageNumber, int pageSize);
}
