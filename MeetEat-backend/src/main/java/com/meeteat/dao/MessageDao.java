/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.dao;

import com.meeteat.model.Offer.Message;

/**
 *
 * @author gvnge
 */
public class MessageDao extends AbstractDao<Message>{
    public MessageDao(){
        super(Message.class);
    }
}
