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
    
    public void create(Offer offer) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.persist(offer);
    }
    
    public void merge(Offer offer){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.merge(offer);
    }
    
    public Offer searchById(Long offerId) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        return em.find(Offer.class, offerId); // renvoie null si l'identifiant n'existe pas
    }
    
    
    public List<Offer> getOngoingOffers(int priceLimit) {
        
        //returns offers according to priceLimit
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Offer> query = em.createQuery("SELECT o from offer o WHERE o.price <= :pricelimit AND o.state = :state ", Offer.class);
        query.setParameter("pricelimit",priceLimit);
        query.setParameter("state",1);
        return query.getResultList();
    }
    
}

