package br.com.fiap.epictask.service.impl;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;
import br.com.fiap.epictask.service.AuthenticationService;
import br.com.fiap.epictask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User configurePassword(User user){
        user.setPassword(
                AuthenticationService.getPasswordEnconder().encode( user.getPassword()));
        return user;
    }

    @Override
    public List<User> remove(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();

        if (Optional.ofNullable(user).isPresent()) {
            userRepository.delete(user);
        }

        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public List<User> update(User newUser, User userParam) {

        newUser.setName(userParam.getName());
        newUser.setEmail(userParam.getEmail());
        newUser.setGithubuser(userParam.getGithubuser());

        userRepository.save(newUser);

        List<User> list = userRepository.findAll();

        return list;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(User user){
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public List<User> findByPoints() {
        return userRepository.findByPoints();
    }

}
