package co.mercy.hr.repository;

import co.mercy.hr.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    // JPA Native Query
    // @Query(value = "select * from tbl_employee e where e.fname = ?1 or e.lname = ?2", nativeQuery = true)
    // List<Employee> findByName(String fname, String lname, Pageable pages);

    // JPQL query
    // @Query("from tbl_employee e where e.fname = ?1 or e.lname = ?2")
    // List<Employee> findByName(String fname, String lname);

    List<Employee> getAllInactiveEmployees(Pageable pages);

    List<Employee> findByFnameOrLname(String fname, String lname, Pageable pages);

    List<Employee> findByFnameAndLname(String fname, String lname, Pageable pages);

    @Modifying
    @Transactional
    @Query("update employee set isDeleted=1 where id = :id")
    Integer deleteEmployeeById(Long id);

    @Query("from employee where isDeleted = :isDeleted")
    Page<Employee> getValidEmployees(Integer isDeleted, Pageable pages);

    List<Employee> findByDepartmentId(Integer department, Pageable pages);

    // Get Name from the Department object
    // This method works just like getEmployeesByDeptName below
    List<Employee> findByDepartmentName(String department, Pageable pages);

    // JPQL Join query in Data JPA
    // This method is a JPQL implementation of findByDepartmentName above
    @Query("from employee where department.name = :department")
    List<Employee> getEmployeesByDeptName(String department, Pageable pages);

    List<Employee> findByAddressContaining(String keyword, Pageable pages);

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);
}
