package task_planner_backend.tasklogic.service;

import task_planner_backend.tasklogic.model.Task;
import task_planner_backend.tasklogic.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public List<TaskDTO> getAllTasks();
    public TaskDTO getTaskById(Long id);
    public boolean updateTask(Long id, TaskDTO updatedTaskDTO);
    public void createTask(TaskDTO taskDTO);
    public boolean deleteTask(Long id);
    public void deleteAllTasks();
}
