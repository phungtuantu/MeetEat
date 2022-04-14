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

    public Cuisine() {
    }

    public Cuisine(String name) {
        super(name);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "com.meeteat.model.Preference.Cuisine[ id=" + getId() + " ]";
    }

}
