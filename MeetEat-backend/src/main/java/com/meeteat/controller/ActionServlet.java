/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meeteat.controller;

import com.meeteat.controller.action.Action;
import com.meeteat.controller.action.ActionAuthentication;
import com.meeteat.controller.action.ActionConsultGuestsList;
import com.meeteat.controller.action.ActionConsultOffer;
import com.meeteat.controller.action.ActionConsultOffers;
import com.meeteat.controller.action.ActionCreateAccount;
import com.meeteat.controller.action.ActionCreateReservation;
import com.meeteat.controller.action.ActionMakeOffer;
import com.meeteat.controller.action.ActionModifyAccount;
import com.meeteat.controller.action.ActionSearchOffers;
import com.meeteat.controller.action.ActionSpecifyPreferences;
import com.meeteat.controller.action.ActionViewOffersHistory;
import com.meeteat.controller.serialisation.Serialisation;
import com.meeteat.controller.serialisation.SerialisationOffer;
import com.meeteat.controller.serialisation.SerialisationOffers;
import com.meeteat.controller.serialisation.SerialisationReservation;
import com.meeteat.controller.serialisation.SerialisationUser;
import com.meeteat.controller.serialisation.SerialisationUsers;
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
            
            case "consultOffer" -> {
                action = new ActionConsultOffer();
                serialisation = new SerialisationOffer();
                break;
            }
            case "consultCooksReviews" ->{
                break;
            }
            case "consultOffers" -> {
                action = new ActionConsultOffers();
                serialisation = new SerialisationOffers();
                break;
            }
            case "makeOffer" -> {
                action = new ActionMakeOffer();
                serialisation = new SerialisationOffer();
                break;
            }
            
            case "searchOffers" -> {
                action = new ActionSearchOffers();
                serialisation = new SerialisationOffers();
                break;
            }
            case "specifiyPreferences" -> {
                action = new ActionSpecifyPreferences();
                serialisation = new SerialisationUser();
                break;
            }
            case "createReservation" -> {
                action = new ActionCreateReservation();
                serialisation = new SerialisationReservation();
                break;
            }
            case "createAccount" -> {
                action = new ActionCreateAccount();
                serialisation = new SerialisationUser();
                break;
            }
            case "modifyAccount" -> {
                action = new ActionModifyAccount();
                serialisation = new SerialisationUser();
                break;
            }
            case "authenticate" -> {
                action = new ActionAuthentication();
                serialisation = new SerialisationUser();
                break;
            }
            case "viewOffersHistory"-> {
                action = new ActionViewOffersHistory();
                serialisation = new SerialisationOffers();
                break;
            }
            case "consultGuestsList"-> {
                action = new ActionConsultGuestsList();
                serialisation = new SerialisationUsers();
                break;
            }
            
        }
        
        if (action!=null&&serialisation!=null){
            action.executer(request);
            serialisation.serialise(request, response);
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
