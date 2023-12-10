package task_planner_backend.tasklogic.model.dto;

import lombok.Data;
import task_planner_backend.tasklogic.model.EPriority;
import task_planner_backend.tasklogic.model.EState;
import task_planner_backend.tasklogic.model.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TaskDTO {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Date date;

    @NotNull
    private EPriority priority;

    @NotNull
    private EState state;

    private String category;

    public TaskDTO(String name, String description, Date date, EPriority priority, EState state, String category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.state = state;
        this.category = category;
    }

    public static TaskDTO build(Task task) {
        return new TaskDTO(
                task.getName(),
                task.getDescription(),
                task.getDate(),
                task.getPriority(),
                task.getState(),
                task.getCategory()
        );
    }
}
