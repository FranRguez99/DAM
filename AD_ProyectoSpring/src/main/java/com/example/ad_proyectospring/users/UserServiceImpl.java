package com.example.ad_proyectospring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
