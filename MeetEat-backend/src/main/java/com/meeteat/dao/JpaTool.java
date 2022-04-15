/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author gvnge
 */
public class JpaTool {
    public static final String PERSISTENCE_UNIT_NAME = "meeteatPU";

    private static EntityManagerFactory entityManagerFactory = null;

    private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<EntityManager>() {

        @Override
        protected EntityManager initialValue() {
            return null;
        }
    };

    private static void log(String message) {
        Logger.getAnonymousLogger().log(Level.INFO, "[JpaUtil:Log] " + message);
    }

    public static synchronized void init() {
        log("Initialize the persistence factory");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized void destroy() {
        log("Free the persistence factory");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    public static void createPersistenceContext() {
        log("Create the persistence context");
        threadLocalEntityManager.set(entityManagerFactory.createEntityManager());
    }

    public static void closePersistenceContext() {
        log("Close the persistence context");
        EntityManager em = threadLocalEntityManager.get();
        em.close();
        threadLocalEntityManager.set(null);
    }

    public static void openTransaction() throws Exception {
        log("Begin opening transaction");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().begin();
        } catch (Exception ex) {
            log("Error in opening transaction");
            throw ex;
        }
    }

    public static void validateTransaction() throws RollbackException, Exception {
        log("Commit transaction");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().commit();
        } catch (Exception ex) {
            log("Error in commit");
            throw ex;
        }
    }

    public static void cancelTransaction() {
        try {
            log("Begin rollback of the transaction");

            EntityManager em = threadLocalEntityManager.get();
            if (em.getTransaction().isActive()) {
                log("Rollback of the transaction");
                em.getTransaction().rollback();
            }

        } catch (Exception ex) {
            log("Error in rolling back the transaction");
        }
    }

    protected static EntityManager obtainPersistenceContext() {
        log("Obtain the persistence context");
        return threadLocalEntityManager.get();
    }
}
