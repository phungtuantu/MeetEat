/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gvnge
 */
public class ActionConsultOffer extends Action {
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Offer offer = service.findOfferById(offerId);
        System.out.println(offer);
        request.setAttribute("offer",offer);
    }
}
