/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.model.Preference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yousr
 */
@Entity
public class Diet extends PreferenceTag implements Serializable {

    public Diet(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Preference.Diet[ id=" + getId() + " ]";
    }
    
}
