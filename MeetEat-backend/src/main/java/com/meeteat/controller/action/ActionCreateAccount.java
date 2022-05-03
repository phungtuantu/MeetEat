/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ihssane
 */

public class ActionCreateAccount extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mail = request.getParameter("mail");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipCode");
        String noTelephone = request.getParameter("noTelephone");
        String password = request.getParameter("password");
        User user = new User (firstName,lastName, address, city, zipCode, noTelephone,mail);
        service.createAccount(user, password);
        request.setAttribute("user",user);
    }
}
