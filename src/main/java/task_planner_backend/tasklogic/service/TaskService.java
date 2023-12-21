package task_planner_backend.tasklogic.service;

import task_planner_backend.tasklogic.model.Task;
import task_planner_backend.tasklogic.model.dto.TaskDTO;

import java.security.Principal;
import java.util.List;

public interface TaskService {

    public List<TaskDTO> getAllTasks(Principal principal);
    public TaskDTO getTaskById(Long id);
    public boolean updateTask(Long id, TaskDTO updatedTaskDTO);
    public Task createTask(TaskDTO taskDTO);
    public boolean deleteTask(Long id);
    public void deleteAllTasks();
}
