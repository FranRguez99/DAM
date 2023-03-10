package com.example.ad_proyectospring.users;

public interface UserService {

    public User saveUser(User user);


    boolean authenticateUser(String username, String password);
}
