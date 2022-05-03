/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yousr
 */

public class ActionCreateReservation extends Action{
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        HttpSession session = request.getSession();
        Long customerId = (Long)session.getAttribute("userId");
        User customer = service.findUserById(customerId);
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Offer offer = service.findOfferById(offerId);
        Date reservationDate = new Date();
        Integer nbOfPortions = Integer.parseInt(request.getParameter("nbOfPortions"));
        Reservation reservation=new Reservation(reservationDate, ReservationState.REQUEST, nbOfPortions, offer, customer);
        service.createReservation(reservation);
        request.setAttribute("reservation",reservation);
    }
    
}