/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service;

import com.meeteat.dao.CookDao;
import com.meeteat.dao.JpaTool;
import com.meeteat.dao.MessageDao;
import com.meeteat.dao.OfferDao;
import com.meeteat.dao.PreferenceTagDao;
import com.meeteat.dao.ReservationDao;
import com.meeteat.dao.ReviewDao;
import com.meeteat.dao.UserDao;
import com.meeteat.model.Offer.Message;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.Review;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Long createAccount(User user){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            userDao.create(user);
            JpaTool.validateTransaction();
            result = user.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createAccount", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long makeOffer(Offer offer){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            offerDao.create(offer);
            JpaTool.validateTransaction();
            result = offer.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling makeOffer", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long approveCook(Cook cook){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            cookDao.create(cook);
            JpaTool.validateTransaction();
            result = cook.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling approveCook", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Offer setPrice(Long offerId, double price){
        JpaTool.createPersistenceContext();
        Offer offer = null;
        try{
            JpaTool.openTransaction();
            offer = offerDao.searchById(offerId);
            offer.setPrice(price);
            offerDao.merge(offer);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return offer;
    }
    
    //Add entries to DB
    public Long createCuisine(Cuisine cuisine){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            preferenceTagDao.create(cuisine);
            JpaTool.validateTransaction();
            result = cuisine.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createCuisine", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createDiet(Diet diet){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            preferenceTagDao.create(diet);
            JpaTool.validateTransaction();
            result = diet.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createDiet", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createIngredient(Ingredient ingredient){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            preferenceTagDao.create(ingredient);
            JpaTool.validateTransaction();
            result = ingredient.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createReservation(Reservation reservation){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            reservationDao.create(reservation);
            JpaTool.validateTransaction();
            result = reservation.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createReview(Review review){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            reviewDao.create(review);
            JpaTool.validateTransaction();
            result = review.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createMessage(Message message){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            messageDao.create(message);
            JpaTool.validateTransaction();
            result = message.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createIngredient", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    //Search for objects in DB
    public Cook findCookById(Long cookId){
        JpaTool.createPersistenceContext();
        Cook cook = null;
        try{
            JpaTool.openTransaction();
            cook = cookDao.searchById(cookId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling searchCookWithID", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return cook;
    }
    
    public Offer findOfferById(Long offerId){
        JpaTool.createPersistenceContext();
        Offer offer = null;
        try{
            JpaTool.openTransaction();
            offer = offerDao.searchById(offerId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return offer;
    }
    
    public User findUserById(Long userId){
        JpaTool.createPersistenceContext();
        User user = null;
        try{
            JpaTool.openTransaction();
            user = userDao.searchById(userId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return user;
    }
    
    public PreferenceTag findPreferanceTagById(Long prefTagId){
        JpaTool.createPersistenceContext();
        PreferenceTag prefTag = null;
        try{
            JpaTool.openTransaction();
            prefTag = preferenceTagDao.searchById(prefTagId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return prefTag;
    }
    
    public Reservation findReservationById(Long reservationId){
        JpaTool.createPersistenceContext();
        Reservation reservation = null;
        try{
            JpaTool.openTransaction();
            reservation = reservationDao.searchById(reservationId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return reservation;
    }
    
    public Review findReviewById(Long reviewId){
        JpaTool.createPersistenceContext();
        Review review = null;
        try{
            JpaTool.openTransaction();
            review = reviewDao.searchById(reviewId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return review;
    }

    public Message findMessageById(Long messageId){
        JpaTool.createPersistenceContext();
        Message message = null;
        try{
            JpaTool.openTransaction();
            message = messageDao.searchById(messageId);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return message;
    }
    
     public List <Offer> searchOffers(List<Long> requestPreferences, int priceRange, User user) {
        //diet --> ok
        //cuisine -->ok
        //preferences (ingredients)--> ok
        //price --> ok
        //localisation
        List <Offer> ongoingOffers;
        List <Offer> result = null;
        JpaTool.createPersistenceContext(); 
        try {
            JpaTool.openTransaction();
            
            //priceRange
            int priceLimit;
            switch (priceRange) {
                case 1 ->
                    priceLimit = 5;
                case 2 ->
                    priceLimit = 9;
                default ->
                    priceLimit = 20;
            }
            ongoingOffers = offerDao.getOngoingOffers(priceLimit);
            
            //generate preferences list
            List <Long> preferences = null; 
            List <Long> ingredients = null;
            preferences.addAll(requestPreferences); //cuisine and diets
            for (PreferenceTag preference:user.getPreferences()){
                if(!(preference instanceof Ingredient)){
                    preferences.add(preference.getId());
                }else{
                    ingredients.add(preference.getId());
                }
            }
            
            //check the preferences in ongoingOffers
            
            for(Offer offer:ongoingOffers){
                if(offer.getClassifications().containsAll(preferences) && Collections.disjoint(offer.getClassifications(), ingredients)){
                    result.add(offer);
                }
            }
            
            //location

            JpaTool.validateTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling searchOffers", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally {
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    public User specifyPreferences(List<PreferenceTag> listPref, User user){
        JpaTool.createPersistenceContext();
        User user1 = user;
        user1.setPreferences(listPref);
        try{
            JpaTool.openTransaction();
            user = userDao.modify(user1);
            JpaTool.validateTransaction();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return user;
    }
    
}
