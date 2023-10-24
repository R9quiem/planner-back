package task_planner_backend.auth.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    @Size(min = 5)
    private String username;
    @Email
    private String email;
    @Size(min = 6)
    private String password;
}
