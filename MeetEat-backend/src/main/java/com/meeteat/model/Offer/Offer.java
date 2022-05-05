/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Offer;

import com.google.maps.model.LatLng;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import static com.meeteat.service.GeoNetApi.getLatLng;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author gvnge
 */
@Entity
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Cook cook;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date availableFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publicationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;
    private String title;
    private double price;
    private int totalPortions;
    private int remainingPortions;
    private String offerPhotoPath;
    public enum offerState {
        PENDING,
        ONGOING,
        SOLDOUT,
        UNAVAILABLE
    }
    private offerState state;
    private String details; //Free text for the cook, description of the offer
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="offer_classifications")
    private List<PreferenceTag> classifications; // Diet and cuisine of the offer, already on the db
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="offer_ingredients")
    private List<Ingredient> ingredients;
    private String specifications; //Additionnal cuisines and diet, not present on our DB
    @OneToMany(mappedBy="offer",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Reservation> reservations;
    @OneToMany(mappedBy="associatedOffer",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Message> messages;
    private String address;
    private String city;
    private String zipCode;
    private LatLng location;
    @Transient
    private double distanceToUser;

    public double getDistanceToUser() {
        return distanceToUser;
    }

    public void setDistanceToUser(double distanceToUser) {
        this.distanceToUser = distanceToUser;
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

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public String getOfferPhotoPath() {
        return offerPhotoPath;
    }

    public void setOfferPhotoPath(String offerPhotoPath) {
        this.offerPhotoPath = offerPhotoPath;
    }
    
    public void setCreationDate(Date creationDate) {
        this.availableFrom = creationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalPortions() {
        return totalPortions;
    }

    public void setTotalPortions(int totalPortions) {
        this.totalPortions = totalPortions;
    }

    public offerState getState() {
        return state;
    }

    public void setState(offerState state) {
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<PreferenceTag> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<PreferenceTag> classifications) {
        this.classifications = classifications;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
    
    public void validateReservation(Reservation reservation){
        if (reservations.contains(reservation)){
            this.remainingPortions -= reservation.getNbOfPortion();
            if(this.remainingPortions == 0){
                this.state = offerState.SOLDOUT;
            }
        }
    }
    
    public void publishOffer() {
        this.state = offerState.ONGOING;
        this.publicationDate = new Date();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public int getRemainingPortions() {
        return remainingPortions;
    }

    public void setRemainingPortions(int remainingPortions) {
        this.remainingPortions = remainingPortions;
        assert(remainingPortions >= 0);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getAddress() {
        return address;
    }
    
    public boolean expired(Date date){
        if(this.expirationDate.before(date)){
            this.state = offerState.UNAVAILABLE;
            return true;
        }
        return false;
    }

    public void setAddress(String address) {
        this.address = address;
        this.location = getLatLng(address + ", " + city);
    }
    
    public Offer() {
    }

    public Offer(Cook cook, Date availableFrom, String title, int totalPortions,
            String details, List<PreferenceTag> classifications, List<Ingredient> ingredients, String specifications,
            String address, String city, String zipCode, Date expirationDate, String offerPhotoPath) {
        assert(expirationDate.after(availableFrom));
        this.cook = cook;
        this.availableFrom = availableFrom;
        this.title = title;
        this.totalPortions = totalPortions;
        this.remainingPortions = totalPortions;
        this.details = details;
        this.specifications = specifications;
        this.state = offerState.PENDING;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.location = getLatLng(address + ", " + city);
        this.classifications = classifications;
        this.ingredients = ingredients;
        this.reservations = new LinkedList<>();
        this.expirationDate = expirationDate;
        this.offerPhotoPath = offerPhotoPath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offer other = (Offer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Offer.Offer[ id=" + id + " ]";
    }
    
}
