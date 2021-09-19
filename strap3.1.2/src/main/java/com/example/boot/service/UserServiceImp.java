package com.example.boot.service;

import com.example.boot.DAO.UserDAO;
import com.example.boot.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService{

    private final UserDAO userDAO;

    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> usersList() {
        return userDAO.findAll();
    }

    @Override
    public User showById(long id) {
        Optional<User> userModel = userDAO.findById(id);
        return userModel.get();
    }

    @Override
    public void save(User user) {
        user.setPassw(new BCryptPasswordEncoder().encode(user.getPassw()));
        userDAO.save(user);
    }

    @Override
    public void delete(long id) {
        userDAO.deleteById(id);
    }

    @Override
    public void update(User user, long id) {
        userDAO.save(user);
    }

    @Override
    public User getUserByLogin(String email) {
        return userDAO.findByUserEmail(email);
    }


}
