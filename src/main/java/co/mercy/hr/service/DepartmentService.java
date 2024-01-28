package co.mercy.hr.service;

import co.mercy.hr.model.Department;

import java.util.Optional;

public interface DepartmentService {
    Department getDepartment(String name);
}
