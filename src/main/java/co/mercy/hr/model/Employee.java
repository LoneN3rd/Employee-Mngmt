package co.mercy.hr.model;

import co.mercy.hr.request.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity(name = "employee")
@Table(name = "tbl_employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@NamedNativeQuery(name = "getAllInactiveEmps", query = "select * from tbl_employee where is_active = 0", resultClass = Employee.class) // query contains the table name and column names
@NamedQuery(name = "Employee.getAllInactiveEmployees", query = "from employee where isActive = 0") // query contains the entity name and field names
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiModelProperty(notes = "This is a unique identifier for an employee")
    private Long id;

    @NotBlank(message="first_name cannot be null")
    // @JsonProperty("first_name")
    private String fname;

    @NotBlank(message="last_name cannot be null")
    // @JsonProperty("last_name")
    private String lname;

    @NotBlank(message="address cannot be null")
    private String address;

    @NotBlank(message="role cannot be null")
    private String role;

    @Email(message="Enter a valid email_address")
    private String emailAddress;

//    @NotNull(message="department cannot be null")
    @JoinColumn(name = "department_id")
    @OneToOne
    private Department department;

    @NotBlank(message = "ref cannot be blank")
    @Column(name="ref", nullable = false, updatable = false)
    private String ref; // TODO: autogenetate
    private Integer isActive = 1; // TODO: ignore passed value
    private String createdBy;

    @CreationTimestamp()
    @Column(name="created_on", nullable = false, updatable = false)
    private Date createdOn;
    private String lastUpdatedBy;

    @UpdateTimestamp
    private Date lastUpdatedOn;
    private Integer isDeleted = 0; // TODO: ignore passed value

    public Employee(EmployeeRequest request) {
        this.fname = request.getFirstName();
        this.lname = request.getLastName();
        this.ref = request.getRef();
        this.isActive = request.getIsActive();
        this.isDeleted = request.getIsDeleted();
        this.createdBy = request.getCreatedBy();
        this.address = request.getAddress();
        this.role = request.getRole();
        this.emailAddress = request.getEmailAddress();
    }
}
