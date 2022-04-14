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
//        Long cookId = (Long)session.getAttribute("cookId");
//        Cook cook = new Cook();
//        Date currentTime = new Date();
//        String title = (String)session.getAttribute("title");
//        Double price = (Double)session.getAttribute("price");
//        Integer totalPortions = (Integer)session.getAttribute("totalPortions");
//        String details = (String)session.getAttribute("details");
//        Cook cook = service.searchCookById(cookId);
//        todo: make list of ingredients and shit
//        List<Long> ingredientIds = 
        List<PreferenceTag> classifications = new LinkedList<>();
        List<Ingredient> ingredients = new LinkedList<>();
        String specifications = (String)session.getAttribute("specifications");
//        Offer offer = new Offer(cook, currentTime, title, price, totalPortions, details, classifications, ingredients, specifications);
        service.makeOffer(offer);
    }
    
}
