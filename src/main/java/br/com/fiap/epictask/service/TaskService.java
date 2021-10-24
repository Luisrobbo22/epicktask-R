package br.com.fiap.epictask.service;

import br.com.fiap.epictask.model.Task;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    void create(Task task);

    Optional<Task> findById(Long id);

    Task buildTaskRelease(Long id, Authentication auth);

    Task buildTaskHold(Long id, Authentication auth);

    List<Task> listTaskFinalized();

    List<Task> listTaskNotFinalized();


}
