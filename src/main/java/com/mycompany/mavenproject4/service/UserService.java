package com.mycompany.mavenproject4.service;

import com.mycompany.mavenproject4.entities.User;
import com.mycompany.mavenproject4.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User", User.class).list();
        session.close();
        return users;
    }

    public void saveUser(User user) {
        user.setPassword(hashPasswordIfNeeded(user.getPassword()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    public void updateUser(User user) {
        user.setPassword(hashPasswordIfNeeded(user.getPassword()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        session.close();
    }

    public void deleteUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, userId);
        if (user != null) {
            user.setDeleted(true);
            session.update(user);
        }
        tx.commit();
        session.close();
    }

    public User getUserById(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);
        session.close();
        return user;
    }

    private String hashPasswordIfNeeded(String password) {
        if (password == null) return null;
        // Si ya es un hash SHA-256 (64 hex), no volver a hashear
        if (password.matches("^[a-fA-F0-9]{64}$")) return password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
} 