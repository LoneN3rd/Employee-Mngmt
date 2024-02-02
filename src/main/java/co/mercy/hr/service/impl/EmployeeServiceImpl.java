package co.mercy.hr.service.impl;

import co.mercy.hr.dao.EmployeeDaoImpl;
import co.mercy.hr.model.Employee;
import co.mercy.hr.repository.EmployeeRepository;
import co.mercy.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return employeeRepository.getValidEmployees(0, pages).getContent();
    }

    @Override
    public List<Employee> getInactiveEmployees(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        // DAO Demo -- @NamedQuery in Entity class
        return employeeDao.getInactiveEmployees(pages);
        // Native Query Demo -- @NamedQuery and @NamedNativeQuery in Entity class, either works
        //return employeeRepository.getAllInactiveEmployees(pages);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getEmployeesByFnameOrLname(String fname, String lname, int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return employeeRepository.findByFnameOrLname(fname, lname, pages);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Integer deleteEmployeeById(Long id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department, int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        // return employeeRepository.findByDepartmentName(department, pages); // also works
        return employeeRepository.getEmployeesByDeptName(department, pages);
    }

    @Override
    public List<Employee> getEmployeesByFnameAndLname(String fname, String lname, int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return employeeRepository.findByFnameAndLname(fname, lname, pages);
    }

    @Override
    public List<Employee> getEmployeeByAddressContaining(String keyword, int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        // Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return employeeRepository.findByAddressContaining(keyword, pages);
    }


}
