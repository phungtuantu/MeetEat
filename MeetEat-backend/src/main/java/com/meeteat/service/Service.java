/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.LatLng;
import com.meeteat.dao.CookDao;
import com.meeteat.dao.CookRequestDao;
import com.meeteat.dao.JpaTool;
import com.meeteat.dao.MessageDao;
import com.meeteat.dao.OfferDao;
import com.meeteat.dao.PreferenceTagDao;
import com.meeteat.dao.RequestImageDao;
import com.meeteat.dao.ReservationDao;
import com.meeteat.dao.ReviewDao;
import com.meeteat.dao.UserDao;
import com.meeteat.model.Offer.Message;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.PriceEstimate;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.Offer.Review;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.model.VerificationRequest.CookRequest;
import com.meeteat.model.VerificationRequest.RequestImage;
import static com.meeteat.service.GeoNetApi.getLatLng;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Math;

/**
 *
 * @author gvnge
 */
public class Service {

    protected UserDao userDao = new UserDao();
    protected CookDao cookDao = new CookDao();
    protected OfferDao offerDao = new OfferDao();
    protected PreferenceTagDao preferenceTagDao = new PreferenceTagDao();
    protected ReservationDao reservationDao = new ReservationDao();
    protected ReviewDao reviewDao = new ReviewDao();
    protected MessageDao messageDao = new MessageDao();
    protected CookRequestDao cookRequestDao = new CookRequestDao();
    protected RequestImageDao requestImageDao = new RequestImageDao();
    public static String spoonacularKey = "f6c1e678f19446c7bd67d810232c484d";

