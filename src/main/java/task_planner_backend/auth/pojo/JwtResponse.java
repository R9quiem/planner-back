package task_planner_backend.auth.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class JwtResponse {
    @NotEmpty
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    public JwtResponse(String token, Long id, String username, String email) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
