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
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Offer offer = (Offer)request.getAttribute("offer");
        container.addProperty("id",offer.getId());
        
        JsonObject jsonCook = new JsonObject();
        
        jsonCook.addProperty("id",offer.getCook().getId());
        jsonCook.addProperty("firstName",offer.getCook().getFirstName());
        jsonCook.addProperty("lastName",offer.getCook().getLastName());
        jsonCook.addProperty("rating",offer.getCook().getRating());
        jsonCook.addProperty("numberOfReviews", offer.getCook().getNumberOfReviews());
        jsonCook.addProperty("image", offer.getCook().getProfilePictureUrl());
        
        container.add("cook",jsonCook);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        if (offer.getPublicationDate()!=null){
            container.addProperty("publicationDate",df.format(offer.getPublicationDate()));
        }
        if (offer.getExpirationDate()!=null){
            container.addProperty("expirationDate",df.format(offer.getExpirationDate()));
        }
        container.addProperty("title",offer.getTitle());
        container.addProperty("price",offer.getPrice());
        container.addProperty("totalPortion",offer.getTotalPortions());
        container.addProperty("details",offer.getDetails());
        
        JsonArray jsonClassificationList = new JsonArray();
        List<PreferenceTag> classifications = offer.getClassifications();
        classifications.stream().map(classification -> {
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", classification.getId());
            jsonPref.addProperty("name", classification.getName());
            return jsonPref;
        }).forEachOrdered(jsonPref -> {
            jsonClassificationList.add(jsonPref);
        });
        container.add("classifications",jsonClassificationList);
        
        JsonArray jsonIngredientList = new JsonArray();
        List<Ingredient> ingredients = offer.getIngredients();
        ingredients.stream().map(ingredient -> {
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", ingredient.getId());
            jsonPref.addProperty("name", ingredient.getName());
            return jsonPref;
        }).forEachOrdered(jsonPref -> {
            jsonIngredientList.add(jsonPref);
        });
        container.add("ingredients",jsonIngredientList);
        
        container.addProperty("specifications", offer.getSpecifications());
        container.addProperty("address", offer.getAddress());
        container.addProperty("remainingPortions",offer.getRemainingPortions());
        container.addProperty("city", offer.getCity());
        container.addProperty("zipCode", offer.getZipCode());
        container.addProperty("distanceToUser", offer.getDistanceToUser());
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
    
}
