package com.example.jpa.main;

import com.example.jpa.domain.Address;
import com.example.jpa.domain.Intro;
import com.example.jpa.domain.Writer;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// embedded를 이용하여 다른 테이블 매핑
public class EmbeddedSecondaryTableMain {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedSecondaryTableMain.class);
    public static void main(String[] args) {
        EMF.init();
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Writer writer = new Writer(
                    "name",
                    new Intro("text/plain", "소개글"),
                    new Address("주소1", "주소2", "12345")
            );
            em.persist(writer);

            // null을 집어넣으면 intro는 insert되지 않음
            Writer writer2 = new Writer("name2", null, new Address("주소3", "주소4", "55656"));
            em.persist(writer2);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
            EMF.close();
        }
    }
}
