package com.example.jpa.service;

import com.example.jpa.domain.User;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class UserService {
    public void changeName(String email, String newName) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = Optional.ofNullable(em.find(User.class, email)).orElseThrow(() -> new RuntimeException("User not found"));
            user.setName(newName);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public User getUser(String email) {
        EntityManager em = EMF.createEntityManager();
        try {
            return Optional
                    .ofNullable(em.find(User.class, email))
                    .orElseThrow(() -> new RuntimeException("No user"));
        } finally {
            em.close();
        }

    }

    public void removeUser(String email) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = Optional.ofNullable(
                    em.find(User.class, email)
            ).orElseThrow(() -> new RuntimeException("No user"));
            em.remove(user);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void saveNewUser(User user) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
