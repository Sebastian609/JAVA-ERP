package com.mycompany.mavenproject4.controller;

import com.mycompany.mavenproject4.entities.User;
import com.mycompany.mavenproject4.service.UserService;
import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }
} 