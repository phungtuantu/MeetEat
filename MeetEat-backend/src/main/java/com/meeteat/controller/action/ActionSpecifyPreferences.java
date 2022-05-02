/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yousr
 */
//User specifyPreferences(List<PreferenceTag> listPref, User user){
public class ActionSpecifyPreferences extends Action{
   @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        User user = service.findUserById(userId);
        List<PreferenceTag> requestPreferences = new LinkedList<>();
        for (String preferenceId : request.getParameterValues("requestPreferences")){
            requestPreferences.add((service.findPreferanceTagById(Long.parseLong(preferenceId))));
        }
        user = service.specifyPreferences(requestPreferences, user);
        System.out.println(user);
    } 
}
