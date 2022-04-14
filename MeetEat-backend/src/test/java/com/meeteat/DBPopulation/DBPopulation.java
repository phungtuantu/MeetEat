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
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
/**
 *
 * @author ithan
 */
public class DBPopulation {
    Faker faker;
    Service service;
    LinkedList<Long> userIdList = new LinkedList<>();
    public DBPopulation(){
        faker = new Faker();
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
        for(int i = 0; i<nbCooks; i++){
            User user = service.findUserById(userIdList.get(i));
            Date date = new Date(faker.date());
            Cook cook = new Cook(user, faker.date(), faker.university().name(), faker.job().keySkills());
            service.approveCook(cook);
        }
    }
    
    public static void main(String [] args){
        Faker faker = new Faker();
        Service service = new Service();
        for(int i = 0; i<10; i++){
            Food food = faker.food();
            System.out.println(food.ingredient());
        }
    }
    
}
