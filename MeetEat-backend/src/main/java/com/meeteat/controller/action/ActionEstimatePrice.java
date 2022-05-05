/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.PriceEstimate;
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
 * @author johanbonnedahl
 */
public class ActionEstimatePrice extends Action{
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        List<String> ingredients = new LinkedList<>();
        for (String ingredientName : request.getParameterValues("ingredients")){
            ingredients.add(ingredientName);
        }

        PriceEstimate estimate = service.getMinMaxPriceFromStrings(ingredients);
        request.setAttribute("priceestimate",estimate);
    }
}
