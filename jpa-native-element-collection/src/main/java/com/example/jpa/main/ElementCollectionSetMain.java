package com.example.jpa.main;

import com.example.jpa.domain.GrantedPermission;
import com.example.jpa.domain.Role;
import com.example.jpa.helper.EMF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ElementCollectionSetMain {
    public static void main(String[] args) {
        EMF.init();
        save();
        get();
        addAndRemove();
        replaceSet();
        clearSet();
        EMF.close();
    }

    private static void save() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            String roleId = "testId";
            Role role = new Role(roleId, "관리자",
                    Set.of(
                            new GrantedPermission("F1", "grantor1"),
                            new GrantedPermission("F2", "grantor1")
                    )
            );
            em.persist(role);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void get() {
        Logger logger = LoggerFactory.getLogger(ElementCollectionSetMain.class);
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            String roleId = "testId";
            Role role = em.find(Role.class, roleId);
            for (GrantedPermission perm : role.getPermissions()) {
                logger.info("perm: {}", perm);
            }
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void addAndRemove() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            String roleId = "testId";
            Role role = em.find(Role.class, roleId);
            role.getPermissions().add(new GrantedPermission("F3", "grantor1")); // insert into role_perm (role_id, perm) values (?, ?)
            role.getPermissions().remove(new GrantedPermission("F1", "grantor1")); // delete from role_perm where role_id=? and perm=?
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void replaceSet() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            String roleId = "testId";
            Role role = em.find(Role.class, roleId);
            role.setPermissions(
                    Set.of(
                            new GrantedPermission("F4", "grantor1"),
                            new GrantedPermission("F5", "grantor1")
                    )
            );
            tx.commit();
            // delete from role_perm where role_id=?
            // insert into role_perm (role_id, perm) values (?, ?)
            // insert into role_perm (role_id, perm) values (?, ?)
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void clearSet() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            String roleId = "testId";
            Role role = em.find(Role.class, roleId);
            role.clearPermissions(); // delete from role_perm where role_id=?
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
