package task_planner_backend.tasklogic.model.dto;

import lombok.Data;
import task_planner_backend.tasklogic.model.EPriority;
import task_planner_backend.tasklogic.model.EState;

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
}
