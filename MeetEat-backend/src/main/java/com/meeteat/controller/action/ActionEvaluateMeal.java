/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.Review;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author taha
 */
public class ActionEvaluateMeal extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        int nbOfStars = Integer.parseInt(request.getParameter("nbOfStars"));
        String comment = request.getParameter("comment");
        Long sourceReservation_id = Long.parseLong(request.getParameter("reservation_Id"));
        Reservation reservation = service.findReservationById(sourceReservation_id);
        User reviewingUser = service.findUserById(userId);
        Offer offer = reservation.getOffer();
        User reviewedUser = offer.getCook().getUser();
        
        Review review = new Review(reservation, reviewedUser, reviewingUser, nbOfStars, comment);
        Long review_Id = service.createReview(review);
        request.setAttribute("review",review);
    }
}
