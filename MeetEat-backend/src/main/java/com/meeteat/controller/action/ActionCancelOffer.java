/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author taha
 */
public class ActionCancelOffer extends Action {
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long offerId = Long.parseLong(request.getParameter("offerId"));
        boolean canceled = service.cancelOffer(offerId);
        request.setAttribute("canceled", canceled);
    }
    
}
