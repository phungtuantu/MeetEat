/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meeteat.modele.User;

/**
 *
 * @author gvnge
 */
public class UserDao {
    public void creer(User employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public void merge(User employe){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(employe);
    }
    
    public User chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(User.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public User chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<User> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", User.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<User> employes = query.getResultList();
        User result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public User employeSouhaite(String genre){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<User> query = em.createQuery("SELECT e FROM Employe e WHERE e.genre = :genre AND e.disponible = 1 ORDER BY e.count ASC", User.class);
        query.setParameter("genre", genre);
        List<User> employes = query.getResultList();
        User result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<User> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<User> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.count DESC", User.class);
        return query.getResultList();
    }
}
