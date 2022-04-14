/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
import com.meeteat.model.User.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class OfferDao {
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
    
//    public Offer searchByFilters(List <Diet> diets, Cuisine cuisine, int priceRange, User user) {
//        //ne pas oublier les infos du user: preferences + localisation
//        // preferences
//        //localisation
//        //diet
//        //cuisine
//        
//        EntityManager em = JpaTool.obtainPersistenceContext();
//        int priceLimit;
//        switch (priceRange) {
//            case 1 -> priceLimit = 5;
//            case 2 -> priceLimit = 9;
//            default -> priceLimit = 20;
//        }
//        TypedQuery<Offer> query = em.createQuery("SELECT O FROM Offer o WHERE o.price <= :priceLimit AND o.classifications");
//        return em.find(Offer.class, offerId); // renvoie null si l'identifiant n'existe pas
//    }
    

    
}
