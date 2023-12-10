package task_planner_backend.auth.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
@Getter
@Setter
public class PasswordResetRequest {

    @Email
    private String email;
}
