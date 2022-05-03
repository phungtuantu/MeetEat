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
import com.meeteat.model.User.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class CookDao extends AbstractDao<Cook>{
    public CookDao() {
        super(Cook.class);
    }
    public Cook getCookByUserId(long userId) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Cook> query = em.createQuery("SELECT c FROM Cook c WHERE c.user.id = :userId", Cook.class);
        query.setParameter("userId", userId); // correspond au paramètre ":mail" dans la requête
        List<Cook> cooks = query.getResultList();
        Cook result = null;
        if (!cooks.isEmpty()) {
            result = cooks.get(0); // premier de la liste
        }
        return result;
    }
}

