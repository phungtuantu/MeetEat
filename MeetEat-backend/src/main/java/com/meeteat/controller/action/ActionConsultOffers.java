/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.service.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gvnge
 */
public class ActionConsultOffers extends Action {

    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        String address = request.getParameter("address");
        int x = service.checkOffersExpirationDate();
        PriorityQueue<Offer> offersQueue = service.consultOffers(address);
        List <Offer> offers= new ArrayList ();
        Offer offer=offersQueue.poll();
        while (offer!=null){
            offers.add(offer);
            offer=offersQueue.poll();
        }
        
        request.setAttribute("offers",offers);
    }
    
}
