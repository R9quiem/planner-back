package com.example.plannerbackend.service;

import com.example.plannerbackend.entity.Task;
import com.example.plannerbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();
    public Task getTaskById(Long id);
    public Task updateTask(Long id, Task updatedTask);
    public void createTask(Task task);
    public void deleteTask(Long id);
    public void deleteAllTasks();
}
