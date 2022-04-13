/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.User;

import com.meeteat.model.Preference.PreferenceTag;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

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
    private String noTelephone;
   @OneToMany(mappedBy="user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<PreferenceTag> preferences;
    
    protected User(){}
    
    public User(String firstName, String lastName, String address, String noTelephone, String mail, String motDePasse){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
        this.noTelephone = noTelephone;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public String getNoTelephone() {
        return noTelephone;
    }

    public void setNoTelephone(String noTelephone) {
        this.noTelephone = noTelephone;
    }
    
    @Override
    public String toString() {
        return "User "+firstName+" "+lastName+" (id = " + id + ")";
    }
}
