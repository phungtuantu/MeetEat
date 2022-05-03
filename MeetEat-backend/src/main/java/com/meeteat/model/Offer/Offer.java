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
import java.util.List;
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
    private Date creationDate;
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
    private String details;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="offer_classifications")
    private List<PreferenceTag> classifications;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="offer_ingredients")
    private List<Ingredient> ingredients;
    private String specifications;
    @OneToMany(mappedBy="offer",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Reservation> reservations;
    @OneToMany(mappedBy="associatedOffer",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Message> messages;
    private String address;
    private String city;
    private String zipCode;
    private LatLng location;
    @Transient
    private transient double distanceToUser;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public String getOfferPhotoPath() {
        return offerPhotoPath;
    }

    public void setOfferPhotoPath(String offerPhotoPath) {
        this.offerPhotoPath = offerPhotoPath;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
        this.totalPortions-= reservation.getNbOfPortion();
    }
    
    public void publishOffer(){
        this.state = offerState.ONGOING;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public int getRemainingPortions() {
        return remainingPortions;
    }

    public void setRemainingPortions(int remainingPortions) {
        this.remainingPortions = remainingPortions;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.location = getLatLng(address + ", " + city);
    }
    
    public Offer() {
    }

    public Offer(Cook cook, Date creationDate, String title, double price, int totalPortions,
            String details, List<PreferenceTag> classifications, List<Ingredient> ingredients, String specifications,
            String address, String city, String zipCode) {
        this.cook = cook;
        this.creationDate = creationDate;
        this.title = title;
        this.price = price;
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
        this.offerPhotoPath = "";
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
