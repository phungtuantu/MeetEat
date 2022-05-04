/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author johanbonnedahl
 */
public class ActionPublishOffer extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Offer offer = service.publishOffer(offerId);
        request.setAttribute("offer", offer);
    }
    
}
