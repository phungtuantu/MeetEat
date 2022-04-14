/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Reservation;

/**
 *
 * @author johanbonnedahl
 */
public class ReservationDao extends AbstractDao<Reservation> {
    public ReservationDao(){
        super(Reservation.class);
    }
}
