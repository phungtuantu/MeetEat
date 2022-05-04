/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.controller.action;

import com.meeteat.model.Preference.Ingredient;
import com.meeteat.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yousr
 */
public class ActionViewIngredients extends Action{
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service();
        List<Ingredient> ingredients=service.viewIngredients();
        request.setAttribute("preferenceTags",ingredients);
    }
}
