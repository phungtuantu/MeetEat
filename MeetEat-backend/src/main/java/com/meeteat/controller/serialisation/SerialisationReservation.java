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
 * @author yousr
 */
public class SerialisationReservation extends Serialisation{

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Reservation reservation = (Reservation)request.getAttribute("reservation");
        if (reservation==null){
            switch ((int)request.getAttribute("errorCode")){
                    case 1 -> {
                        container.addProperty("errorCode",1);
                        break;
                    }
                    case 2 -> {
                        container.addProperty("errorCode",2);
                        break;
                    }
            }
            container.addProperty("foundReservation",false);
        } else{
            container.addProperty("foundReservation",true);
            container.addProperty("id",reservation.getId());
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            if (reservation.getReservationDate()!=null){
                container.addProperty("reservationDate",df.format(reservation.getReservationDate()));
            }
            container.addProperty("nbOfPortion",reservation.getNbOfPortion());
            container.addProperty("customerId",reservation.getCustomer().getId());
            JsonArray jsonReviewList = new JsonArray();
            List<Review> reviews = reservation.getReviews();
            reviews.stream().map(review -> {
                JsonObject jsonPref = new JsonObject();
                jsonPref.addProperty("id", review.getId());
                jsonPref.addProperty("ReviewingUserId", review.getReviewingUser().getId());
                jsonPref.addProperty("nbOfStars", review.getNbOfStars());
                jsonPref.addProperty("comment", review.getComment());
                return jsonPref;
            }).forEachOrdered(jsonPref -> {
                jsonReviewList.add(jsonPref);
            });
            container.add("reviews",jsonReviewList);
            container.addProperty("state",reservation.getState().name());

            JsonObject containerOffer = new JsonObject();
            Offer offer = reservation.getOffer();
            containerOffer.addProperty("id",offer.getId());
            containerOffer.addProperty("cookId",offer.getCook().getId());
            if (offer.getPublicationDate()!=null){
                containerOffer.addProperty("publicationDate",df.format(offer.getPublicationDate()));
            }
            if (offer.getExpirationDate()!=null){
                containerOffer.addProperty("expirationDate",df.format(offer.getExpirationDate()));
            }
            containerOffer.addProperty("title",offer.getTitle());
            containerOffer.addProperty("price",offer.getPrice());
            containerOffer.addProperty("totalPortion",offer.getTotalPortions());
            containerOffer.addProperty("details",offer.getDetails());

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
            containerOffer.add("classifications",jsonClassificationList);

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
            containerOffer.add("ingredients",jsonIngredientList);

            containerOffer.addProperty("offerSpecifications", offer.getSpecifications());
            containerOffer.addProperty("offerPhoto", offer.getOfferPhotoPath());
            containerOffer.addProperty("offerAddress", offer.getAddress());
            containerOffer.addProperty("remainingPortions",offer.getRemainingPortions());
            containerOffer.addProperty("city", offer.getCity());
            containerOffer.addProperty("zipCode", offer.getZipCode());

            container.add("offer",containerOffer);
            JsonObject cook = new JsonObject();
            cook.addProperty("cookId",offer.getCook().getId());
            cook.addProperty("cookFirstName", offer.getCook().getFirstName());
            cook.addProperty("cookLastName", offer.getCook().getLastName());
            cook.addProperty("cookPhoto", offer.getCook().getUser().getProfilePhotoPath());

            container.add("cook",cook);
        }
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}
