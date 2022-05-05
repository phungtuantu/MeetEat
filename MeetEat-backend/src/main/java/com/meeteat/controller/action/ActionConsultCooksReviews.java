/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Review;
import com.meeteat.model.User.Cook;
import com.meeteat.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gvnge
 */
public class ActionConsultCooksReviews extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
//        HttpSession session = request.getSession();
//        Long userId = (Long)session.getAttribute("userId");
        
        Long userId = Long.parseLong(request.getParameter("userId"));
        Cook cook = service.findCookByUserId(userId);
        List<Review> reviews = service.viewCooksReviews(cook.getId());
        request.setAttribute("reviews", reviews);
    }

}
