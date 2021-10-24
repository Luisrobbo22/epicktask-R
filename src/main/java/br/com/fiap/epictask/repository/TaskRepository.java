package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Task;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> findByTitleContaining(String title, Pageable pageable);

	@Query("Select t from Task t where t.status = 100")
	List<Task> listTaskFinalized();

	@Query("Select t from Task t where t.status < 100")
	List<Task> listTaskNotFinalized();

}
