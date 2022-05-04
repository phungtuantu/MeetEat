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
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yousr
 */
public class SerialisationUser extends Serialisation{
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        User user =(User)request.getAttribute("user");
        container.addProperty("id",user.getId());
        container.addProperty("firstName",user.getFirstName());
        container.addProperty("lastName",user.getLastName());
        container.addProperty("mail",user.getMail());
        container.addProperty("address",user.getAddress());
        container.addProperty("city",user.getCity());
        container.addProperty("zipCode",user.getZipCode());
        container.addProperty("noTelephone",user.getNoTelephone());
        container.addProperty("photo",user.getProfilePhotoPath());
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
