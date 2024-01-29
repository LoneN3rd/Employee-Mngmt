package co.mercy.hr.dao;

import co.mercy.hr.model.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Employee> getInactiveEmployees(Pageable pages){
        return manager.createNamedQuery("Employee.getAllInactiveEmployees", Employee.class).getResultList();
        // return manager.createNamedQuery("getAllInactiveEmps", Employee.class).getResultList(); // also works
    }
}
