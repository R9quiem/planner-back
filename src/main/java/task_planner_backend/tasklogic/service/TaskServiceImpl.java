package task_planner_backend.tasklogic.service;

import org.springframework.security.core.context.SecurityContextHolder;
import task_planner_backend.auth.model.User;
import task_planner_backend.tasklogic.service.service.UserDetailsImpl;
import task_planner_backend.tasklogic.model.Task;
import task_planner_backend.tasklogic.model.dto.TaskDTO;
import task_planner_backend.tasklogic.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<TaskDTO> getAllTasks() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();

        List<Task> tasks = taskRepository.findAllTasksByUserId(userId, Sort.by(
                Sort.Order.asc("date"),
                Sort.Order.desc("priority")
        ));

        List<TaskDTO> taskDTOs = new ArrayList<>();

        for (Task task : tasks) {
            TaskDTO taskDTO = TaskDTO.build(task);
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }
    @Override
    public TaskDTO getTaskById(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        Task task = taskRepository.findTaskById(id,userId);

        return TaskDTO.build(task);
    }

    @Override
    public boolean updateTask(Long id, TaskDTO updatedTaskDTO) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = userDetails.getId();

            User user = new User(userId);

            existingTask = Task.build(updatedTaskDTO,user);
            taskRepository.save(existingTask);
            return true;
        }
        return false;
    }
    @Override
    public void createTask(TaskDTO taskDTO) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();

        User user = new User(userId);

        Task task = Task.build(taskDTO,user);

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
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        taskRepository.deleteAllTasksByUserId(userId);
    }

}
