/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.service.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author johanbonnedahl
 */
public class ActionPublishOffer extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Date expirationDate = null;
        try {
            expirationDate = df.parse(request.getParameter("expirationDate"));
        } catch (ParseException ex) {
            Logger.getLogger(ActionPublishOffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Offer offer = service.publishOffer(offerId, expirationDate);
        request.setAttribute("offer", offer);
    }
    
}
