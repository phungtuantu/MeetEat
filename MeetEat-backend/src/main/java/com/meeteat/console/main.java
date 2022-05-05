/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.console;

import com.meeteat.dao.JpaTool;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.PriceEstimate;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.Offer.Review;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
//import com.meeteat.DBpopulation.DBpopulation;

/**
 *
 * @author gvnge
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JpaTool.init();
//        testCreatePref();
//        testCreateAccount();
//        testModifyAccount();
//        testApproveCook();
//        testMakeOffer();
//        testSpecifyPreferences();
//        testViewPurchasedMeals();
//        testMakeReservation();
//        testAuthenticate();
//        testCreateReview();
//          testViewOffersHistory();
//           testSearchOffers();
//        testGetPrice();
        JpaTool.destroy();
    }
    
    public static void testGetPrice(){
        Service service = new Service();
        Offer offer = service.getOfferById(101l);
        PriceEstimate e = service.getMinMaxPrice(offer.getIngredients());
        System.out.println("We think that you are cooking: " + e.getRecipeGuess() 
                + " and suggest a price between " + e.getMinPrice() + " and " + e.getMaxPrice());
        
    }
    
    public static void testCreatePref(){
        Service service = new Service();
        Cuisine cuisine = new Cuisine ("Asian shit");
        service.createCuisine(cuisine);
    }
    
    public static void testCreateAccount(){
        Service service = new Service();
        System.out.println("create an account");
        User user = new User("Bob", "Smith","here", "this city", "1010","0611","bobsmith@here.com");
        service.createAccount(user, "password");
    }
    
    public static void testModifyAccount(){
        Service service = new Service();
        System.out.println("modify an account");
        Long id = new Long(100);
        User user = service.modifyAccount(id, "Luna", "Booba", "avenue des bgs", "lyon", "2323",
            "060000000",false, "hacker", ""); 
        System.out.println(user);
    }
    
    public static void testCreateReview(){
        Service service = new Service();
        System.out.println("create a review");
        User user = service.findUserById( (long) 2);
        User user2 = service.findUserById( (long) 3);
        Cook cook = service.findCookById( (long) 3);
        List<PreferenceTag> classifications = new LinkedList<>();
        List<Ingredient> ingredients = new LinkedList<>();
        Offer offer = new Offer(cook, new Date(), "teest2", 11, "bery goood food", classifications, ingredients, "noone","address1","city2","zipcode3", new Date(), "path");
        service.makeOffer(offer);
        Reservation res1 = new Reservation(new Date(), ReservationState.PURCHASEDMEAL , 6, offer, user);
        service.createReservation(res1);
        Review rev = new Review(res1, user2, user, 4, "delicious" );
        service.createReview(rev);
    }
    
    public static void testApproveCook(){
        Service service = new Service();
        System.out.println("create an account then make a cook");
        User user = new User("Bob the Second", "Smith","here","this city", "1010", "0611","bobthesecondsmith@here.com");
        service.createAccount(user, "password");
        System.out.println("make him a cook");
        Cook cook = new Cook(user, new Date(), 0, "", "none");
        System.out.println(user);
        System.out.println(cook);
        service.approveCook(cook);
        System.out.println(cook);
    }
    
    
    public static void testViewPurchasedMeals(){
        Service service = new Service();
        System.out.println("View purchased meals");
        User user2 = new User("Bob the Second", "Smith","here","0611","bobthesecondsmith2@here.com","password","oh");
//        service.createAccount(user2);
        Cook cook = new Cook(user2, new Date(), 0, "", "none");
//        service.approveCook(cook);
        List<PreferenceTag> classifications = new LinkedList<>();
        List<Ingredient> ingredients = new LinkedList<>();
        Offer offer = new Offer(cook, new Date(), "teest2", 11, "bery goood food", classifications, ingredients, "noone","address1","city2","zipcode3", new Date(), "path");
        User user = new User("Bob the Second", "Smith","here","0611","bobthesecondsmith@here.com","password","ohoo");
//        service.createAccount(user); 
//        service.makeOffer(offer);
        Reservation res1 = new Reservation(new Date(), ReservationState.PURCHASEDMEAL , 6, offer, user);
//        service.createReservation(res1);
        Reservation res2 = new Reservation(new Date(), ReservationState.PURCHASEDMEAL , 4, offer, user);
//        service.createReservation(res2);
        User user3 = service.findUserById( (long) 2);
        List<Reservation> res = service.searchPurchasedMeals(user3);
        res.forEach(r -> {
            System.out.println(r.getNbOfPortion());
        });
    }
    
//    public static void testMakeOffer(){
//        Service service = new Service();
//        System.out.println("make a few offers");
//        long cookId = 1;
//        Cook cook = service.findCookById(cookId);
//        List<PreferenceTag> classifications = new LinkedList<>();
//        List<Ingredient> ingredients = new LinkedList<>();
//        ingredients.add(new Ingredient("pork chops"));
//        ingredients.add(new Ingredient("potatoes"));
//        ingredients.add(new Ingredient("cream"));
//        Offer offer = new Offer(cook, new Date(), "My pork chops",5.5, 10, "bery good food", classifications, ingredients, "none", "there", "that city", "1011");
//        service.makeOffer(offer);
//    }
    
    public static void testMakeReservation(){
        Service service = new Service();
        Reservation reservation = new Reservation(new Date(System.currentTimeMillis()),ReservationState.REQUEST,2, service.getOfferById(1l), service.getUserById(1l));
        service.createReservation(reservation);
    }
    
    public static void testSpecifyPreferences(){
        Service service = new Service();
        System.out.println("specify preferences");
        User user = new User("Bob the Second", "Smith","here","0611","bobthesecondsmith@here.com","password","oh");
        service.createAccount(user, "password");
        PreferenceTag pref1 = new PreferenceTag("viande");
        PreferenceTag pref2 = new PreferenceTag("beurre");
        PreferenceTag pref3 = new PreferenceTag("riz");
        List<PreferenceTag> prefs = new LinkedList<>();
        prefs.add(pref3);
        List<PreferenceTag> prefsChange = new LinkedList<>();
        prefsChange.add(pref1);
        prefsChange.add(pref2);
        user.setPreferences(prefs);
        System.out.println(user.getPreferences().get(0).getName());
        user = service.specifyPreferences(prefsChange, user);
        System.out.println(user.getPreferences().get(0).getName());
        System.out.println(user.getPreferences().get(1).getName());
    }
    
    public static void testAuthenticate (){
//        Service service = new Service();
//        System.out.println("authenticate a user");
//        long userId = 1;
//        User user = service.findUserById(userId);
//        System.out.println("Le user de test est: ");
//        System.out.println(user);
//        User AuthenticatedUser=service.authenticate(user.getMail(), "password");
//        System.out.println("Avec le bon mdp : Le user connecté est: ");
//        System.out.println(AuthenticatedUser);
//        User NonAuthenticatedUser=service.authenticate(user.getMail(), "hack");
//        System.out.println("Avec le mauvais mdp : Le user connecté est: ");
//        System.out.println(NonAuthenticatedUser);
        Service service = new Service();
        System.out.println("authenticate a user");
//        long userId = 1;
//        User user = service.findUserById(userId);
//        System.out.println("Le user de test est: ");
//        System.out.println(user);
        User AuthenticatedUser=service.authenticate("lilou.gonzalez@yahoo.fr", "password");
        System.out.println("Avec le bon mdp : Le user connecté est: ");
        System.out.println(AuthenticatedUser);
        User NonAuthenticatedUser=service.authenticate("lilou.gonzalez@yahoo.fr", "hack");
        System.out.println("Avec le mauvais mdp : Le user connecté est: ");
        System.out.println(NonAuthenticatedUser);
    }
    
    public static void testViewOffersHistory(){
        Service service = new Service();
        System.out.println("View offers history");
        Cook cook = service.findCookById(Long.parseLong("1"));
        List<PreferenceTag> classifications = new LinkedList<>();
        List<Ingredient> ingredients = new LinkedList<>();
        Offer offer1 = new Offer(cook, new Date(), "teest2", 11, "bery goood food", classifications, ingredients, "noone","address1","city2","zipcode3", new Date(), "path");
        service.makeOffer(offer1);
        Offer offer2 = new Offer(cook, new Date(), "teest3", 11, "bery goood food", classifications, ingredients, "noone","address10","city2","zipcode3", new Date(), "path");
        service.makeOffer(offer2);
        System.out.println("the test offers are:");
        System.out.println(offer1);
        System.out.println(offer2);
        List<Offer> offers = service.viewOffersHistory(cook);
        offers.forEach(o -> {
            System.out.println(o);
        });
    }
    
    public static void testSearchOffers(){
        Service service = new Service();
        User user = service.findUserById(Long.parseLong("2"));
        List<Long> requestPreferences = null;
        List <Offer> offers = service.searchOffers(requestPreferences, 2 , user, "Villeurbanne");
        System.out.println(offers);
    }

}
