package co.mercy.hr.repository;

import co.mercy.hr.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from tbl_employee e where e.fname = ?1 or e.lname = ?2")
    List<Employee> findByName(String fname, String lname);

    @Modifying
    @Transactional
    @Query("update tbl_employee e set e.isDeleted=1 where e.id = ?1")
    void deleteEmployeeById(Long id);

    @Query("select e from tbl_employee e where e.isDeleted = 0")
    List<Employee> findAll();
}
