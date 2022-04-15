/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meeteat.controller;

import com.meeteat.controller.action.Action;
import com.meeteat.controller.action.ActionConsultOffer;
import com.meeteat.controller.action.ActionMakeOffer;
import com.meeteat.controller.serialisation.Serialisation;
import com.meeteat.dao.JpaTool;
import java.io.IOException;
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
            case "makeOffer" -> {
                action = new ActionMakeOffer();
                break;
            }
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
