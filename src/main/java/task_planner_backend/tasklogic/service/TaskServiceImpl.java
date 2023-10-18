package task_planner_backend.tasklogic.service;

import task_planner_backend.tasklogic.entity.Task;
import task_planner_backend.tasklogic.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("priority")));
    }
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    @Override
    public boolean updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(updatedTask.getName());
                    task.setDate(updatedTask.getDate());
                    task.setPriority(updatedTask.getPriority());
                    task.setState(updatedTask.getState());
                    taskRepository.save(task);
                    return true;
                })
                .orElse(false);
    }
    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }
    @Override
    public boolean deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);
        if(exists) {
            taskRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
