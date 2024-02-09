package com.example.jpa.main;

import com.example.jpa.domain.Game;
import com.example.jpa.domain.Member;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.HashMap;
import java.util.Map;

public class OneToManyMapMain {
    public static void main(String[] args) {
        EMF.init();

        saveMember();
        saveGame();
        addAndRemoveMember();
        EMF.close();
    }

    private static void saveMember() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(new Member("M1", "멤버1"));
            em.persist(new Member("M2", "멤버2"));
            em.persist(new Member("M3", "멤버3"));
            em.persist(new Member("M4", "멤버3"));
            em.persist(new Member("M5", "멤버3"));
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void saveGame() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Member m1 = em.find(Member.class, "M1");
            Member m2 = em.find(Member.class, "M2");
            Member m3 = em.find(Member.class, "M3");

            Map<String, Member> members = new HashMap<>();
            members.put("C", m1);
            members.put("PG", m2);
            members.put("SG", m3);
            Game g = new Game("G1", "게임1", members);
            em.persist(g);
            tx.commit();
            /*
            insert into game (name, id) values (?, ?)
            update game_member set game_id=?, role_name=? where id=?
            update game_member set game_id=?, role_name=? where id=?
            update game_member set game_id=?, role_name=? where id=?
             */
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void addAndRemoveMember() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Game game = em.find(Game.class, "G1");
            Member m4 = em.find(Member.class, "M4");
            Member m5 = em.find(Member.class, "M5");
            game.putMember("C", m4);
            game.putMember("PF", m5);
            game.removeMember("SG");
            tx.commit();
            /*
            update game_member set game_id=null, role_name=null where game_id=? and id=?
            update game_member set game_id=null, role_name=null where game_id=? and id=?
            update game_member set game_id=?, role_name=? where id=?
            update game_member set game_id=?, role_name=? where id=?
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