    public Long createPreferenceTag(PreferenceTag preferenceTag) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            preferenceTagDao.create(preferenceTag);
            JpaTool.validateTransaction();
            result = preferenceTag.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createPreferenceTag", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Long createAccount(User user, String password) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            String encryptedPassword = this.encryptPassword(password);
            user.setPassword(encryptedPassword);
            JpaTool.openTransaction();
            userDao.create(user);
            JpaTool.validateTransaction();
            result = user.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createAccount", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public User modifyAccount(Long userId, String firstName, String lastName, String address, String city, String zipCode,
            String noTelephone,Boolean passwordUpdated, String password, String profilePhotoPath) {
        
        JpaTool.createPersistenceContext();
        User user = userDao.searchById(userId);
        User result = null;
        try {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setCity(city);
            user.setZipCode(zipCode);
            user.setNoTelephone(noTelephone);
            if (passwordUpdated == true){
                String encryptedPassword = this.encryptPassword(password);
                user.setPassword(encryptedPassword);
            }
            user.setProfilePhotoPath(profilePhotoPath);
            JpaTool.openTransaction();
            userDao.merge(user);
            JpaTool.validateTransaction();
            result = user;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling modifyAccount", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public String encryptPassword(String password) {
        //The MD5 (Message Digest) is a very popular hashing algorithm. 
        //It is a cryptographic hash function that generates a 128-bits hash value.
        //This algorithm is defined under java.security package in Java programming.
        String result = null;
        try {
            String encryptedPassword = null;
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedPassword = s.toString();
            result = encryptedPassword;

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling encryptPassword", ex);
            result = null;
        }
        return result;
    }

    public User authenticate(String mail, String password) {
        JpaTool.createPersistenceContext();
        User user = null;
        String verifiedPassword = null;
        String encryptedPassword = null;
        try {
            JpaTool.openTransaction();
            System.out.println("mail" + mail);
            System.out.println(password);
            System.out.println("password" + password);
            encryptedPassword = this.encryptPassword(password);
            user = userDao.SearchByMail(mail);
            System.out.println("user trouvé par la dao" + user);

            verifiedPassword = user.getPassword();
            if (!verifiedPassword.equals(encryptedPassword)) {
                user = null;
            }
            JpaTool.validateTransaction();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling authenticate", ex);
            JpaTool.cancelTransaction();
            user = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return user;
    }

    public User findUserById(Long userId) {
        User user = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            user = userDao.searchById(userId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findUserById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return user;
    }
    
    public PreferenceTag findPreferenceById(Long preferenceId) {
        PreferenceTag preference = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            preference = preferenceTagDao.searchById(preferenceId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findPreferenceById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return preference;
    }

    public Long makeOffer(Offer offer) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            offerDao.create(offer);
            JpaTool.validateTransaction();
            result = offer.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling makeOffer", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Offer updateOffer(Offer offer) {
        Offer res = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            offerDao.merge(offer);
            JpaTool.validateTransaction();
            res = offer;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling updateOffer", ex);
            JpaTool.cancelTransaction();
            res = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return res;
    }
    
    public Offer publishOffer(Long offerId){
        Offer offer = getOfferById(offerId);
        offer.publishOffer();
        return updateOffer(offer);
    }
    
    public int checkOffersExpirationDate(){
        int cleanedOffers = 0;
        Date today = new Date();
        List<Offer> offers = new LinkedList<>();
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            offers = offerDao.getOngoingByStatus(Offer.offerState.ONGOING);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling checkOffersExpirationDate", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        for(Offer offer : offers){
            if(offer.expired(today)){
                updateOffer(offer);
                cleanedOffers++;
            }
        }
        return cleanedOffers;
    }
    
    public Long approveCook(Cook cook){
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            cookDao.create(cook);
            JpaTool.validateTransaction();
            result = cook.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling approveCook", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Offer setPrice(Long offerId, double price) {
        JpaTool.createPersistenceContext();
        Offer offer = null;
        try {
            JpaTool.openTransaction();
            offer = offerDao.searchById(offerId);
            offer.setPrice(price);
            offerDao.merge(offer);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return offer;
    }
    
    public boolean cancelOffer(Long offerId) {
        JpaTool.createPersistenceContext();
        boolean canceled = false;
        try {
            JpaTool.openTransaction();
            Offer offer = offerDao.searchById(offerId);
            offerDao.delete(offer);
            JpaTool.validateTransaction();
            canceled = true;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling cancelOffer", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return canceled;
    }
    
    public boolean cancelReservation(Long reservationId) {
        JpaTool.createPersistenceContext();
        boolean canceled = false;
        try {
            JpaTool.openTransaction();
            Reservation reservation = reservationDao.searchById(reservationId);
            reservationDao.delete(reservation);
            JpaTool.validateTransaction();
            canceled = true;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling cancelReservation", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return canceled;
    }

    public Reservation acceptRequest(Long reservationId){
        JpaTool.createPersistenceContext();
        Reservation reservation = null;
        
        try {
            JpaTool.openTransaction();
            reservation = reservationDao.searchById(reservationId);
            Offer offer = reservation.getOffer();
            if (reservation.getNbOfPortion()<=offer.getRemainingPortions()){
                reservation.setState(ReservationState.RESERVATION);
                offer.addReservation(reservation);
            }else{
                reservation.setState(ReservationState.REJECTED);
            }
            reservationDao.merge(reservation);
            offerDao.merge(offer);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling acceptRequest", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        
        return reservation;
    }
    
    public Reservation rejectRequest (Long reservationId){
        JpaTool.createPersistenceContext();
        Reservation reservation = null;
        
        try {
            JpaTool.openTransaction();
            reservation = reservationDao.searchById(reservationId);
            reservation.setState(ReservationState.REJECTED);
            reservationDao.merge(reservation);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling rejectRequest", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        
        return reservation;
    }
    
    public User getUserById(Long id) {
        JpaTool.createPersistenceContext();
        User user = null;
        try {
            JpaTool.openTransaction();
            user = userDao.searchById(id);;
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling getUserFromId", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return user;
    }

    public Offer getOfferById(Long id) {
        JpaTool.createPersistenceContext();
        Offer offer = null;
        try {
            JpaTool.openTransaction();
            offer = offerDao.searchById(id);;
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling getOfferFromId", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return offer;
    }

    //Add entries to DB
    public Long createCuisine(Cuisine cuisine) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            preferenceTagDao.create(cuisine);
            JpaTool.validateTransaction();
            result = cuisine.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createCuisine", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Long createDiet(Diet diet) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            preferenceTagDao.create(diet);
            JpaTool.validateTransaction();
            result = diet.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createDiet", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Long createIngredient(Ingredient ingredient) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            preferenceTagDao.create(ingredient);
            JpaTool.validateTransaction();
            result = ingredient.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createRequestImage(RequestImage requestImage) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            requestImageDao.create(requestImage);
            JpaTool.validateTransaction();
            result = requestImage.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Long createReservation(Reservation reservation) {
        Long result = null;
        JpaTool.createPersistenceContext();
        Offer offer = reservation.getOffer();
        //This "reservation" is still a request, so the number of portions does not change
        offer.addReservation(reservation);
        try {
            JpaTool.openTransaction();
            reservationDao.create(reservation);    
            offerDao.merge(offer);
            JpaTool.validateTransaction();
            result = reservation.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createReservation", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Long createReview(Review review) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            reviewDao.create(review);
            JpaTool.validateTransaction();
            result = review.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createReview", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public boolean updateRating(Cook cook, int reviewNote) {
        boolean result = false;
        JpaTool.createPersistenceContext(); 
        try {
            JpaTool.openTransaction();
            cook.updateRating(reviewNote);
            cookDao.merge(cook);
            JpaTool.validateTransaction();
            result = true;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling updateRating", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    public Message createMessage(Message message) {
        Message result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            messageDao.create(message);
            JpaTool.validateTransaction();
            result = message;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createMessage", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }

    //Search for objects in DB
    public Cook findCookById(Long cookId) {
        JpaTool.createPersistenceContext();
        Cook cook = null;
        try {
            JpaTool.openTransaction();
            cook = cookDao.searchById(cookId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findCookById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return cook;
    }
    
    public Cook findCookByUserId(Long userId) {
        JpaTool.createPersistenceContext();
        Cook cook = null;
        try {
            JpaTool.openTransaction();
            cook = cookDao.getCookByUserId(userId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findCookByUserId", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return cook;
    }
    
    public Offer findOfferById(Long offerId) {
        JpaTool.createPersistenceContext();
        Offer offer = offerDao.searchById(offerId);
        JpaTool.closePersistenceContext();
        return offer;
    }
    
    public Offer viewOfferDetails(Long offerId, String address){
        Offer offer = this.findOfferById(offerId);
        LatLng location = getLatLng(address);
        Double distance;
        if (offer.getLocation()!=null && location!=null){
            distance = GeoNetApi.getFlightDistanceInKm(offer.getLocation(), location);
        } else{
            distance = Double.MAX_VALUE;
        }
        offer.setDistanceToUser(distance);
        return offer;
    }

    public PreferenceTag findPreferanceTagById(Long prefTagId) {
        JpaTool.createPersistenceContext();
        PreferenceTag prefTag = null;
        try {
            JpaTool.openTransaction();
            prefTag = preferenceTagDao.searchById(prefTagId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findPreferanceTagById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return prefTag;
    }

    public Reservation findReservationById(Long reservationId) {
        JpaTool.createPersistenceContext();
        Reservation reservation = null;
        try {
            JpaTool.openTransaction();
            reservation = reservationDao.searchById(reservationId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findReservationById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return reservation;
    }

    public Review findReviewById(Long reviewId) {
        JpaTool.createPersistenceContext();
        Review review = null;
        try {
            JpaTool.openTransaction();
            review = reviewDao.searchById(reviewId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findReviewById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return review;
    }

    public Message findMessageById(Long messageId) {
        JpaTool.createPersistenceContext();
        Message message = null;
        try {
            JpaTool.openTransaction();
            message = messageDao.searchById(messageId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling findMessageById", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return message;
    }

    public PriorityQueue<Offer> consultOffers(String address) {
        //consult offers according to an address given (user connected or not)
        List<Offer> ongoingOffers;
        LatLng location = getLatLng(address);
        PriorityQueue<Offer> sortedByDistanceOffers = new PriorityQueue<>(new Comparator<Offer>() {
            public int compare(Offer n1, Offer n2) {
                Double distance1 = n1.getDistanceToUser();
                Double distance2 = n2.getDistanceToUser();
                return distance1.compareTo(distance2); //May be - instead
            }
        });
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            ongoingOffers = offerDao.getOngoingOffers(20);
            //check the preferences in ongoingOffers + distance to User
            double distance;
            for (Offer offer : ongoingOffers) {// total complexity O(n * log(n))
                if (offer.getLocation()!=null && location!=null){
                    distance = GeoNetApi.getFlightDistanceInKm(offer.getLocation(), location);
                } else{
                    distance = Double.MAX_VALUE;
                }
                offer.setDistanceToUser(distance);
                sortedByDistanceOffers.add(offer); // insertion on O(log(n))   
            }
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling consultOffers", ex);
            JpaTool.cancelTransaction();
            sortedByDistanceOffers = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return sortedByDistanceOffers;
    }

    public List<Offer> searchOffers(List<Long> requestPreferences, int priceLimit, User user, String address) {
        //SearchOffers according to :diet, cuisine, user's preferences, price and location

        List<Offer> ongoingOffers;
        List<Offer> res;
        PriorityQueue<Offer> sortedByDistanceOffers = new PriorityQueue<>(new Comparator<Offer>() {
            public int compare(Offer n1, Offer n2) {
                Double distance1 = n1.getDistanceToUser();
                Double distance2 = n2.getDistanceToUser();
                return distance1.compareTo(distance2); //May be - instead
            }
        });
        
        LatLng location = getLatLng(address);

        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();

            ongoingOffers = offerDao.getOngoingOffers(priceLimit);
            //generate preferences list
            List<Long> preferences = new ArrayList<>();
            List<Long> ingredients = new ArrayList<>();
            if(requestPreferences!=null){
                preferences.addAll(requestPreferences); //cuisine and diets
            }
            //check if user is logged in yet
            if (user!=null){
                user.getPreferences().forEach(preference -> {
                    if (!(preference instanceof Ingredient)) {
                        preferences.add(preference.getId());
                    } else {
                        ingredients.add(preference.getId());
                    }
                });
            }
            //check the preferences in ongoingOffers + distance to User
            double distance;
            List<Long> offerClassifications = new ArrayList<>();
            for (Offer offer : ongoingOffers) {// total complexity O(n * log(n))
                offerClassifications.clear();
                offer.getClassifications().forEach(classification ->{
                    offerClassifications.add(classification.getId());
                });
                if (offerClassifications.containsAll(preferences) && Collections.disjoint(offerClassifications, ingredients)) {
                    if (offer.getLocation()!=null && location!=null){
                        distance = GeoNetApi.getFlightDistanceInKm(offer.getLocation(), location);
                    } else{
                        distance = Double.MAX_VALUE;
                    }
                    offer.setDistanceToUser(distance);
                    sortedByDistanceOffers.add(offer); // insertion on O(log(n))
                }
            }
            System.out.println(ongoingOffers.size());
            res = new ArrayList(sortedByDistanceOffers);

            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling searchOffers", ex);
            JpaTool.cancelTransaction();
            res = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return res;
    }

    public User specifyPreferences(List<PreferenceTag> listPref, User user) {
        JpaTool.createPersistenceContext();
        user.setPreferences(listPref);
        try {
            JpaTool.openTransaction();
            userDao.merge(user);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling specifyPreferences", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return user;
    }

    public List<Reservation> searchPurchasedMeals(User user) {
        JpaTool.createPersistenceContext();
        List<Reservation> purchasedMeals = new LinkedList<>();
        try {
            JpaTool.openTransaction();
            purchasedMeals = reservationDao.searchPurchasedMeals(user);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling searchPurchasedMeals", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return purchasedMeals;
    }
    
    public List<CookRequest> searchCookRequests() {
        JpaTool.createPersistenceContext();
        List<CookRequest> cookRequests = new LinkedList<>();
        try {
            JpaTool.openTransaction();
            cookRequests = cookRequestDao.searchCookRequests();
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling searchCookRequest", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return cookRequests;
    }
    
    public List<Offer> viewOffersHistory(Cook cook) {
        //consult offers that have been created by a cook
        List<Offer> offersHistory = null;
        JpaTool.createPersistenceContext();

        try {
            JpaTool.openTransaction();
            offersHistory = offerDao.getOffers(cook.getId());
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewOffersHistory", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return offersHistory;
    }
    
    public List<User> consultGuestsList(Offer offer) {
        //returns the list of users who made a reservation on an offer
        List<User> guestsList = new LinkedList<>();
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            for(Reservation reservation : offer.getReservations()){
                if(!guestsList.contains(reservation.getCustomer())){
                    guestsList.add(reservation.getCustomer());
                }
            }
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling consultGuestsList", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return guestsList;
    }
    
    public CookRequest viewCookRequest(Long cookRequestId) {
        CookRequest cookRequest = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            cookRequest = cookRequestDao.searchById(cookRequestId);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewCookRequest", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return cookRequest;
    }
    
    public List<Reservation> viewReservationsList(User user) {
        //view the reservations made by a user
        List<Reservation> reservationsList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            reservationsList = reservationDao.getReservationsListByUserId(user.getId());
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewReservationsList", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return reservationsList;
    }
    
    public List<Reservation> viewReservationsRequests(Cook cook) {
        //view the reservations requests made by users for offers of a cook
        List<Reservation> reservationsList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            reservationsList = reservationDao.getReservationsRequests(cook.getId());
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewReservationsList", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return reservationsList;
    }

    public Long becomeCook(CookRequest cookRequest) {
        Long result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            cookRequestDao.create(cookRequest);
            JpaTool.validateTransaction();
            result = cookRequest.getIdCookRequest();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling becomeCook", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public List<Review> viewCooksReviews(Long cookId){
        List<Review> result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            List <Offer> madeOffers = offerDao.getOffers(cookId);

            for (Offer offer : madeOffers){
                if (result==null){
                    result = reviewDao.getOffersReviews(offer.getId());
                } else{
                    result.addAll(reviewDao.getOffersReviews(offer.getId()));
                }
                
            }
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewCooksReviews", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public List<Review> viewGuestReviews(User user){
        List<Review> result = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            result = reviewDao.getGuestReviews(user);
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewGuestsReviews", ex);
            JpaTool.cancelTransaction();
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public List<Offer> viewOngoingOffersList(Cook cook) {
        //view the ongoing offers made by a cook
        List<Offer> offersList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            offersList = offerDao.getOngoingOffersByCookId(cook.getId());
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewOngoingOffersList", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return offersList;
    }
    
    public List<Diet> viewDiets() {
        List<Diet> dietsList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            dietsList = preferenceTagDao.getDiets();
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewDiets", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return dietsList;
    }
    
    public List<Cuisine> viewCuisines() {
        List<Cuisine> cuisinesList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            cuisinesList = preferenceTagDao.getCuisines();
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewCuisines", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return cuisinesList;
    }
    
    public List<Ingredient> viewIngredients() {
        //
        List<Ingredient> ingredientsList = null;
        JpaTool.createPersistenceContext();
        try {
            JpaTool.openTransaction();
            ingredientsList = preferenceTagDao.getIngredients();
            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling viewIngredients", ex);
        } finally {
            JpaTool.closePersistenceContext();
        }
        return ingredientsList;
    }
    
    public PriceEstimate getMinMaxPrice(List<Ingredient> ingredients){
        JsonObject json = getSpoonacularResponseByIngredients(ingredients);
        Long id = Long.parseLong(json.get("id").getAsString());
        String title = json.get("title").getAsString();
        JsonObject json2 = getRequestAsJsonObject("https://api.spoonacular.com/recipes/"
                +id+"/priceBreakdownWidget.json?apiKey="+spoonacularKey);
        System.out.println(json2);
        Double price = Double.parseDouble(json2.get("totalCostPerServing").getAsString())/100.0;
        PriceEstimate estimate = new PriceEstimate(price, price*2, title);
        return estimate;
    }
    
    public JsonObject getSpoonacularResponseByIngredients(List<Ingredient> ingredients){
        String urlString = "";
        for(int i=0; i<ingredients.size(); i++){
            urlString+=ingredients.get(i).getName();
            if(i<ingredients.size()-1){
                urlString+=",+";
            }
        }
        JsonObject json = getRequestAsJsonObject("https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                    +urlString+"&number=1&apiKey="+spoonacularKey);
        return json;
    }
    
    public PriceEstimate getMinMaxPriceFromStrings(List<String> ingredients){
        String urlString = "";
        for(int i=0; i<ingredients.size(); i++){
            urlString+=ingredients.get(i);
            if(i<ingredients.size()-1){
                urlString+=",+";
            }
        }
        JsonObject json = getRequestAsJsonObject("https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                    +urlString+"&number=1&apiKey=0edfed31f0a340a9927395d2d566d6fb");
        Long id = Long.parseLong(json.get("id").getAsString());
        String title = json.get("title").getAsString();
        JsonObject json2 = getRequestAsJsonObject("https://api.spoonacular.com/recipes/"
                +id+"/priceBreakdownWidget.json?apiKey=0edfed31f0a340a9927395d2d566d6fb");
        System.out.println(json2);
        Double price = Double.parseDouble(json2.get("totalCostPerServing").getAsString())/100.0;
        PriceEstimate estimate = new PriceEstimate(((int)(price.doubleValue()*100))/100.0, ((int)(price.doubleValue()*2*100))/100.0, title);
        return estimate;
    }
    
    public JsonObject getRequestAsJsonObject(String urlString){
        URL url;
        JsonObject json = null;
        try {
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int status = con.getResponseCode();
            System.out.println(status);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String temp = content.toString();
            if(temp.startsWith("[") && temp.endsWith("]")){
                temp = temp.substring(1, temp.length()-1);
            }
            json = new JsonParser().parse(temp).getAsJsonObject(); 
        } catch (MalformedURLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            
        }
        return json;
    }
}
