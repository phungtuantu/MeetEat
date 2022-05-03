/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Offer;

import com.meeteat.model.User.User;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author gvnge
 */
@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Reservation source;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private User reviewedUser;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private User reviewingUser;
    private int nbOfStars;
    private String comment;

    public Review(Reservation source, User reviewedUser, User reviewingUser, int nbOfStars, String comment) {
        this.source = source;
        this.reviewedUser = reviewedUser;
        this.reviewingUser = reviewingUser;
        this.nbOfStars = nbOfStars;
        this.comment = comment;
    }

    public Review() {
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Reservation getSource() {
        return source;
    }

    public User getReviewedUser() {
        return reviewedUser;
    }

    public User getReviewingUser() {
        return reviewingUser;
    }

    public int getNbOfStars() {
        return nbOfStars;
    }

    public String getComment() {
        return comment;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Offer.Review[ id=" + id + " ]";
    }

}
