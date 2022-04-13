/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service.VerificationRequest;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Ihssane
 */
public class CookRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCookRequest;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date requestDate; 
    @OneToMany(mappedBy="CookRequest",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<RequestImage> requestImages;

    public Long getIdCookRequest() {
        return idCookRequest;
    }

    public void setIdCookRequest(Long idCookRequest) {
        this.idCookRequest = idCookRequest;
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
