/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gvnge
 */
public class SerialisationOffer extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Offer offer = (Offer)request.getAttribute("offer");
        container.addProperty("id",offer.getId());
        container.addProperty("cookId",offer.getCook().getId());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        container.addProperty("publicationDate",df.format(offer.getPublicationDate()));
        container.addProperty("expirationDate",df.format(offer.getExpirationDate()));
        container.addProperty("title",offer.getTitle());
        container.addProperty("price",offer.getPrice());
        container.addProperty("totalPortion",offer.getTotalPortions());
        container.addProperty("details",offer.getDetails());
        
        JsonArray jsonClassificationList = new JsonArray();
        List<PreferenceTag> classifications = offer.getClassifications();
        for (PreferenceTag classification : classifications){
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", classification.getId());
            jsonPref.addProperty("name", classification.getName());
            jsonClassificationList.add(jsonPref);
        }
        container.add("classifications",jsonClassificationList);
        
        JsonArray jsonIngredientList = new JsonArray();
        List<Ingredient> ingredients = offer.getIngredients();
        for (PreferenceTag ingredient : ingredients){
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", ingredient.getId());
            jsonPref.addProperty("name", ingredient.getName());
            jsonIngredientList.add(jsonPref);
        }
        container.add("ingredients",jsonIngredientList);
        
        container.addProperty("specifications", offer.getSpecifications());
        container.addProperty("address", offer.getAddress());
        container.addProperty("remainingPortions",offer.getRemainingPortions());
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container,out);
        out.close();
    }
    
}
