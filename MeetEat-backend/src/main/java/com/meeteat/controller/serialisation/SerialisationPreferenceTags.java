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
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.meeteat.model.Preference.PreferenceTag;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author yousr
 */
public class SerialisationPreferenceTags extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        List<PreferenceTag> preferenceTags = (List<PreferenceTag>) request.getAttribute("preferenceTags");
        JsonArray jsonPreferenceTagList = new JsonArray();
        preferenceTags.stream().map(preferenceTag -> {
            JsonObject jsonPreferenceTag = new JsonObject();
            jsonPreferenceTag.addProperty("id", preferenceTag.getId());
            jsonPreferenceTag.addProperty("name", preferenceTag.getName());
           
            return jsonPreferenceTag;
        }).forEachOrdered(jsonPreferenceTag -> {
            jsonPreferenceTagList.add(jsonPreferenceTag);
        });
        container.add("preferenceTags", jsonPreferenceTagList);
        try ( PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }
}