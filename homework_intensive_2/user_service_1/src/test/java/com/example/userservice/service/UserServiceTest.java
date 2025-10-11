package com.example.userservice.service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.model.User;
import com.example.userservice.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userDao);
    }

    @Test
    public void testCreateUser() throws DaoException {
        User user = new User("John Doe", "johndoe@example.com", 30);
        when(userDao.create(user)).thenReturn(user);

        User createdUser = userService.createUser("John Doe", "johndoe@example.com", 30);

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("johndoe@example.com", createdUser.getEmail());
        verify(userDao, times(1)).create(user);  // Проверка, что метод create был вызван
    }

    @Test
    public void testGetUserById() throws DaoException {
        User user = new User("Jane Doe", "janedoe@example.com", 28);
        when(userDao.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.getUserById(1L).orElseThrow();

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void testUpdateUser() throws DaoException {
        User user = new User("Jake Doe", "jake@example.com", 40);
        when(userDao.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userDao.update(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1L, "Jake Updated", "jakeupdated@example.com", 42);

        assertEquals("Jake Updated", updatedUser.getName());
        assertEquals("jakeupdated@example.com", updatedUser.getEmail());
        assertEquals(42, updatedUser.getAge());
    }

    @Test
    public void testDeleteUser() throws DaoException {
        User user = new User("Mike Doe", "mike@example.com", 22);
        when(userDao.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userDao.delete(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);
        assertTrue(result);
        verify(userDao, times(1)).delete(1L);
    }
}