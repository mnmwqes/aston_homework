package com.example.userservice.service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.dao.UserDaoImpl;
import com.example.userservice.exception.DaoException;
import com.example.userservice.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();

    public User createUser(String name, String email, Integer age) throws DaoException {
        User user = new User(name, email, age);
        return userDao.create(user);
    }

    public Optional<User> getUserById(Long id) throws DaoException {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() throws DaoException {
        return userDao.findAll();
    }

    public Optional<User> getUserByEmail(String email) throws DaoException {
        return userDao.findByEmail(email);
    }

    public User updateUser(Long id, String name, String email, Integer age) throws DaoException {
        Optional<User> existing = userDao.findById(id);
        if (existing.isEmpty()) {
            throw new DaoException("User not found with id: " + id);
        }
        User u = existing.get();
        if (name != null) u.setName(name);
        if (email != null) u.setEmail(email);
        if (age != null) u.setAge(age);
        return userDao.update(u);
    }

    public boolean deleteUser(Long id) throws DaoException {
        return userDao.delete(id);
    }
}
