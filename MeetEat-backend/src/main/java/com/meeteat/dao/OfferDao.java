/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Offer;

/**
 *
 * @author gvnge
 */
public class OfferDao extends AbstractDao<Offer>{
    public OfferDao(){
        super(Offer.class);
    }  
}
