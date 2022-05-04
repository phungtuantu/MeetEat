/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.controller.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johanbonnedahl
 */
public class SerialisationPublishOffer extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject container = new JsonObject();
        Offer offer = (Offer) request.getAttribute("offer");
        if(offer.getState() == Offer.offerState.ONGOING){
            container.addProperty("offerPublished", true);
        }else{
            container.addProperty("offerPublished", false);
            container.addProperty("error","Something went wrong in publishing the offer");
        }
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}