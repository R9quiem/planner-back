package task_planner_backend.tasklogic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String priority;

    @Column(name = "state")
    private String state;

    @PrePersist
    public void prePersist() {
        // Устанавливаем поле date на текущую дату при создании задачи
        date = new Date();
    }

}
