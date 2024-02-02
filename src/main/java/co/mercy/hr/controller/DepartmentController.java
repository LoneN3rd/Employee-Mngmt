package co.mercy.hr.controller;

import co.mercy.hr.model.Department;
import co.mercy.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getActiveDepartments(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting active departments...");
        return new ResponseEntity<>(departmentService.getActiveDepartments(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/all/inactive")
    public ResponseEntity<List<Department>> getInactiveDepartments(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        System.out.println("Getting inactive departments...");
        return new ResponseEntity<>(departmentService.getInactiveDepartments(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name){
        System.out.println("Getting department with name "+ name +"...");
        Department department = departmentService.getDepartmentByName(name);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable Long id){
        System.out.println("Getting department with name "+ id +"...");
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        System.out.println("Creating department...");
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department, @PathVariable Long id){
        System.out.println("Updating department with ID "+ id +"...");
        department.setId(id);
        return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatus.OK);
    }

    @PutMapping("/del/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        System.out.println("Deleting department with ID "+ id +"...");
        return new ResponseEntity<>(departmentService.deleteDepartment(id) + " department(s) deleted", HttpStatus.OK);
    }
}