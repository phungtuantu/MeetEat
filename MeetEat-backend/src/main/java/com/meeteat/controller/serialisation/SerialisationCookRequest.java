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
public class SerialisationCookRequest extends Serialisation{
    
    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        CookRequest cookRequest = (CookRequest)request.getAttribute("cookRequest");
        container.addProperty("id",cookRequest.getIdCookRequest());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        if (cookRequest.getRequestDate()!=null){
            container.addProperty("requestDate",df.format(cookRequest.getRequestDate()));
        }
        container.addProperty("userId",cookRequest.getUser().getId());
        container.addProperty("id_imageId", cookRequest.getIdImage().getId());
        container.addProperty("id_imageReference", cookRequest.getIdImage().getImageReference());
        container.addProperty("certificationImageId", cookRequest.getCertificationImage().getId());
        container.addProperty("certificationImageReference", cookRequest.getCertificationImage().getImageReference());
        JsonArray jsonEquipmentImagesList = new JsonArray();
        List<RequestImage> equipmentImages = cookRequest.getEquipmentImages();
        equipmentImages.stream().map(image -> {
            JsonObject jsonEquipment = new JsonObject();
            jsonEquipment.addProperty("id", image.getId());
            jsonEquipment.addProperty("reference", image.getImageReference());
            return jsonEquipment;
        }).forEachOrdered(jsonEquipment -> {
            jsonEquipmentImagesList.add(jsonEquipment);
        });
        container.add("equipmentImages",jsonEquipmentImagesList);
        
        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container,out);
        }
    }
}
