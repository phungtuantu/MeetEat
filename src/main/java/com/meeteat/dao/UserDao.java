/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meeteat.model.User.User;

/**
 *
 * @author gvnge
 */
public class UserDao {
    public void create(User user) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.persist(user);
    }
    
    public void merge(User user){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.merge(user);
    }
    
    public void delete(User user){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.remove(em.merge(user));
    }
    
    public User searchById(Long userId) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        return em.find(User.class, userId); // renvoie null si l'identifiant n'existe pas
    }
    
    public User SearchByMail(String userMail) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class);
        query.setParameter("mail", userMail); // correspond au paramètre ":mail" dans la requête
        List<User> users = query.getResultList();
        User result = null;
        if (!users.isEmpty()) {
            result = users.get(0); // premier de la liste
        }
        return result;
    }
}
