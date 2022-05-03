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
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.PriorityQueue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yousr
 */
public class SerialisationUsers extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        List<User> users = (List<User>) request.getAttribute("users");
        JsonArray jsonUserList = new JsonArray();
        users.stream().map(user -> {
            JsonObject jsonUser = new JsonObject();
            jsonUser.addProperty("id", user.getId());
            jsonUser.addProperty("firstName", user.getFirstName());
            jsonUser.addProperty("lastName", user.getLastName());
            jsonUser.addProperty("mail", user.getMail());
            jsonUser.addProperty("address", user.getAddress());
            jsonUser.addProperty("city", user.getCity());
            jsonUser.addProperty("zipCode", user.getZipCode());
            jsonUser.addProperty("noTelephone", user.getNoTelephone());
            jsonUser.addProperty("photo", user.getProfilePhotoPath());
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
            jsonUser.add("preferences", jsonPreferenceList);

            return jsonUser;
        }).forEachOrdered(jsonUser -> {
            jsonUserList.add(jsonUser);
        });
        container.add("users", jsonUserList);
        try ( PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }
}
