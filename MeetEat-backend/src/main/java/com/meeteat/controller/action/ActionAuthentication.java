/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ihssane
 */
public class ActionAuthentication extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        
        Service service = new Service(); 
        String mail = request.getParameter("mail");    
        String password = request.getParameter("password");
        User user = service.authenticate(mail, password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            request.setAttribute("user", user);
            request.setAttribute("connection", true);
        }
        else{
            request.setAttribute("connection", false);
        }
    }
}
