/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Offer;

import com.meeteat.model.User.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge
 */
@Entity
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @ManyToOne
    private Offer associatedOffer;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sentDate;
    
    public Message(){}

    public Message(String content, User sender, User receiver, Offer associatedOffer, Date sentDate) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.associatedOffer = associatedOffer;
        this.sentDate = sentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return "com.meeteat.model.Offer.Message[ id=" + id + " ]";
    }
    
}
