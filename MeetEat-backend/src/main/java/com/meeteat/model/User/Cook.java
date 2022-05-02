/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.User;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge
 */
@Entity
public class Cook implements Serializable{
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date verifiedDate;
    private int level;
    private String education;
    private String training;
    @Id
    @OneToOne
    private User user;
    private int numberOfReviews;
    private double rating;

    public Cook() {
    }
    
    public Cook(User user, Date verifiedDate, int level, String education, String training) {
        this.user = user;
        this.verifiedDate = verifiedDate;
        this.level = level;
        this.education = education;
        this.training = training;
        this.numberOfReviews = 0;
        this.rating = 0;
    }
    
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
    
    public Long getId() {
        return this.user.getId();
    }

    public void setId(Long id) {
        this.user.setId(id);
    }

    public String getFirstName() {
        return this.user.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    public String getMail() {
        return this.user.getMail();
    }

    public void setMail(String mail) {
        this.user.setMail(mail);
    }

    public String getAddress() {
        return this.user.getAddress();
    }

    public void setAddress(String address) {
        this.user.setAddress(address);
    }

    public String getNoTelephone() {
        return user.getNoTelephone();
    }

    public void setNoTelephone(String noTelephone) {
        this.user.setNoTelephone(noTelephone);
    }
    
    public String getProfilePictureUrl() {
        return user.getProfilePictureUrl();
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.user.setProfilePictureUrl(profilePictureUrl);
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public double getRating() {
        return rating;
    }

    public void updateRating(int reviewNote) {
        rating = (rating*numberOfReviews+reviewNote)/(numberOfReviews+1);
        numberOfReviews++;
    }
    
    @Override
    public String toString() {
        return "Cook "+user.getFirstName()+" "+user.getLastName()+" (id = " + user.getId() + ")";
    }
    
}
