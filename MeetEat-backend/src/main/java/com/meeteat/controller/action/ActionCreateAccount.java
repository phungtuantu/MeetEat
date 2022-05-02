/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.google.maps.model.LatLng;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;  

/**
 *
 * @author Ihssane
 */
public class ActionCreateAccount extends Action{
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
        service.createAccount(user);
        service.encryptPassword(user, password);
        request.setAttribute("user",user);
    }
}
