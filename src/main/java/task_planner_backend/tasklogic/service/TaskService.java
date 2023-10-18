package task_planner_backend.tasklogic.service;

import task_planner_backend.tasklogic.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();
    public Task getTaskById(Long id);
    public boolean updateTask(Long id, Task updatedTask);
    public void createTask(Task task);
    public boolean deleteTask(Long id);
    public void deleteAllTasks();
}
