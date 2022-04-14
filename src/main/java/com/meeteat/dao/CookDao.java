/**
 *
 * @author gvnge
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;


import com.meeteat.model.User.Cook;
import javax.persistence.EntityManager;

/**
 *
 * @author gvnge
 */
public class CookDao {
    public void create(Cook cook) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.persist(cook);
    }
    
    public void merge(Cook cook){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.merge(cook);
    }
    
    public void delete(Cook cook){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.remove(cook);
    }
    
    public Cook searchById(Long cookId) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        return em.find(Cook.class, cookId); // renvoie null si l'identifiant n'existe pas
    }
}

