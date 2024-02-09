package com.example.jpa.main;

import com.example.jpa.domain.Player;
import com.example.jpa.domain.Review;
import com.example.jpa.domain.Sight;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ManyToOneMain {
    public static void main(String[] args) {
        EMF.init();
//        saveSight();
//        saveReview();
//        get();
        getWithJPQL();
        EMF.close();
    }

    private static void saveSight() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Sight sight = new Sight("S-01", "경복궁");
            em.persist(sight);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void saveReview() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Sight sight = em.find(Sight.class, "S-01");
            Review review = new Review(sight, 5, "댓글댓글");
            em.persist(review);
            tx.commit();
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
            Review review = em.find(Review.class, 1L);
            Sight sight = review.getSight();
            String name = sight.getName();
            tx.commit();
            /*
            select r1_0.id,r1_0.comment,r1_0.grade,s1_0.id,s1_0.name
            from sight_review r1_0
            left join sight s1_0 on s1_0.id=r1_0.sight_id where r1_0.id=?
             */
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void getWithJPQL() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Review> query = em.createQuery(
                    "select r from Review r where r.sight.id = :sightId order by r.id desc",
                    Review.class
            );
            query.setParameter("sightId", "S-01");
            List<Review> results = query.getResultList();

            Sight sight = em.find(Sight.class, "S-01");
            TypedQuery<Review> query2 = em.createQuery(
                    "select r from Review r where r.sight = :sight order by r.id desc",
                    Review.class
            );
            query2.setParameter("sight", sight);
            List<Review> results2 = query2.getResultList();
            tx.commit();
            // select r1_0.id,r1_0.comment,r1_0.grade,r1_0.sight_id from sight_review r1_0 where r1_0.sight_id=? order by r1_0.id desc

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
