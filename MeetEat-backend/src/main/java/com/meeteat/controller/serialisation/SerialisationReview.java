/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.meeteat.model.Offer.Review;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taha
 */
public class SerialisationReview extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Review review = (Review)request.getAttribute("review");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        container.addProperty("id",review.getId());
        
        container.addProperty("comment",review.getComment());
        
        container.addProperty("nbOfStars",review.getNbOfStars());
        container.addProperty("reviewedUser_firstName", review.getReviewedUser().getFirstName());
        container.addProperty("reviewedUser_lastName", review.getReviewedUser().getLastName());
        container.addProperty("reviewingUser_firstName", review.getReviewingUser().getFirstName());
        container.addProperty("reviewingUser_lastName", review.getReviewingUser().getLastName());
        container.addProperty("reviewingUser_photo", review.getReviewingUser().getProfilePhotoPath());
        container.addProperty("orderName", review.getSource().getOffer().getTitle());
        container.addProperty("reversationDate", df.format(review.getSource().getReservationDate()));
        container.addProperty("evaluatedByCustomer", review.getSource().isEvaluatedByCustomer());
        container.addProperty("evaluatedByCook", review.getSource().isEvaluatedByCook());
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}
