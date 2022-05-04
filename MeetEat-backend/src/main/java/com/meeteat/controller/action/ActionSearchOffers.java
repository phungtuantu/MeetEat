/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gvnge
 */
public class ActionSearchOffers extends Action {

    @Override
    public void executer(HttpServletRequest request){
        //seperate the preferences ids with comas
        Service service = new Service();
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
//        Long userId = Long.parseLong(request.getParameter("userId"));
        User user = service.findUserById(userId);
        int priceLimit = Integer.parseInt(request.getParameter("priceLimit"));
        List<Long> requestPreferences = new LinkedList<>();
        for (String preferenceId : request.getParameterValues("requestPreferences")){
            if(!preferenceId.isEmpty()){
                requestPreferences.add(Long.parseLong(preferenceId));
            }
        }
                      
//        List<Long> requestPreferences = new LinkedList<>();
//        String[] split = request.getParameter("requestPreferences").split(",");
//        for (int i=0; i< split.length; i++){
//            requestPreferences.add(Long.parseLong(split[i]));
//        }
//        for (String preferenceId : request.getParameterValues("requestPreferences")){
//            requestPreferences.add(Long.parseLong(preferenceId));
//        }
        List <Offer> offers=service.searchOffers(requestPreferences, priceLimit, user);
        request.setAttribute("offers",offers);
    }
    
}
