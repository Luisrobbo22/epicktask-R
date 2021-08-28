package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	//select WHERE title LIKE '%api%'
	Page<Task> findByTitleLike(String title, Pageable pageable);

}
