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
        System.out.println("Test");
        Service service = new Service();
        List<Ingredient> ingredients = new LinkedList<>();
        for (String ingredientId : request.getParameterValues("ingredients")){
            System.out.println("Ingredients: " + ingredientId);
            ingredients.add((Ingredient)(service.findPreferanceTagById(Long.parseLong(ingredientId))));
        }

        PriceEstimate estimate = service.getMinMaxPrice(ingredients);
        System.out.println(estimate.getMinPrice());
        request.setAttribute("priceestimate",estimate);
    }
}
