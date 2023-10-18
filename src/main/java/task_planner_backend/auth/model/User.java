package task_planner_backend.auth.model;

import lombok.Data;
import task_planner_backend.tasklogic.entity.Task;

import javax.persistence.*;
import java.util.List;

@Table(name = "users",
        uniqueConstraints =  {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email")

})
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany
    private List<Task> tasks;
}
