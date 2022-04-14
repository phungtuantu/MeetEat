/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Preference.Ingredient;

/**
 *
 * @author ithan
 */
public class IngredientDao extends AbstractDao<Ingredient>{
    public IngredientDao() {
        super(Ingredient.class);
    }
}
