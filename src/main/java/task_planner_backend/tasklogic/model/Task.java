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
    public void updateFromDTO(TaskDTO taskDTO) {
        this.setName(taskDTO.getName());
        this.setDescription(taskDTO.getDescription());
        this.setDate(taskDTO.getDate());
        this.setPriority(taskDTO.getPriority());
        this.setState(taskDTO.getState());
    }

}
