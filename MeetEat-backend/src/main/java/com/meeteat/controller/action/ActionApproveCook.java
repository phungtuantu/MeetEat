/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.model.VerificationRequest.CookRequest;
import com.meeteat.service.Service;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author taha
 */
public class ActionApproveCook extends Action{
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();        
        Long cookRequestId = Long.parseLong(request.getParameter("cookRequestId"));
        CookRequest cookRequest = service.viewCookRequest(cookRequestId);
        User user = cookRequest.getUser();
        Date date = new Date();
        
        Cook cook = new Cook(user,date, 0, null, null);
        service.approveCook(cook);
        request.setAttribute("cook",cook);
    }
}
