/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.serialisation;

import com.google.gson.JsonObject;
import com.meeteat.model.Offer.Message;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ithan
 */
public class SerialisationMessage extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Message message = (Message) request.getAttribute("message");
        JsonObject container = new JsonObject(message);
        container.addProperty("message", message);
        container.addProperty("id",user.getId());
        container.addProperty("firstName",user.getFirstName());
        container.addProperty("lastName",user.getLastName());
        container.addProperty("mail",user.getMail());
        container.addProperty("address",user.getAddress());
        container.addProperty("city",user.getCity());
        container.addProperty("zipCode",user.getZipCode());
        container.addProperty("noTelephone",user.getNoTelephone());
        JsonArray jsonPreferenceList = new JsonArray();
        List<PreferenceTag> preferences = user.getPreferences();
        preferences.stream().map(preference -> {
            JsonObject jsonPref = new JsonObject();
            jsonPref.addProperty("id", preference.getId());
            jsonPref.addProperty("name", preference.getName());
            return jsonPref;
        }).forEachOrdered(jsonPref -> {
            jsonPreferenceList.add(jsonPref);
        });
        container.add("preferences",jsonPreferenceList);
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
    
}
