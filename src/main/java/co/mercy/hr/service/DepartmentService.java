package co.mercy.hr.service;

import co.mercy.hr.model.Department;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department getDepartmentByName(String name);

    Optional<Department> getDepartmentById(Long id);

    List<Department> getActiveDepartments(int pageNumber, int pageSize);

    List<Department> getInactiveDepartments(int pageNumber, int pageSize);

    Department createDepartment(Department department);

    Department updateDepartment(Department department);

    Integer deleteDepartment(Long id);
}
