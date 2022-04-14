/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.DBPopulation;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Food;
import com.github.javafaker.Name;
import com.github.javafaker.DateAndTime;
import com.meeteat.dao.JpaTool;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
/**
 *
 * @author ithan
 */
public class DBPopulation {
    Faker faker;
    Service service;
    LinkedList<Long> userIdList = new LinkedList<>();
    LinkedList<Long> cookIdList = new LinkedList<>();
    Locale locale = new Locale("fr");
    public DBPopulation(){
        faker = new Faker(locale);
        service = new Service();
    }
    
    public void createUsers(int nbUsers){
        for(int i = 0; i<nbUsers; i++){
            String email = faker.pokemon().name();
            String password = faker.crypto().sha1();
            String payementInfo = faker.crypto().sha1();
            Address address = faker.address();
            Name name = faker.name();
            String phone = faker.phoneNumber().cellPhone();
            User user = new User(name.firstName(), name.lastName(), address.streetAddress(), phone, email, password);
            userIdList.add(service.createAccount(user));
        }
    }
    
    public void createCooks(int nbCooks){
        assert(nbCooks < userIdList.size());
        for(int i = 0; i<nbCooks; i++){
            User user = service.findUserById(userIdList.get(i));
            DateAndTime dat = faker.date();
            Date validationDate = dat.birthday(0, 2);
            Cook cook = new Cook(user, validationDate, faker.number().numberBetween(1, 6), faker.university().name(), faker.job().keySkills());
            cookIdList.add(service.approveCook(cook));
        }
    }
    
    public void createIngedients(int nbIngredients){
        for(int i =0; i<(nbIngredients/2); i++){
            Food food = faker.food();
            Ingredient ingredient = new Ingredient(food.ingredient());
            service.createIngredient(ingredient);
        }
        for(int i =0; i<(nbIngredients/2); i++){
            Food food = faker.food();
            Ingredient ingredient = new Ingredient(food.spice());
            service.createIngredient(ingredient);
        }
    }
    
    public static void main(String [] args){
        JpaTool.init();
        DBPopulation ss = new DBPopulation();
        ss.createUsers(5);
        ss.createCooks(3);
        ss.createIngedients(15);
        JpaTool.destroy();
    }
    
}
