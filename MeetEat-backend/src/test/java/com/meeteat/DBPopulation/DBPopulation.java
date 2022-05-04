/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.DBPopulation;
import com.github.javafaker.Address;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.Food;
import com.github.javafaker.Name;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Number;
import com.meeteat.dao.JpaTool;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Diet;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author ithan
 */
public class DBPopulation {
    Faker faker;
    Service service;
    LinkedList<Long> userIdList = new LinkedList<>();
    LinkedList<Long> cookIdList = new LinkedList<>();
    LinkedList<Long> ingredientsList = new LinkedList<>();
    LinkedList<Long> dietList = new LinkedList<>();
    LinkedList<Long> cuisineList = new LinkedList<>();
    LinkedList<Long> cookRequestList = new LinkedList<>();
    LinkedList<Long> createdOfferList = new LinkedList<>();
    LinkedList<Long> ongoingOfferList = new LinkedList<>();
    LinkedList<Long> expiredOfferList = new LinkedList<>();
    LinkedList<Long> soldoutOfferList = new LinkedList<>();
    LinkedList<Long> reservationList = new LinkedList<>();
    Locale locale = new Locale("fr");
    int nbProfilePictures = 20;
    int nbOfferPictures = 20;
    
    public DBPopulation(){
        
        faker = new Faker(locale);
        service = new Service();
    }
    
    public void createUsers(int nbUsers){
        System.out.println("creating users...");
        for(int i = 0; i<nbUsers; i++){
            String email = faker.internet().emailAddress();
            String profilePhoto = faker.internet().image();
            String payementInfo = faker.crypto().sha1();
            Address address = faker.address();
            Name name = faker.name();
            String phone = faker.phoneNumber().cellPhone();
            String password = "password";
            User user = new User(name.firstName(), name.lastName(), address.streetAddress(), address.city(), address.zipCode(), phone, email);
            user.setProfilePhotoPath("./Images/profile_images/profile" + (i%nbProfilePictures + 1));
            Long created = service.createAccount(user, password);
            if(created != null){
                userIdList.add(created);
            }
        }
    }
    
    public void createCooks(int nbCooks){
        assert(nbCooks < userIdList.size());
        System.out.println("creatin cooks...");
        for(int i = 0; i<nbCooks; i++){
            User user = service.findUserById(userIdList.get(i));
            DateAndTime dat = faker.date();
            Date validationDate = dat.birthday(0, 2);
            Cook cook = new Cook(user, validationDate, faker.number().numberBetween(1, 6), faker.university().name(), faker.job().keySkills());
            Long created = service.approveCook(cook);
            if(created != null){
                cookIdList.add(created);
            }
        }
    }
    
    public void createIngedients(int nbIngredients){
        System.out.println("creatin ingredients...");
        for(int i =0; i<(nbIngredients/2); i++){
            Food food = faker.food();
            Ingredient ingredient = new Ingredient(food.ingredient());
            Long created = service.createPreferenceTag(ingredient);
            if(created != null){
               ingredientsList.add(created); 
            }
        }
        for(int i =0; i<(nbIngredients/2); i++){
            Food food = faker.food();
            Ingredient ingredient = new Ingredient(food.spice());
            Long created = service.createPreferenceTag(ingredient);
            if(created != null){
               ingredientsList.add(created); 
            }
        }
    }
    
    public void createDiets(){
        System.out.println("creatin diets...");
        LinkedList<Diet> dietList1 = new LinkedList<>();
        dietList1.add(new Diet("Vegetarian"));
        dietList1.add(new Diet("Vegan"));
        dietList1.add(new Diet("Pesco Vegetarian"));
        dietList1.add(new Diet("Diary-free"));
        dietList1.add(new Diet("Gluten free"));
        dietList1.add(new Diet("No pork"));
        for(Diet diet : dietList1){
            Long created = service.createPreferenceTag(diet);
            if(created != null){
                dietList.add(created);
            }
        }
    }
    
    public void createCuisines(int nbCuisines){
        System.out.println("creatin cuisines...");
        for(int i = 0; i<nbCuisines; i++){
            Country country = faker.country();
            String name = country.name();
            Cuisine cuisine = new Cuisine(name);
            Long created = service.createPreferenceTag(cuisine);
            if(created != null){
                cuisineList.add(created);
            }
        }
    }
    
    public void createOffers(int nbOffers){
        int min = 0;
        DateAndTime dat = faker.date();
        Date today = new Date();
        System.out.println("creatin offers...");
        Date minDate = dat.past(10, TimeUnit.DAYS, today);
        Date maxDate = dat.future(10, TimeUnit.DAYS, today);
        for(int i = 0; i<nbOffers; i++){
            Number number = faker.number();
            Address address = faker.address();
            List<Ingredient> ingredients = getIngredientsForOffer();
            List<PreferenceTag> classifications = getPreferenceTagForOffer();
            Cook cook = service.findCookById(cookIdList.get(number.numberBetween(min, cookIdList.size())));
            Food food = faker.food();
            dat = faker.date();
            String title = food.dish();
            Date availableFrom = dat.between(minDate, maxDate);
            Date expDate = dat.between(availableFrom, dat.future(10, TimeUnit.DAYS, availableFrom));
            String imagePath = faker.internet().avatar();
            //double price = number.randomDouble(2, 0, 20);
            int totalPortions = number.numberBetween(1, 30);
            String details = food.spice();
            String specifications = faker.backToTheFuture().quote();
            Offer offer = new Offer(cook, availableFrom, title, totalPortions,
                                  details, classifications, ingredients, specifications, address.streetAddress(), address.city(), 
                                    address.zipCode(), expDate, imagePath);
            offer.setOfferPhotoPath("./Images/profile_images/meal" + (i%nbOfferPictures + 1));
            Long created = service.makeOffer(offer);
            if(created != null){
                createdOfferList.add(created);
            }
        }
    }
    
