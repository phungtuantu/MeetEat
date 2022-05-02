/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.User;

import com.google.maps.model.LatLng;
import com.meeteat.model.Preference.PreferenceTag;
import static com.meeteat.service.GeoNetApi.getLatLng;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 *
 * @author gvnge
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class User implements Serializable, Loginable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String mail;
    private String address;
    private String city;
    private String zipCode;
    private LatLng location;
    private String noTelephone;
    private String profilePhotoURL;
    @ManyToMany
    private List<PreferenceTag> preferences;
    private String password;


    public List<PreferenceTag> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<PreferenceTag> preferences) {
        this.preferences = preferences;
    }
    
    protected User(){
    }
    
    public User(String firstName, String lastName, String address, String city, String zipCode,
            String noTelephone, String mail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.noTelephone = noTelephone;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.location = getLatLng(address + ", " + city);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.location = getLatLng(address + ", " + city);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getNoTelephone() {
        return noTelephone;
    }

    public void setNoTelephone(String noTelephone) {
        this.noTelephone = noTelephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User "+firstName+" "+lastName+" (id = " + id + ")";
    }
}
