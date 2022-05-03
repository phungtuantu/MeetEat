/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.VerificationRequest.CookRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author taha
 */
public class CookRequestDao extends AbstractDao<CookRequest>{
    public CookRequestDao() {
        super(CookRequest.class);
    }
    
    public List<CookRequest> searchCookRequests(){
        EntityManager em = JpaTool.obtainPersistenceContext();
        String jpql="select r from CookRequest r";
        TypedQuery query=em.createQuery(jpql, CookRequest.class);
        List<CookRequest> cookRequests = query.getResultList();
        return cookRequests;
    }
}
