/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service;

import com.meeteat.dao.JpaTool;
import com.meeteat.dao.OfferDao;
import com.meeteat.model.Offer.Offer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ithan
 */
public class ServiceTest {
    OfferDao offerDao = new OfferDao();
    Service service = new Service();
    
    public ServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        JpaTool.init();
    }
    
    @AfterAll
    public static void tearDownClass() {
        JpaTool.destroy();
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    
    
//    @Test
//    public void setPriceTest(){
//        Offer offer = new Offer();
//        service.makeOffer(offer);
//        double price = 69.69;
//        service.setPrice(offer.getId(), price);
//        Offer offer3 = service.findOfferById(offer.getId());
//        assertEquals(offer3.getId(), offer.getId());
//        assertEquals(offer3.getPrice(), price);
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void publishOfferTest(){
        Offer offer = new Offer();
        service.makeOffer(offer);
        service.publishOffer(offer.getId());
        Offer newOffer = service.getOfferFromId(offer.getId());
        System.out.println(newOffer.getState());
        assertEquals(newOffer.getState(), Offer.offerState.ONGOING);
    }
}
