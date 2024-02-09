package com.example.jpa.main;

import com.example.jpa.domain.Player;
import com.example.jpa.domain.Question;
import com.example.jpa.domain.Survey;
import com.example.jpa.domain.Team;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyListMain {
    public static void main(String[] args) {
        EMF.init();
//        saveQuestion();
//        saveSurvey();
        addAndDeleteQuestion();
        EMF.close();
    }

    private static void saveQuestion() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(new Question("Q1", "질문1"));
            em.persist(new Question("Q2", "질문2"));
            em.persist(new Question("Q3", "질문3"));
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void saveSurvey() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Question q1 = em.find(Question.class, "Q1");
            Question q2 = em.find(Question.class, "Q2");
            Question q3 = em.find(Question.class, "Q3");
            List<Question> qs = new ArrayList<>();
            qs.add(q1);
            qs.add(q2);
            qs.add(q3);
            em.persist(new Survey("S1", "설문", qs));
            tx.commit();
            /*
            insert into survey (name, id) values (?, ?)
            update survey_question set survey_id=?, order_no=? where id=?
            update survey_question set survey_id=?, order_no=? where id=?
            update survey_question set survey_id=?, order_no=? where id=?
            */
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void addAndDeleteQuestion() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Question q1 = em.find(Question.class, "Q1");
            Question q4 = em.find(Question.class, "Q4");

            Survey survey = em.find(Survey.class, "S1");
            survey.removeQuestion(q1);
            survey.addQuestion(q4);

            tx.commit();
            /*
            update survey_question set survey_id=null, order_no=null where survey_id=? and id=?
            update survey_question set survey_id=null, order_no=null where survey_id=? and id=?
            update survey_question set survey_id=null, order_no=null where survey_id=? and id=?
            update survey_question set survey_id=?, order_no=? where id=?
            update survey_question set survey_id=?, order_no=? where id=?
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
