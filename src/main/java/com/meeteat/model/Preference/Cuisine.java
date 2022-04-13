/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Preference;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author yousr
 */
@Entity
public class Cuisine extends PreferenceTag implements Serializable {

    public Cuisine(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "com.meeteat.model.Preference.Cuisine[ id=" + getId() + " ]";
    }

}