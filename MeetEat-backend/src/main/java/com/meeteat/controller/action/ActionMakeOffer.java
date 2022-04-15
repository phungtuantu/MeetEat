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
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gvnge
 */
public class ActionMakeOffer extends Action {

    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        HttpSession session = request.getSession();
        Long cookId = (Long)session.getAttribute("userId");
        Cook cook = service.findCookById(cookId);
        Date currentTime = new Date();
        String title = request.getParameter("title");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer totalPortions = Integer.parseInt(request.getParameter("totalPortions"));
        String details = request.getParameter("details");
        List<Ingredient> ingredients = new LinkedList<>();
        for (String ingredientId : request.getParameterValues("ingredients")){
            ingredients.add((Ingredient)(service.findPreferanceTagById(Long.parseLong(ingredientId))));
        }
        List<PreferenceTag> classifications = new LinkedList<>();
        for (String preferenceTagId : request.getParameterValues("preferences")){
            classifications.add(service.findPreferanceTagById(Long.parseLong(preferenceTagId)));
        }
        String specifications = (String)session.getAttribute("specifications");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipCode");
        Offer offer = new Offer(cook, currentTime, title, price, totalPortions, details,
                classifications, ingredients, specifications,address,city,zipCode);
        service.makeOffer(offer);
        request.setAttribute("offer",offer);
    }
    
}
