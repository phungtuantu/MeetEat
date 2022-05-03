/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.VerificationRequest.CookRequest;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author taha
 */
public class ActionCookRequestDetails extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        Long cookRequestId = Long.parseLong(request.getParameter("cookRequestId"));
        CookRequest cookRequest = service.viewCookRequest(cookRequestId);
        request.setAttribute("cookRequest",cookRequest);
    }
}
