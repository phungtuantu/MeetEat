/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.VerificationRequest;

import com.meeteat.model.User.User;
import java.io.Serializable;
import java.util.Date;
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
 * @author Ihssane
 */
@Entity
public class CookRequest implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCookRequest;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date requestDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="CookRequest",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<RequestImage> requestImages;

    public CookRequest(Date requestDate, User user, List<RequestImage> requestImages) {
        this.requestDate = requestDate;
        this.user = user;
        this.requestImages = requestImages;
    }

    public CookRequest() {
    }
    
    public Long getIdCookRequest() {
        return idCookRequest;
    }

    public void setIdCookRequest(Long idCookRequest) {
        this.idCookRequest = idCookRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public List<RequestImage> getRequestImages() {
        return requestImages;
    }

    public void setRequestImages(List<RequestImage> requestImages) {
        this.requestImages = requestImages;
    }

}
