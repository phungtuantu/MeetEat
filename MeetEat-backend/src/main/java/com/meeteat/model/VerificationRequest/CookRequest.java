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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name="equipment_images")
    private List<RequestImage> equipmentImages;
    @OneToOne
    private RequestImage idImage;
    @OneToOne
    private RequestImage certificationImage;

    public CookRequest(Date requestDate, User user, List<RequestImage> equipmentImages, RequestImage idImage, RequestImage certificationImage) {
        this.requestDate = requestDate;
        this.user = user;
        this.equipmentImages = equipmentImages;
        this.idImage = idImage;
        this.certificationImage = certificationImage;
    }

    public CookRequest(Date requestDate, User user, List<RequestImage> equipmentImages, RequestImage idImage) {
        this.requestDate = requestDate;
        this.user = user;
        this.equipmentImages = equipmentImages;
        this.idImage = idImage;
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

    public List<RequestImage> getEquipmentImages() {
        return equipmentImages;
    }

    public void setEquipmentImages(List<RequestImage> equipmentImages) {
        this.equipmentImages = equipmentImages;
    }

    public RequestImage getIdImage() {
        return idImage;
    }

    public void setIdImage(RequestImage idImage) {
        this.idImage = idImage;
    }

    public RequestImage getCertificationImage() {
        return certificationImage;
    }

    public void setCertificationImage(RequestImage certificationImage) {
        this.certificationImage = certificationImage;
    }


}
