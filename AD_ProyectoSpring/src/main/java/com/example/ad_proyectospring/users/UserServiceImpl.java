package com.example.ad_proyectospring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
}
