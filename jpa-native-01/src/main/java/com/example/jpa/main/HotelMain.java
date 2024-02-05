package com.example.jpa.main;

import com.example.jpa.domain.Address;
import com.example.jpa.domain.Hotel;
import com.example.jpa.domain.Review;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class HotelMain {
    private final static Logger logger = LoggerFactory.getLogger(HotelMain.class);
    public static void main(String[] args) {
        EMF.init();
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Hotel hotel = new Hotel("H-01", new Address("주소1", "주소2", "12345"),
                    "hotel 1", 2015, Hotel.Grade.BRONZE, LocalDateTime.now(), LocalDateTime.now());
            em.persist(hotel);

            Review review = new Review(hotel.getId(), 5, "작성자", "댓글");
            logger.info("persist 실행 전");
            em.persist(review);
            logger.info("persist 실행 함"); // auto increment가 붙으면 commit 전에 persist만 호출해도 insert 쿼리가 나간다.
            logger.info("생성한 식별자: {}", review.getId());
            logger.info("커밋 하기 전");
            tx.commit();
            logger.info("커밋함");
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            EMF.close();
        }
    }
}
