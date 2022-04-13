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
public class JpaUtil {
    public static final String PERSISTENCE_UNIT_NAME = "TP-DASI-PU";

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
        log("Initialisation de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized void destroy() {
        log("Libération de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    public static void creerContextePersistance() {
        log("Création du contexte de persistance");
        threadLocalEntityManager.set(entityManagerFactory.createEntityManager());
    }

    public static void fermerContextePersistance() {
        log("Fermeture du contexte de persistance");
        EntityManager em = threadLocalEntityManager.get();
        em.close();
        threadLocalEntityManager.set(null);
    }

    public static void ouvrirTransaction() throws Exception {
        log("Ouverture de la transaction (begin)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().begin();
        } catch (Exception ex) {
            log("Erreur lors de l'ouverture de la transaction");
            throw ex;
        }
    }

    public static void validerTransaction() throws RollbackException, Exception {
        log("Validation de la transaction (commit)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().commit();
        } catch (Exception ex) {
            log("Erreur lors de la validation (commit) de la transaction");
            throw ex;
        }
    }

    public static void annulerTransaction() {
        try {
            log("Annulation de la transaction (rollback)");

            EntityManager em = threadLocalEntityManager.get();
            if (em.getTransaction().isActive()) {
                log("Annulation effective de la transaction (rollback d'une transaction active)");
                em.getTransaction().rollback();
            }

        } catch (Exception ex) {
            log("Erreur lors de l'annulation (rollback) de la transaction");
        }
    }

    protected static EntityManager obtenirContextePersistance() {
        log("Obtention du contexte de persistance");
        return threadLocalEntityManager.get();
    }
}
