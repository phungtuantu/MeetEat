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
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.Review;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
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
public class SerialisationOffer extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Offer offer = (Offer) request.getAttribute("offer");
        container.addProperty("id", offer.getId());

        JsonObject jsonCook = new JsonObject();

        jsonCook.addProperty("id", offer.getCook().getId());
        jsonCook.addProperty("firstName", offer.getCook().getFirstName());
        jsonCook.addProperty("lastName", offer.getCook().getLastName());
        jsonCook.addProperty("rating", offer.getCook().getRating());
        jsonCook.addProperty("numberOfReviews", offer.getCook().getNumberOfReviews());
        jsonCook.addProperty("image", offer.getCook().getUser().getProfilePhotoPath());

        container.add("cook", jsonCook);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        if (offer.getPublicationDate() != null) {
            container.addProperty("publicationDate", df.format(offer.getPublicationDate()));
        }
        if (offer.getExpirationDate() != null) {
            container.addProperty("expirationDate", df.format(offer.getExpirationDate()));
        }
        if (offer.getAvailableFrom() != null) {
            container.addProperty("availableFrom", df.format(offer.getAvailableFrom()));
        }
        container.addProperty("title", offer.getTitle());
        container.addProperty("price", offer.getPrice());
        container.addProperty("totalPortion", offer.getTotalPortions());
        container.addProperty("details", offer.getDetails());

        JsonArray jsonCuisineList = new JsonArray();
        JsonArray jsonDietList = new JsonArray();
        List<PreferenceTag> classifications = offer.getClassifications();
        classifications.forEach(classification -> {
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", classification.getId());
            jsonPref.addProperty("name", classification.getName());
            if (classification instanceof Cuisine) {
                jsonCuisineList.add(jsonPref);
            } else if (classification instanceof Diet) {
                jsonDietList.add(jsonPref);
            }
        });
        container.add("cuisines", jsonCuisineList);
        container.add("diets", jsonDietList);

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
        container.add("ingredients", jsonIngredientList);

        container.addProperty("specifications", offer.getSpecifications());
        container.addProperty("address", offer.getAddress());
        container.addProperty("remainingPortions", offer.getRemainingPortions());
        container.addProperty("city", offer.getCity());
        container.addProperty("zipcode", offer.getZipCode());
        container.addProperty("distanceToUser", offer.getDistanceToUser());
        
        JsonArray jsonReviewsList = new JsonArray();
        List<Reservation> reservations = offer.getReservations();
        reservations.stream().map(res -> {
            List<Review> reviews = res.getReviews();
            return reviews;
        }).forEachOrdered(reviews -> {
            for(Review review : reviews){
                JsonObject jsonReview = new JsonObject();
                jsonReview.addProperty("id",review.getId());
                jsonReview.addProperty("comment",review.getComment());
                jsonReview.addProperty("nbOfStars",review.getNbOfStars());
                jsonReview.addProperty("reviewedUser_firstName", review.getReviewedUser().getFirstName());
                jsonReview.addProperty("reviewedUser_lastName", review.getReviewedUser().getLastName());
                jsonReview.addProperty("reviewingUser_firstName", review.getReviewingUser().getFirstName());
                jsonReview.addProperty("reviewingUser_lastName", review.getReviewingUser().getLastName());
                jsonReview.addProperty("reviewingUser_photo", review.getReviewingUser().getProfilePhotoPath());
                jsonReview.addProperty("orderName", review.getSource().getOffer().getTitle());
                jsonReview.addProperty("reversationDate", df.format(review.getSource().getReservationDate()));
                jsonReviewsList.add(jsonReview);
            }
        });
        container.add("reviews",jsonReviewsList);
        
        container.addProperty("image", offer.getOfferPhotoPath());
        container.addProperty("state", offer.getState().name());
        container.addProperty("soldPortions", offer.getTotalPortions() - offer.getRemainingPortions());
        try ( PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }

}
