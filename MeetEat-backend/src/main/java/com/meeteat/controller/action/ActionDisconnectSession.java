/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gvnge
 */
public class ActionDisconnectSession extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session!=null && session.getAttribute("userId")!=null){
            session.invalidate();
            request.setAttribute("disconnected", true);
        } else{
            request.setAttribute("disconnected", false);
        }
    }
    
}
