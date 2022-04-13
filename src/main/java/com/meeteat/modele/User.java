/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.modele;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author gvnge
 */
@Entity
public class User implements Serializable, Loginable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String nom;
//    private String prenom;
//    @Column(unique = true)
//    private String mail;
//    private String motDePasse;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date dob;
//    private String adresse;
//    private String noTelephone;
//    private String genre;
//    private boolean disponible;
//    @OneToMany(mappedBy="employe",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
//    private LinkedList<Consultation> histoire;
//    private int count;
//    
//    protected Employe(){}
//    
//    public Employe(String nom, String prenom, String genre, String adresse, String noTelephone, String mail, String motDePasse, String dob){
//        this.nom = nom;
//        this.prenom = prenom;
//        this.mail = mail;
//        this.motDePasse = motDePasse;
//        this.adresse = adresse;
//        this.genre = genre;
//        this.noTelephone = noTelephone;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            this.dob=df.parse(dob);
//        } catch (ParseException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        histoire = new LinkedList<>();
//        count = histoire.size();
//        disponible = true;
//    }
//    
//    public Long getId() {
//        return id;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }
//
//    public String getMotDePasse() {
//        return motDePasse;
//    }
//
//    public void setMotDePasse(String motDePasse) {
//        this.motDePasse = motDePasse;
//    }
//    
//    public String getAdresse() {
//        return adresse;
//    }
//
//    public void setAdresse(String adresse) {
//        this.adresse = adresse;
//    }
//    
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//    
//    public String getNoTelephone() {
//        return noTelephone;
//    }
//
//    public void setNoTelephone(String noTelephone) {
//        this.noTelephone = noTelephone;
//    }
//    
//    public Date getDOB(){
//        return dob;
//    }
//
//    public boolean isDisponible() {
//        return disponible;
//    }
//
//    public void setDisponible(boolean disponible) {
//        this.disponible = disponible;
//    }
//    
//    public void setDOB(String dob){
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            this.dob=df.parse(dob);
//        } catch (ParseException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public LinkedList<Consultation> getHistoire (){
//        return histoire;
//    }
//    
//    public void addConsultation (Consultation consultation){
//        histoire.add(consultation);
//        count = histoire.size();
//    }
//    
//    public void removeConsultation (){
//        histoire.pop();
//        count = histoire.size();
//    }
//    
//    public int getCount(){
//        return count;
//    }
//    
//    public Consultation getCurrentConsultation (){
//        Consultation resultat = null;
//        if (!histoire.isEmpty() && histoire.getLast().getFin()==null){     //ne rendre une consultation que s'il y a une consultation qui n'est pas encore terminée
//            resultat=histoire.getLast();
//        }
//        return resultat;
//    }
//    
//    @Override
//    public String toString() {
//        return "Employe "+prenom+" "+nom+" (id = " + id + "), genre: "+genre+", a reçu "+histoire.size()+" consultations";
//    }
    
}
