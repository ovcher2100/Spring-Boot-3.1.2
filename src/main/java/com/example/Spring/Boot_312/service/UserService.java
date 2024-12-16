package com.example.Spring.Boot_312.service;



import com.example.Spring.Boot_312.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findById(Long id);
    void delete(Long id);
    void update(User user);
    List<User> findAll();
}
