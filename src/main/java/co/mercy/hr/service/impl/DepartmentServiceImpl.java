package co.mercy.hr.service.impl;

import co.mercy.hr.model.Department;
import co.mercy.hr.repository.DepartmentRepository;
import co.mercy.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
