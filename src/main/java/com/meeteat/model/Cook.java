/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge
 */
@Entity
public class Cook extends User implements Serializable{
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date verifiedDate;
    private int level;
    private String education;
    private String training;

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }
    
    @Override
    public String toString() {
        return "Cook "+getFirstName()+" "+getLastName()+" (id = " + getId() + ")";
    }
    
}
