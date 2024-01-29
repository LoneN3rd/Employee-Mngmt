package co.mercy.hr.controller;

import co.mercy.hr.model.Department;
import co.mercy.hr.model.Employee;
import co.mercy.hr.repository.DepartmentRepository;
import co.mercy.hr.request.EmployeeRequest;
import co.mercy.hr.service.DepartmentService;
import co.mercy.hr.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Data
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/app-info")
    public String getAppInfo(){
        return appName + " v" + appVersion;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting all employees");
        return new ResponseEntity<>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("id") Long id){
        System.out.println("Getting employee with ID " + id +"...");
        List<Employee> employees = new ArrayList<>();
        Optional<Employee> employee = employeeService.getEmployee(id);
        employee.ifPresent(employees::add);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/findByFnameOrLname/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByFnameOrLname(@PathVariable String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting employee with fname " + name +" or lname "+ name +"...");
        return new ResponseEntity<>(employeeService.getEmployeesByFnameOrLname(name, name, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeeByFnameOrLname(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting employee with fname " + name +" or lname "+ name +"...");
        return new ResponseEntity<>(employeeService.getEmployeesByFnameOrLname(name, name, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/findByFnameAndLname")
    public ResponseEntity<List<Employee>> getEmployeesByFnameAndLname(@RequestParam String fname, @RequestParam String lname, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting employee with fname " + fname +" and lname "+ lname +"...");
        return new ResponseEntity<>(employeeService.getEmployeesByFnameAndLname(fname, lname, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String department, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting employees from department ==> "+ department);
        return new ResponseEntity<>(employeeService.getEmployeesByDepartment(department, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/address_contains")
    public ResponseEntity<List<Employee>> getEmployeeByAddressContaining(@RequestParam String keyword, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting employees with address containing " + keyword +"...");
        return new ResponseEntity<>(employeeService.getEmployeeByAddressContaining(keyword, pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request){
        System.out.println("Saving employee details...");
        Department existingDept = departmentService.getDepartment(request.getDepartment());
        System.out.println("existingDept --> " + existingDept);
        Employee employee = new Employee(request);
        if (existingDept == null){
            Department dept = new Department();
            dept.setName(request.getDepartment());
            departmentRepository.save(dept);
            employee.setDepartment(dept);
        } else {
            employee.setDepartment(existingDept);
        }

        System.out.println("Employee details... "+employee);
        employeeService.createEmployee(employee);
        // TODO: save both entities once instead of saving separately
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
        System.out.println("Updating employee with ID " + employee.getId() + "...");
        employee.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @PutMapping("/del/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        System.out.println("Deleting employee with ID " + id + "...");
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id) + " employee(s) with ID "+ id +" deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/all/inactive")
    public ResponseEntity<List<Employee>> getInactive(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting inactive employees");
        return new ResponseEntity<>(employeeService.getInactiveEmployees(pageNumber, pageSize), HttpStatus.OK);
    }
}
