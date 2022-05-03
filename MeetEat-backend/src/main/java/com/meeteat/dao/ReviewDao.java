/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Review;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class ReviewDao extends AbstractDao<Review>{
    public ReviewDao(){
        super(Review.class);
    }
    
    public List<Review> getOffersReviews(Long idOffer) {
        //returns offers of a cook
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Review> query = em.createQuery("SELECT r from Review r WHERE r.reservation.offer.id = :idOffer"
                + "AND r.reviewingUser = r.reservation.customer ORDER BY DESC r.reservation.reservationDate", Review.class);
        query.setParameter("idOffer",idOffer);
        return query.getResultList();
    }
}
