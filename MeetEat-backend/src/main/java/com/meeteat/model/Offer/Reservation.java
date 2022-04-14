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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reservationDate;
    
    private ReservationState state;
    private int nbOfPortion;
    @ManyToOne
    private Offer offer;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="source",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Reservation(Date reservationDate, ReservationState state, int nbOfPortion, Offer offer, User user) {
        this.reservationDate = reservationDate;
        this.state = state;
        this.nbOfPortion = nbOfPortion;
        this.offer = offer;
        this.user = user;
        this.reviews = new LinkedList();
    }
    
    public Reservation(){
        
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void addReview(Review review){
        for(Review r: reviews){
            if(r.getId().equals(review.getId())){
                reviews.remove(r);
                reviews.add(review);
                return;
            }
        }
        this.reviews.add(review);
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

    public Offer getOffer() {
        return offer;
    }


    public User getUser() {
        return user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Reservation[ id=" + id + " ]";
    }
    
}
