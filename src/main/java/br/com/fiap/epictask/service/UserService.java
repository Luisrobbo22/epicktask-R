package br.com.fiap.epictask.service;

import br.com.fiap.epictask.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    void create(User user);

    User configurePassword(User user);

    List<User> remove(Long id);

    List<User> update(User newUser, User userParam);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(User user);

    List<User> findByPoints();
}
