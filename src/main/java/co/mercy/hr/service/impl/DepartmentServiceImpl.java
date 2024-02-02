package co.mercy.hr.service.impl;

import co.mercy.hr.model.Department;
import co.mercy.hr.repository.DepartmentRepository;
import co.mercy.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getActiveDepartments(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return departmentRepository.getActiveDepartments(pages);
    }

    @Override
    public List<Department> getInactiveDepartments(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return departmentRepository.getInactiveDepartments(pages);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Integer deleteDepartment(Long id) {
        return departmentRepository.deleteDepartment(id);
    }
}
