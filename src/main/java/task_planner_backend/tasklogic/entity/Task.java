package task_planner_backend.tasklogic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_planner_backend.auth.model.User;

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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "dateCreatedAt")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private EPriority priority;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EState state;

    @Column(name = "user_id")
    private Long user_id;

    @PrePersist
    public void prePersist() {
        // Устанавливаем поле date на текущую дату при создании задачи
        date = new Date();
    }

}
