package com.example.jpa.main;

import com.example.jpa.domain.Choice;
import com.example.jpa.domain.Question;
import com.example.jpa.helper.EMF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ElementCollectionListMain {
    public static void main(String[] args) {
        EMF.init();
        save();
        get();
        replaceList();
//        deleteEntity();
        EMF.close();
    }

    private static void save() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            Question q = new Question("1", "질문", List.of(new Choice("보기1", true), new Choice("보기2", false)));
            em.persist(q);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void get() {
        Logger logger = LoggerFactory.getLogger(ElementCollectionListMain.class);
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            // select q1_0.id,c1_0.question_id,c1_0.idx,c1_0.text,q1_0.text from question q1_0 left join question_choice c1_0 on q1_0.id=c1_0.question_id where q1_0.id=?
            Question q = em.find(Question.class, "1");
            logger.info("보기 개수: {}", q.getChoices().size());

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void replaceList() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            Question q = em.find(Question.class, "1");
            q.setChoices(List.of(new Choice("답1", true), new Choice("답2", false)));
            tx.commit();
            // delete from question_choice where question_id=?
            // insert into question_choice (question_id, idx, text) values (?, ?, ?)
            // insert into question_choice (question_id, idx, text) values (?, ?, ?)
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void deleteEntity() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            Question q = em.find(Question.class, "1");
            em.remove(q);
            tx.commit();
            // delete from question_choice where question_id=?
            // delete from question where id=?
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
