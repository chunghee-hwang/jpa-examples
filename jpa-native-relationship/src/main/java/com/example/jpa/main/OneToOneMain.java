package com.example.jpa.main;

import com.example.jpa.domain.MembershipCard;
import com.example.jpa.domain.User;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneToOneMain {
    public static void main(String[] args) {
        EMF.init();
//        saveUser();
//        saveMembershipCard();
        get();
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

    private static void saveMembershipCard() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, "a@a.com");
            MembershipCard card = new MembershipCard("888811112223333", user, LocalDate.of(2024, 5, 31), true);
            em.persist(card);

            MembershipCard card2 = new MembershipCard("1111000022223333", null, LocalDate.of(2024, 5, 31), true);
            em.persist(card2);

            tx.commit();
            /*
            select * from membership_card;
            +------------------+------------+-------------+---------+
            | card_no          | user_email | expiry_date | enabled |
            +------------------+------------+-------------+---------+
            | 1111000022223333 | NULL       | 2024-05-31  |       1 |
            | 888811112223333  | a@a.com    | 2024-05-31  |       1 |
            +------------------+------------+-------------+---------+
             */
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void get() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            MembershipCard card = em.find(MembershipCard.class, "888811112223333");
            User owner = card.getOwner();
            tx.commit();
            /*
            select
                m1_0.card_no,m1_0.enabled,m1_0.expiry_date,
                o1_0.email,o1_0.create_date,o1_0.name
            from membership_card m1_0
            left join user o1_0 on o1_0.email=m1_0.user_email
            where m1_0.card_no=?
             */
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
