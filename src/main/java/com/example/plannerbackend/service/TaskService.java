package com.example.plannerbackend.service;

import com.example.plannerbackend.entity.Task;
import com.example.plannerbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("priorityId")));
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll() { taskRepository.deleteAll();}
}
