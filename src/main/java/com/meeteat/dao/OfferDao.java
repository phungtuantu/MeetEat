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
    
    public List<Offer> searchByFilters(List<Long> preferences, List<Long> ingredients, int priceLimit) {
        
        //preferences : list of diets + type of cuisine
        //to do : gestion des ingredients dans la requete SQL et gestion de la localisation des offres
        EntityManager em = JpaTool.obtainPersistenceContext();
        //preferences
        String squery = "SELECT o FROM Offer o WHERE ";
        for(Long idPreference:preferences){
            squery = squery + "CONTAINS (o.classifications,"+idPreference+") AND";
        }
        squery = squery.substring(0, squery.length()-3);
        squery = squery + "AND o.price <= :priceLimit";
        TypedQuery<Offer> query = em.createQuery(squery, Offer.class);
        return query.getResultList();
        
        //        TypedQuery<Offer> query = em.createQuery("SELECT o FROM Offer o WHERE (SELECT c from o.classifications c IN (:searchPrefrences))= ALL (:searchPreferences)", Offer.class);
//query.setParameter("searchPreferences",searchPreferences);
//        TypedQuery<Offer> queryPreferences = em.createQuery("SELECT o FROM Offer o WHERE o. <= :priceLimit AND ");
//        TypedQuery<Offer> query = em.createQuery("SELECT O FROM Offer o WHERE o.price <= :priceLimit AND ");
        
    }
    
    public List<Offer> getOngoingOffers(int priceLimit) {
        
        //returns offers according to priceLimit
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Offer> query = em.createQuery("SELECT o from offer o WHERE o.price <= :pricelimit AND o.state = :state ", Offer.class);
        query.setParameter("pricelimit",priceLimit);
        query.setParameter("state","ONGOING");
        return query.getResultList();
    }
    

    
}
