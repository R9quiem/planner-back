package task_planner_backend.tasklogic.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import task_planner_backend.tasklogic.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user_id = :user_id" )
    List<Task> findAllTasksByUserId(Long user_id, Sort sort);
}