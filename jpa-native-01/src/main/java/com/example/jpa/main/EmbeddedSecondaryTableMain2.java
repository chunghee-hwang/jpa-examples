package com.example.jpa.main;

import com.example.jpa.domain.Writer;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// get
public class EmbeddedSecondaryTableMain2 {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedSecondaryTableMain.class);
    public static void main(String[] args) {
        EMF.init();
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            /*
            select
            w1_0.id,w1_0.name,
            w1_1.addr1,w1_1.addr2,w1_1.zipcode,
            w1_2.content,w1_2.content_Type
            from Writer w1_0
            left join writer_address w1_1 on w1_0.id=w1_1.writer_id
            left join writer_intro w1_2 on w1_0.id=w1_2.writer_id
            where w1_0.id=?
             */
            Writer writer = em.find(Writer.class, 8L);
            logger.info("Get writer: {}", writer);

            /*
            delete from writer_address where writer_id=?
             */
            writer.setAddress(null);

            /*
            delete from writer_intro where writer_id=?
            delete from writer_address where writer_id=?
            delete from Writer where id=?
             */
            em.remove(writer);

            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
            EMF.close();
        }
    }
}
