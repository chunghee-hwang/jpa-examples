package com.example.jpa.main;

import com.example.jpa.domain.BestPick;
import com.example.jpa.domain.User;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;

public class OneToOnePrimaryKeyJoinColumnMain {

    public static void main(String[] args) {
        EMF.init();

//        saveUser();
        save();
        EMF.close();
    }

    private static void saveUser() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = new User("a@a.com", "유저1", LocalDateTime.now());
            em.persist(user);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void save() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, "a@a.com");
            BestPick bestPick = new BestPick(user, "제목");
            em.persist(bestPick);

            tx.commit();
            // insert into best_pick (title, user_email) values (?, ?)
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void template() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