    public void publishOffers(int nbOffersToPublish){
        System.out.println("Publishing offers");
        assert(nbOffersToPublish < createdOfferList.size());
        Number number = faker.number();
        for(int i = 0; i<nbOffersToPublish; i++){
            int chosenOffer = number.numberBetween(0, createdOfferList.size());
            Offer offerToPublish = service.getOfferById(createdOfferList.get(chosenOffer));
            try{
                offerToPublish = service.publishOffer(offerToPublish.getId());
            }catch(Exception e){
                System.out.println("Dates are incoherent");
                offerToPublish = null;
            }finally{
                if(offerToPublish != null){
                    ongoingOfferList.add(offerToPublish.getId());
                }
            }
        }
    }
    
    public void createReservations(int nbReservations){
        System.out.println("creating reservations...");
        int reservationsMade = 0;
        Number number = faker.number();
        DateAndTime dateAndTime = faker.date();
        while(reservationsMade < nbReservations){
            if(ongoingOfferList.isEmpty()){
                break;
            }
            int offerNumber = number.numberBetween(0, ongoingOfferList.size()-1);
            Offer offer = service.getOfferById(ongoingOfferList.get(offerNumber));
            int reservationsToBeMade = number.numberBetween(0, (nbReservations - reservationsMade));
            for(int reservationNumber = 0; reservationNumber <= reservationsToBeMade; reservationNumber++){
                if(offer.getState() == Offer.offerState.SOLDOUT){
                    soldoutOfferList.add(offer.getId());
                    boolean found = ongoingOfferList.remove(offer.getId());
                    assert(found);
                    break;
                }
                //Find a random user
                int userNumber = number.numberBetween(0, userIdList.size()-1);
                User customer = service.findUserById(userIdList.get(userNumber));
                assert(customer != null);
                //Find a random date
                Date availableFrom = offer.getAvailableFrom();
                Date expirationDate = offer.getExpirationDate();
                Date reservationDate = dateAndTime.between(availableFrom, expirationDate);
                //Assign a random state to the reservation
                ReservationState state = ReservationState.values()[number.numberBetween(0, ReservationState.values().length)];
                //Assign a random number of portions
                int nbPortions = number.numberBetween(1, offer.getRemainingPortions()/5);
                Reservation reservation = new Reservation(reservationDate, state, nbPortions, offer, customer);
                Long created = service.createReservation(reservation);
                if(created != null){
                    reservationList.add(created);
                    reservationsMade++;
                }
            }
        }
    }
    
    public void createReviews(int nbReviews){
        
    }
    
    public void populateDatabase(int nbUsers, int nbCooks, int nbIngredients, int nbCuisines, 
                                 int nbOffers, int nbOffersToPublish, int nbReservations){
        JpaTool.init();
        createUsers(nbUsers);
        createCooks(nbCooks);
        createIngedients(nbIngredients);
        createDiets();
        createCuisines(nbCuisines);
        createOffers(nbOffers);
        publishOffers(nbOffersToPublish);
        createReservations(nbReservations);
        int expired = service.checkOffersExpirationDate();
        System.out.println(expired + " offers expired today");
        JpaTool.destroy();
    }
    private List<Ingredient> getIngredientsForOffer(){
        int min = 0;
        int max = ingredientsList.size();
        Number number = faker.number();
        int lower = number.numberBetween(min, max);
        int upper = number.numberBetween(lower, max);
        List<Ingredient> ll = new LinkedList<>();
        for(int i = lower; i<upper; i++){
            ll.add((Ingredient) service.findPreferenceById(ingredientsList.get(i)));
        }
        return ll;
    }
    
    private List<PreferenceTag> getPreferenceTagForOffer(){
        Number number = faker.number();
        int nbCuisines = number.numberBetween(0, cuisineList.size());
        int nbDiets = number.numberBetween(0, dietList.size());
        List<PreferenceTag> ll = new LinkedList<>();
        int startCuisine = number.numberBetween(0, cuisineList.size());
        while((startCuisine + nbCuisines) >= cuisineList.size()){
            startCuisine = number.numberBetween(0, cuisineList.size());
        }
        int startDiet = number.numberBetween(0, dietList.size());
        while((startDiet + nbDiets) >= dietList.size()){
            startDiet = number.numberBetween(0, dietList.size());
        }
        for(int i = startCuisine; i<(startCuisine + nbCuisines); i++){
            ll.add(service.findPreferenceById(cuisineList.get(i)));
        }
        for(int i = startDiet; i<(startDiet + nbDiets); i++){
            ll.add(service.findPreferenceById(dietList.get(i)));
        }
        return ll;
    }
    
    public static void main(String [] args){
        DBPopulation dbp = new DBPopulation();
        dbp.populateDatabase(100, 30, 200, 20, 50, 30, 40);
    }
}
