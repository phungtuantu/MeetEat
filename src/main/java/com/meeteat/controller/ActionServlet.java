/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meeteat.controller;

import com.meeteat.controller.action.Action;
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
//            case "demanderSynthese": {
//                action = new ActionDemanderSynthese();
//                serialisation = new SyntheseSerialisation();
//                break;
//            }
        }
        
//        if (action!=null&&serialisation!=null){
//            action.executer(request);
//            serialisation.serialiser(request, response);
//        } else{
//            response.sendError(400, "Bad Request (pas d'Action ou de Serialisation pour traiter cette requete)");
//        }
        
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ActionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1> Contrôleur Servlet ActionServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
