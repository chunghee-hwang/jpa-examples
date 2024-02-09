package com.example.jpa.helper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static EntityManagerFactory emf;

    public static void init() {
        if (emf != null) {
            return;
        }
        emf = Persistence.createEntityManagerFactory("jpabegin");
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf == null) {
            return;
        }
        emf.close();
    }
}
