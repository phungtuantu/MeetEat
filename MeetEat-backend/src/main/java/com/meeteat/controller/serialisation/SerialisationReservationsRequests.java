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
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
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
 * @author taha
 */
public class SerialisationReservationsRequests extends Serialisation{
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        List <Reservation> reservations = (List <Reservation>)request.getAttribute("reservations");
        JsonArray jsonReservationList = new JsonArray();
        reservations.stream().map(res->{
            JsonObject jsonReservation = new JsonObject();
            if(res.getState() != ReservationState.REQUEST){
                jsonReservation.addProperty("id",res.getId());
                jsonReservation.addProperty("isRequest","This reservation is not a request");
                return jsonReservation;
            }else{
                jsonReservation.addProperty("state", "request");
                jsonReservation.addProperty("id",res.getId());
                jsonReservation.addProperty("nbOfPortion", res.getNbOfPortion());
                jsonReservation.addProperty("reservationDate",df.format(res.getReservationDate()));
                jsonReservation.addProperty("totalPrice",res.getOffer().getPrice() * res.getNbOfPortion());
                jsonReservation.addProperty("offerTitle",res.getOffer().getTitle());
                jsonReservation.addProperty("offerDetails",res.getOffer().getDetails());
                jsonReservation.addProperty("offerImage",res.getOffer().getOfferPhotoPath());
                jsonReservation.addProperty("portionPrice",res.getOffer().getPrice());
                jsonReservation.addProperty("offerPortionsLeft",res.getOffer().getRemainingPortions());
                
                JsonArray jsonClassificationList = new JsonArray();
                List<PreferenceTag> classifications = res.getOffer().getClassifications();
                classifications.stream().map(classification -> {
                    JsonObject jsonPref = new JsonObject();
                    jsonPref.addProperty("id", classification.getId());
                    jsonPref.addProperty("name", classification.getName());
                    return jsonPref;
                }).forEachOrdered(jsonPref -> {
                    jsonClassificationList.add(jsonPref);
                });
                jsonReservation.add("classifications",jsonClassificationList);
                
                JsonObject customer = new JsonObject();
                customer.addProperty("customerFirstName",res.getCustomer().getFirstName());
                customer.addProperty("customerImage", res.getCustomer().getProfilePhotoPath());
                customer.addProperty("customerLastImage", res.getCustomer().getLastName());
                
                return jsonReservation;
            }
        }).forEachOrdered(jsonReservation -> {
            jsonReservationList.add(jsonReservation);
        });
        container.add("reservations", jsonReservationList);
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
    
}
