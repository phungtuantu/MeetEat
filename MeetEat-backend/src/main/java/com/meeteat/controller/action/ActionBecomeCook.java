
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ihssane
 */
public class ActionBecomeCook extends Action{

//    @Override
//    public void executer(HttpServletRequest request) {
//        Service service = new Service();
//        HttpSession session = request.getSession();
////        Long userId = (Long)session.getAttribute("userId");
//        Long userId = Long.parseLong(request.getParameter("userId"));
//        User user = service.findUserById(userId);
//        
//        Date requestDate = new Date();
//        CookRequest cookRequest = new CookRequest(requestDate, user, equipmentImages, id_Image, certificationImage);
//        
//        service.becomeCook(cookRequest);
//        request.setAttribute("cookRequest",cookRequest);
//    }
    
}
