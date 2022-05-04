
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.User.User;
import com.meeteat.model.VerificationRequest.CookRequest;
import com.meeteat.model.VerificationRequest.RequestImage;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ihssane
 */
public class ActionBecomeCook extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession();
//        Long userId = (Long)session.getAttribute("userId");
        Long userId = Long.parseLong(request.getParameter("userId"));
        User user = service.findUserById(userId);
        String id_ImagePath = request.getParameter("idImagePath");
        String certificationImagePath = request.getParameter("certificationImagePath");
        RequestImage id_Image = new RequestImage(id_ImagePath);    
        RequestImage certificationImage = new RequestImage(certificationImagePath); 
        service.createRequestImage(id_Image);
        service.createRequestImage(certificationImage);
        List<RequestImage> equipmentImages = new LinkedList<>();
        for (String equipmentImagePath : request.getParameterValues("equipmentImagePath")){
            //equipmentImages.add((service.createRequestImage(Long.parseLong(equipmentImageId))));
            RequestImage equipmentImage = new RequestImage(equipmentImagePath);
            equipmentImages.add(equipmentImage);
            service.createRequestImage(equipmentImage);
        }
        
        Date requestDate = new Date();
        CookRequest cookRequest = new CookRequest(requestDate, user, equipmentImages, id_Image, certificationImage);
        
        service.becomeCook(cookRequest);
        System.out.println(cookRequest.getIdCookRequest());
        request.setAttribute("cookRequest",cookRequest);
    }
    
}
