package com.example.userservice.dao;

import com.example.userservice.model.User;
import com.example.userservice.util.HibernateUtil;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoIntegrationTest {

    private static PostgreSQLContainer<?> postgreSQLContainer;
    private static Session session;
    private static UserDao userDao;

    @BeforeAll
    public static void setUp() {
        // Запуск контейнера PostgreSQL
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("test")
                .withUsername("user")
                .withPassword("password");
        postgreSQLContainer.start();

        // Устанавливаем соединение с БД
        System.setProperty("DB_URL", postgreSQLContainer.getJdbcUrl());
        System.setProperty("DB_USER", postgreSQLContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgreSQLContainer.getPassword());

        // Инициализация Hibernate Session
        session = HibernateUtil.getSessionFactory().openSession();
        userDao = new UserDaoImpl();
    }

    @BeforeEach
    public void beforeEach() {
        // Очистка таблицы перед каждым тестом
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE users RESTART IDENTITY").executeUpdate();
        tx.commit();
    }

    @Test
    public void testCreateUser() {
        User user = new User("John Doe", "johndoe@example.com", 30);
        User createdUser = userDao.create(user);

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("johndoe@example.com", createdUser.getEmail());
    }

    @Test
    public void testFindUserById() {
        User user = new User("Jane Doe", "janedoe@example.com", 28);
        User createdUser = userDao.create(user);

        User foundUser = userDao.findById(createdUser.getId()).orElseThrow();
        assertEquals(createdUser.getId(), foundUser.getId());
        assertEquals(createdUser.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("Jack Doe", "jackdoe@example.com", 35);
        User createdUser = userDao.create(user);

        boolean isDeleted = userDao.delete(createdUser.getId());
        assertTrue(isDeleted);
        assertFalse(userDao.findById(createdUser.getId()).isPresent());
    }

    @AfterAll
    public static void tearDown() {
        session.close();
        postgreSQLContainer.stop();
    }
}