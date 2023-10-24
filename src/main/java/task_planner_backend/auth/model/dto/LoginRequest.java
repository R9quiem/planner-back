package task_planner_backend.auth.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {
    @Size(min = 5)
    private String username;
    @Size(min = 6)
    private String password;
}
