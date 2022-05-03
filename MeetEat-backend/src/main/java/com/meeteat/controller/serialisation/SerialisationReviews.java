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
import com.meeteat.model.Offer.Review;
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
public class SerialisationReviews extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject container = new JsonObject();
        List<Review> reviews = (List<Review>)request.getAttribute("reviews");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        JsonArray jsonReviewList = new JsonArray();
        reviews.stream().map(review->{
            JsonObject jsonReview = new JsonObject();
            
            jsonReview.addProperty("comment",review.getComment());
        
            jsonReview.addProperty("nbOfStars",review.getNbOfStars());
            jsonReview.addProperty("reviewedUser_firstName", review.getReviewedUser().getFirstName());
            jsonReview.addProperty("reviewedUser_lastName", review.getReviewedUser().getLastName());
            jsonReview.addProperty("reviewingUser_firstName", review.getReviewingUser().getFirstName());
            jsonReview.addProperty("reviewingUser_lastName", review.getReviewingUser().getLastName());
            jsonReview.addProperty("reviewingUser_photo", review.getReviewingUser().getProfilePhotoPath());
            jsonReview.addProperty("orderName", review.getSource().getOffer().getTitle());
            jsonReview.addProperty("reversationDate", df.format(review.getSource().getReservationDate()));
            
            return jsonReview;
        }).forEachOrdered(jsonReview -> {
            jsonReviewList.add(jsonReview);
        });
        
        container.add("reviews",jsonReviewList);
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
    
}
