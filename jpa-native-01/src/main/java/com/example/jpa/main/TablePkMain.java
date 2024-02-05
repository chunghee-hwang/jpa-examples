package com.example.jpa.main;

import com.example.jpa.domain.AccessLog;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

// 테이블에서 PK를 가져오는 방식 테스트
public class TablePkMain {
    private static final Logger logger = LoggerFactory.getLogger(TablePkMain.class);
    public static void main(String[] args) {
        EMF.init();
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            AccessLog log = new AccessLog("/path01", LocalDateTime.now());
            logger.info("persist 실행 전");
            em.persist(log); // select tbl.nextval from id_seq tbl where tbl.entity=? for update, update id_seq set nextval=?  where nextval=? and entity=?
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", log.getId());
            logger.info("커밋하기 전");
            tx.commit(); // insert into access_log (created, path, id) values (?, ?, ?)
            logger.info("커밋 함");
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            EMF.close();
        }
    }
}
