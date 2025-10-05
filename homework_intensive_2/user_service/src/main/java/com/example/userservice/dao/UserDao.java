package main.java.com.example.userservice.dao;

import main.java.com.example.userservice.model.User;
import main.java.com.example.userservice.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user) throws DaoException;
    Optional<User> findById(Long id) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    List<User> findAll() throws DaoException;
    User update(User user) throws DaoException;
    boolean delete(Long id) throws DaoException;
}
