/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meeteat.model.Offer;

/**
 *
 * @author johanbonnedahl
 */
public class PriceEstimate {
    private double minPrice, maxPrice;
    private String recipeGuess;

    public PriceEstimate(double minPrice, double maxPrice, String recipeGuess) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.recipeGuess = recipeGuess;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getRecipeGuess() {
        return recipeGuess;
    }
    
    
}
