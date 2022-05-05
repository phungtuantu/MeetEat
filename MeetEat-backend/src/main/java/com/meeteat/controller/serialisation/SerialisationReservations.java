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
public class SerialisationReservations extends Serialisation{
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        List <Reservation> reservations = (List <Reservation>)request.getAttribute("reservations");
        JsonArray jsonReservationList = new JsonArray();
        reservations.stream().map(res->{
            JsonObject jsonReservation = new JsonObject();
            jsonReservation.addProperty("id",res.getId());
            jsonReservation.addProperty("nbOfPortion", res.getNbOfPortion());
            jsonReservation.addProperty("reservationDate",df.format(res.getReservationDate()));
            jsonReservation.addProperty("customerName",res.getCustomer().getFirstName());
            jsonReservation.addProperty("price",res.getOffer().getPrice() * res.getNbOfPortion());
            
            JsonObject offer = new JsonObject();
            offer.addProperty("offerId",res.getOffer().getId());
            offer.addProperty("offerTitle",res.getOffer().getTitle());
            offer.addProperty("offerDetails",res.getOffer().getDetails());
            offer.addProperty("offerImage",res.getOffer().getOfferPhotoPath());
            offer.addProperty("noTelCustomer",res.getCustomer().getNoTelephone());
            jsonReservation.add("offer", offer);
            if (null != res.getState())switch (res.getState()) {
                case REQUEST -> jsonReservation.addProperty("state", "request");
                case RESERVATION -> jsonReservation.addProperty("state", "reservation");
                case REJECTED -> jsonReservation.addProperty("state", "rejected");
                case CANCELLED -> jsonReservation.addProperty("state", "cancelled");
                case PURCHASEDMEAL -> jsonReservation.addProperty("state", "purchasedMeal");
                default -> {
                }
            }
            jsonReservation.addProperty("evaluatedByCustomer",res.isEvaluatedByCustomer());
            jsonReservation.addProperty("evaluatedByCook",res.isEvaluatedByCook());
            
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
            
            JsonObject cook = new JsonObject();
            cook.addProperty("cookId",res.getOffer().getCook().getId());
            cook.addProperty("cookFirstName", res.getOffer().getCook().getFirstName());
            cook.addProperty("cookLastName", res.getOffer().getCook().getLastName());
            cook.addProperty("cookPhoto", res.getOffer().getCook().getUser().getProfilePhotoPath());

            jsonReservation.add("cook",cook);
            
            return jsonReservation;
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
