/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Offer;

import com.meeteat.model.User.User;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge, johanbonnedahl
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reservationDate;
    @Enumerated(EnumType.STRING)
    private ReservationState state;
    private int nbOfPortion;
    @ManyToOne(cascade=CascadeType.MERGE)
    private Offer offer;
    @ManyToOne(cascade=CascadeType.MERGE)
    private User customer;
    @OneToMany(mappedBy="source",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Reservation() {
    }

    public Reservation(Date reservationDate, ReservationState state, int nbOfPortion, Offer offer, User customer) {
        this.reservationDate = reservationDate;
        this.state = state;
        this.nbOfPortion = nbOfPortion;
        this.offer = offer;
        this.customer = customer;
        this.reviews = new LinkedList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void addReview(Review review){
        if(!reviews.contains(review)){
            this.reviews.add(review);
        }
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public int getNbOfPortion() {
        return nbOfPortion;
    }

    public void setNbOfPortion(int nbOfPortion) {
        this.nbOfPortion = nbOfPortion;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCustomer() {
        return customer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Reservation[ id=" + id + " ]";
    }
    
}
