/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yousr
 */
public class ActionConsultGuestsList extends Action{
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Offer offer = service.findOfferById(offerId);
        List<User> guests=service.consultGuestsList(offer);
        request.setAttribute("users",guests);
    }
}
