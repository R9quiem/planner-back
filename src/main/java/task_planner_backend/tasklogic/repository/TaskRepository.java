package task_planner_backend.tasklogic.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task_planner_backend.tasklogic.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task_planner_backend.tasklogic.model.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId" )
    List<TaskDTO> findAllTasksByUserId(@Param("userId") Long userId, Sort sort);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.user.id = :userId")
    void deleteAllTasksByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.user.id = :userId")
    TaskDTO findTaskById(@Param("id")Long id, @Param("userId")Long userId);

}