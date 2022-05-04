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
 * @author gvnge
 */
public class SerialisationAuthenticate extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean connection =(boolean)request.getAttribute("connection");
        JsonObject container = new JsonObject();
        
        if (connection){
            User user =(User)request.getAttribute("user");
            JsonObject userJson = new JsonObject();
            userJson.addProperty("id",user.getId());
            userJson.addProperty("firstName",user.getFirstName());
            userJson.addProperty("lastName",user.getLastName());
            userJson.addProperty("mail",user.getMail());
            userJson.addProperty("address",user.getAddress());
            userJson.addProperty("city",user.getCity());
            userJson.addProperty("zipCode",user.getZipCode());
            userJson.addProperty("noTelephone",user.getNoTelephone());
            userJson.addProperty("photo",user.getProfilePhotoPath());
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
            userJson.add("preferences",jsonPreferenceList);
            container.add("user",userJson);
            container.addProperty("connection", true);
        } else{
            container.addProperty("connection", false);
        }
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}
