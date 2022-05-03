/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Review;
import com.meeteat.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gvnge
 */
public class ActionConsultCooksReviews extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long cookId = Long.parseLong(request.getParameter("cookId"));
        List<Review> reviews = service.viewCooksReviews(cookId);
        request.setAttribute("reviews", reviews);
    }
    
}
