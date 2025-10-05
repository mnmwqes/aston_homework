package com.example.userservice.dao;

import com.example.userservice.exception.DaoException;
import com.example.userservice.model.User;
import com.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User create(User user) throws DaoException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            logger.info("Created user: {}", user);
            return user;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error creating user", e);
            throw new DaoException("Failed to create user", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error finding user by id", e);
            throw new DaoException("Failed to find user", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> q = session.createQuery("FROM User u WHERE u.email = :email", User.class);
            q.setParameter("email", email);
            User user = q.uniqueResultOptional().orElse(null);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error finding user by email", e);
            throw new DaoException("Failed to find user by email", e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            logger.error("Error finding all users", e);
            throw new DaoException("Failed to find users", e);
        }
    }

    @Override
    public User update(User user) throws DaoException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            logger.info("Updated user: {}", user);
            return user;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating user", e);
            throw new DaoException("Failed to update user", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user == null) {
                tx.rollback();
                return false;
            }
            session.remove(user);
            tx.commit();
            logger.info("Deleted user: {}", id);
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting user", e);
            throw new DaoException("Failed to delete user", e);
        }
    }
}
