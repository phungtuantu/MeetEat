/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Offer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class OfferDao extends AbstractDao<Offer>{
    public OfferDao(){
        super(Offer.class);
    }
    
    public List<Offer> getOngoingOffers(int priceLimit) {
        
        //returns offers according to priceLimit
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Offer> query = em.createQuery("SELECT o from offer o WHERE o.price <= :pricelimit AND o.state = :state ", Offer.class);
        query.setParameter("pricelimit",priceLimit);
        query.setParameter("state",1);
        return query.getResultList();
    }
    public List<Offer> getOffers(long idCook) {
        
        //returns offers of a cook
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Offer> query = em.createQuery("SELECT o from Offer o WHERE o.cook.id = :idCook ORDER BY o.creationDate DESC", Offer.class);
        query.setParameter("idCook",idCook);
        return query.getResultList();
    }
    
}

