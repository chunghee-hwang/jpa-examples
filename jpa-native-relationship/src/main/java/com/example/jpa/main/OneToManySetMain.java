package com.example.jpa.main;

import com.example.jpa.domain.Player;
import com.example.jpa.domain.Team;
import com.example.jpa.helper.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.HashSet;
import java.util.Set;

public class OneToManySetMain {
    public static void main(String[] args) {
        EMF.init();
//        savePlayer();
//        saveTeam();
//        removeAndAddPlayer();
        removeAllPlayers();
        EMF.close();
    }

    private static void savePlayer() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Player p21 = new Player("P-21", "플레이어21");
            Player p22 = new Player("P-22", "플레이어22");
            Player p23 = new Player("P-23", "플레이어23");

            em.persist(p21);
            em.persist(p22);
            em.persist(p23);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void saveTeam() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Player p21 = em.find(Player.class, "P-21");
            Player p22 = em.find(Player.class, "P-22");

            Set<Player> players = new HashSet<>();
            players.add(p21);
            players.add(p22);
            em.persist(new Team("T-02", "팀2", players));

            /*
            insert into team (name, id) values (?, ?)
            update player set team_id=? where id=?
            update player set team_id=? where id=?
             */
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void removeAndAddPlayer() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Player p21 = em.find(Player.class, "P-21");
            Player p23 = em.find(Player.class, "P-23");

            Team team = em.find(Team.class, "T-02");
            team.removePlayer(p21); // update player set team_id=null where team_id=? and id=?
            team.addPlayer(p23); // update player set team_id=? where id=?

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void removeAllPlayers() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Team team = em.find(Team.class, "T-02");
            team.removeAllPlayers(); // update player set team_id=null where team_id=?

            tx.commit();
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
