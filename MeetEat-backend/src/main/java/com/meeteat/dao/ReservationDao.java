/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Reservation;

/**
 *
 * @author gvnge
 */
public class ReservationDao extends AbstractDao<Reservation> {
    public ReservationDao() {
        super(Reservation.class);
    }
}
