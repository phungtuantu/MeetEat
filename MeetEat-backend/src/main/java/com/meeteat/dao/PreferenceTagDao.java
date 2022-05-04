/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author gvnge
 */
public class PreferenceTagDao extends AbstractDao<PreferenceTag>{
    public PreferenceTagDao(){
        super(PreferenceTag.class);
    }
    public List<Cuisine> getCuisines() {
        
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Cuisine> query = em.createQuery("SELECT c from Cuisine c", Cuisine.class);
        return query.getResultList();
    }
    
    public List<Diet> getDiets() {
        
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Diet> query = em.createQuery("SELECT d from Diet d", Diet.class);
        return query.getResultList();
    }
    
    public List<Ingredient> getIngredients() {
        
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<Ingredient> query = em.createQuery("SELECT i from Ingredient i", Ingredient.class);
        return query.getResultList();
    }
}
