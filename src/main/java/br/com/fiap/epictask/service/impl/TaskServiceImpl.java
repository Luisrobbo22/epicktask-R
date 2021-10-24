package br.com.fiap.epictask.service.impl;

import br.com.fiap.epictask.exception.NotAllowedException;
import br.com.fiap.epictask.exception.TaskNotFoundException;
import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.TaskRepository;
import br.com.fiap.epictask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Task task) {
        repository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Task buildTaskRelease(Long id, Authentication auth){
        Optional<Task> optional = findById(id);

        Task task = optional.get();
        if (optional.isEmpty())
            throw new TaskNotFoundException("Tarefa não encontrada");

        User user = (User) auth.getPrincipal();

        if (task.getUser() != user)
            throw new NotAllowedException("Essa tarefa não está atribuída para você");

        task.setUser(null);
        return task;
    }

    @Override
    public Task buildTaskHold(Long id, Authentication auth) {
        Optional<Task> optional = findById(id);

        Task task = optional.get();

        if (optional.isEmpty())
            throw new TaskNotFoundException("Tarefa não encontrada");


        if (task.getUser() != null)
            throw new NotAllowedException("Tarefa já está atribuída para outro usuário");

        User user = (User) auth.getPrincipal();
        task.setUser(user);

        return task;
    }

    @Override
    public List<Task> listTaskFinalized() {
        return repository.listTaskFinalized();
    }

    @Override
    public List<Task> listTaskNotFinalized() {
        return repository.listTaskNotFinalized();
    }


}
