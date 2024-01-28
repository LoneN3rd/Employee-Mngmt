package co.mercy.hr.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String department;
    private String ref;
    private Integer isActive = 1;
    private String createdBy;
    private String address;
    private String role;
    private String emailAddress;
}
