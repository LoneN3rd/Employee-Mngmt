package co.mercy.hr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "department")
@Table(name = "tbl_department")
@Getter
@Setter
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "department name cannot be null")
    private String name;

    private Integer isActive = 1;

    private Integer isDeleted = 0;

    @NotBlank(message = "created_by cannot be null")
    private String createdBy;

    @CreationTimestamp()
    @Column(name="created_on", nullable = false, updatable = false)
    private Date createdOn;

    private String lastUpdatedBy;

    @UpdateTimestamp
    private Date lastUpdatedOn;

    private String ref;

    // @OneToOne(mappedBy = "department")
    // private Employee employee;
}
