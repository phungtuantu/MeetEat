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
import com.meeteat.model.VerificationRequest.CookRequest;
import com.meeteat.model.VerificationRequest.RequestImage;
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
public class SerialisationCookRequests extends Serialisation{
 
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        List <CookRequest> cookRequests = (List <CookRequest>)request.getAttribute("cookRequests");
        JsonArray jsonCookRequestsList = new JsonArray();
        cookRequests.stream().map(res->{
            JsonObject jsonCookRequest = new JsonObject();
            jsonCookRequest.addProperty("id",res.getIdCookRequest());
            jsonCookRequest.addProperty("requestDate",df.format(res.getRequestDate()));
            JsonObject userContainer = new JsonObject();
            userContainer.addProperty("userId",res.getUser().getId());
            userContainer.addProperty("userPhoto",res.getUser().getProfilePhotoPath());
            userContainer.addProperty("userName",res.getUser().getFirstName());
            userContainer.addProperty("userAddress",res.getUser().getAddress());
            userContainer.addProperty("userCity",res.getUser().getCity());
            container.add("user", userContainer);
            
            return jsonCookRequest;
        }).forEachOrdered(jsonCookRequest -> {
            jsonCookRequestsList.add(jsonCookRequest);
        });
        container.add("cookRequests", jsonCookRequestsList);
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}
