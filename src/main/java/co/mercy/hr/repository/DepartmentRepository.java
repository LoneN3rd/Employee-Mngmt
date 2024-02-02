package co.mercy.hr.repository;

import co.mercy.hr.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);

    @Query("from department where isDeleted = 0 and isActive = 1")
    List<Department> getActiveDepartments(Pageable pages);

    @Query("from department where isDeleted = 0 and isActive = 0")
    List<Department> getInactiveDepartments(Pageable pages);

    @Modifying
    @Transactional
    @Query("update department set isDeleted = 1, isActive = 0 where id = :id")
    Integer deleteDepartment(Long id);
}
