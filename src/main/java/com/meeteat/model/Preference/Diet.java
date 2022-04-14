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
public class Diet extends PreferenceTag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Diet() {
    }

    public Diet(String name) {
        super(name);
    }
    
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diet)) {
            return false;
        }
        Diet other = (Diet) object;
        return ! ( (this.getId() == null && other.getId() != null) || 
                   (this.getId() != null && !this.getId().equals(other.getId())) );
    }

    @Override
    public String toString() {
        return "com.meeteat.model.Preference.Diet[ id=" + getId() + " ]";
    }
    
}
