/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gvnge
 */
public class ActionSearchOffers extends Action {

    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        User user = service.findUserById(userId);
        int priceRange = Integer.parseInt(request.getParameter("priceRange"));
       
        List<Long> requestPreferences = new LinkedList<>();
        for (String preferenceId : request.getParameterValues("requestPreferences")){
            requestPreferences.add(Long.parseLong(preferenceId));
        }
        PriorityQueue <Offer> offers=service.searchOffers(requestPreferences, priceRange, user);
        request.setAttribute("offers",offers);
    }
    
}
