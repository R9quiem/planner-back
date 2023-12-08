package task_planner_backend.tasklogic.controller;

import task_planner_backend.tasklogic.model.Task;
import task_planner_backend.tasklogic.model.dto.TaskDTO;
import task_planner_backend.tasklogic.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks(Principal principal) {
        List<TaskDTO> tasks = taskService.getAllTasks(principal);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = taskService.getTaskById(id);
        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/task")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO updatedTaskDTO) {
        final boolean updated = taskService.updateTask(id,updatedTaskDTO);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        final boolean deleted = taskService.deleteTask(id);;
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/tasks")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }



}