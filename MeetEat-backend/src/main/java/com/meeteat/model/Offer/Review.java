/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Offer;

import com.meeteat.model.User.User;
import java.io.Serializable;
import javax.persistence.Entity;
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
    @ManyToOne
    private Reservation source;

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
    @ManyToOne
    private User reviewedUser;
    @ManyToOne
    private User reviewingUser;
    private int nbOfStars;
    private String comment;

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
