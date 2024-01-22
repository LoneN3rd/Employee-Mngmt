package co.mercy.hr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "tbl_employee")
@Table(name = "tbl_employee")
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "This is a unique identifier for an employee")
    private Long id;
    @JsonProperty("first_name")
    private String fname;
    @JsonProperty("last_name")
    private String lname;
    private String address;
    private String role;
    private String emailAddress;
    private String department;
    private String ref;
    private Integer isActive;
    private String createdBy;
    @CreationTimestamp()
    @Column(name="created_on", nullable = false, updatable = false)
    private Date createdOn;
    private String lastUpdatedBy;
    @UpdateTimestamp
    private Date lastUpdatedOn;
    private Integer isDeleted;

}
