/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meeteat.controller;

import com.meeteat.controller.action.Action;
import com.meeteat.controller.action.ActionMakeOffer;
import com.meeteat.controller.serialisation.Serialisation;
import com.meeteat.controller.serialisation.SerialisationOffer;
import com.meeteat.dao.JpaTool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gvnge
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Action action = null;
        Serialisation serialisation = null;
        String todo = request.getParameter("todo");
        switch (todo){
            case "makeOffer" -> {
                action = new ActionMakeOffer();
                serialisation = new SerialisationOffer();
                break;
            }
        }
        
        if (action!=null&&serialisation!=null){
            action.executer(request);
            serialisation.serialiser(request, response);
        } else{
            response.sendError(400, "Bad Request (Action and/or Serialisation missing for request)");
        }
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaTool.init();
    }

    @Override
    public void destroy() {
        JpaTool.destroy();
        super.destroy();
    }


}
