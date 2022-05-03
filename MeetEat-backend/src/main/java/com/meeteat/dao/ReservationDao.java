/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.User.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class ReservationDao extends AbstractDao<Reservation> {
    public ReservationDao() {
        super(Reservation.class);
    }
    
    public List<Reservation> searchPurchasedMeals(User user){
        EntityManager em = JpaTool.obtainPersistenceContext();
        String jpql="select r from Reservation r where r.customer= :customer and r.state = :purchasedState";
        TypedQuery query=em.createQuery(jpql, Reservation.class);
        query.setParameter("customer",user);
        query.setParameter("purchasedState",ReservationState.PURCHASEDMEAL);
        List<Reservation> purchasedMeals = query.getResultList();
        return purchasedMeals;
    }
    public List<Reservation> getReservationsListByUserId(Long userId){
        EntityManager em = JpaTool.obtainPersistenceContext();
        String jpql="select r from Reservation r where r.customer.id= :userId";
        TypedQuery query=em.createQuery(jpql, Reservation.class);
        query.setParameter("userId",userId);
        List<Reservation> reservations = query.getResultList();
        return reservations;
    }
}
