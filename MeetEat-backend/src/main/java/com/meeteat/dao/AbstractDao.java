/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author johanbonnedahl
 */
public abstract class AbstractDao<T> {
    private final Class<T> entityType;
    
    public AbstractDao(Class<T> entityType){
        this.entityType = entityType;
    }
    
    public void create(T t) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.persist(t);
    }
    
    public void merge(T t){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.merge(t);
    }
    
    public T searchById(Long objectId) {
        EntityManager em = JpaTool.obtainPersistenceContext();
        return em.find(entityType, objectId); // renvoie null si l'identifiant n'existe pas
    }
    
    public void delete(T t){
        EntityManager em = JpaTool.obtainPersistenceContext();
        em.remove(t);
    }
    
    public List<T> getAll(){
        EntityManager em = JpaTool.obtainPersistenceContext();
        TypedQuery<T> query = (TypedQuery<T>) em.createQuery("SELECT e FROM "+entityType.getSimpleName()+" e",entityType);
        List<T> results = query.getResultList();
        return results;
    }
    
}
