package co.mercy.hr.controller;

import co.mercy.hr.model.Employee;
import co.mercy.hr.service.EmployeeService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Setter
@Data
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/app-info")
    public String getAppInfo(){
        return appName + " v" + appVersion;
    }
    @GetMapping("/all")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/id/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
        System.out.println("Getting employee with ID " + id +"...");
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee;
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmployeeByName(@PathVariable("name") String name){
        System.out.println("Getting employee with name " + name + "...");
        List<Employee> employees = employeeService.getEmployeeByName(name, name);
        return employees;
    }

    @GetMapping("/search")
    public List<Employee> searchEmployeeByName(@RequestParam("name") String name){
        System.out.println("Searching employee with name " + name +"...");
        List<Employee> employees = employeeService.getEmployeeByName(name, name);
        return employees;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        System.out.println("Saving employee details...");
        System.out.println(employee);
        employeeService.createEmployee(employee);
        return employee;
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        System.out.println("Updating employee with ID " + employee.getId() + "...");
        employeeService.updateEmployee(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        System.out.println("Deleting employee with ID " + id + "...");
        employeeService.deleteEmployeeById(id);
        return "Employee with ID " + id + " deleted successfully";
    }
}
