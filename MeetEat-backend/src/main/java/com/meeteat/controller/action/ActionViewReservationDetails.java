/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Reservation;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gvnge
 */
public class ActionViewReservationDetails extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long reservationId = Long.parseLong(request.getParameter("reservationId"));
        System.out.println(reservationId);
        Reservation reservation= service.findReservationById(reservationId);
        request.setAttribute("reservation",reservation);
    }
    
}
