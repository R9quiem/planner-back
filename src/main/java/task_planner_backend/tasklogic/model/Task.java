package task_planner_backend.tasklogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_planner_backend.auth.model.User;
import task_planner_backend.tasklogic.model.dto.TaskDTO;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private EPriority priority;

    @Enumerated(EnumType.STRING)
    private EState state;

    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        // Устанавливаем поле date на текущую дату при создании задачи
         date = new Date();
    }

    public Task(String name, String description, Date date, EPriority priority, EState state, User user) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.state = state;
        this.user = user;
    }
    public static Task build(TaskDTO taskDTO, User user) {
        return new Task(
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getDate(),
                taskDTO.getPriority(),
                taskDTO.getState(),
                user
        );
    }

}
