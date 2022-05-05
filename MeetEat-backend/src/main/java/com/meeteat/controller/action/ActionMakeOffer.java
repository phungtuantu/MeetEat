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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Long userId = (Long)session.getAttribute("userId");
        Cook cook = service.findCookByUserId(userId);
        Date availableFrom;
        try {
            availableFrom = getDate(request.getParameter("fromDate"));
        } catch (Exception ex) {
            Logger.getLogger(ActionMakeOffer.class.getName()).log(Level.SEVERE, null, ex);
            availableFrom = null;
        }
        String title = request.getParameter("title");
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
        String zipCode = request.getParameter("zipcode");
        //System.out.println(specifications);
        Date expirationDate;
        try {
            expirationDate = getDate(request.getParameter("expDate"));
        } catch (Exception ex) {
            Logger.getLogger(ActionMakeOffer.class.getName()).log(Level.SEVERE, null, ex);
            expirationDate = null;
        }
        String offerPhotoPath = request.getParameter("photoPath");
        Offer offer = new Offer(cook, availableFrom, title, totalPortions, details,
                classifications, ingredients, specifications,address,city,zipCode, expirationDate, offerPhotoPath);
        service.makeOffer(offer);
        request.setAttribute("offer",offer);
    }
    
    private Date getDate(String dateString) throws Exception{
        Date expirationDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        return expirationDate ;
    }
}
