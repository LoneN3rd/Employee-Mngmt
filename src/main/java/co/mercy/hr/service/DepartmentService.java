package co.mercy.hr.service;

import co.mercy.hr.model.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    Department createDepartment(Department department);
}
