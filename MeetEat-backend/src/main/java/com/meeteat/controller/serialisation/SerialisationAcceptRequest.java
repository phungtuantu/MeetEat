/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gvnge
 */
public class SerialisationAcceptRequest extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject container = new JsonObject();
        Reservation reservation = (Reservation) request.getAttribute("reservation");
        if(reservation.getState() != ReservationState.RESERVATION){
            container.addProperty("requestAccepted", true);
        }else{
            container.addProperty("requestAccepted", false);
            if (reservation.getNbOfPortion()>reservation.getOffer().getRemainingPortions()){
                container.addProperty("error","not enough remaining portions");
            }
        }
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
    
}
