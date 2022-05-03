/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Message;
import com.meeteat.model.User.User;
import com.meeteat.model.Offer.Offer;
import com.meeteat.service.Service;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ithan
 */
public class ActionSendMessage extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service(); 
        Long senderId = Long.parseLong(request.getParameter("senderId"));
        Long receiverId = Long.parseLong(request.getParameter("receiverId"));
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        User sender = service.findUserById(senderId);
        User receiver = service.findUserById(receiverId);
        Offer offer = service.findOfferById(offerId);
        String content = request.getParameter("content");
        Message message = new Message(content, sender, receiver, offer, today);
        Message confirmationMessage = service.createMessage(message);
        if (confirmationMessage != null){
            request.setAttribute("error", false);
            request.setAttribute("message", message);
        }
        else{
            request.setAttribute("error", true);
        }
    }    
}
