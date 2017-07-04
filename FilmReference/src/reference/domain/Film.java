/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bgrec
 */
public class Film {
    private String name;
    //private List<Rating> ratings;

    public Film(String name) {
        this.name = name;
        //this.ratings = new ArrayList<Rating>();
    }
    
    //public void addRating(Rating rating) {
    //    this.ratings.add(rating);
    //}

    //public List<Rating> getRatings() {
    //    return ratings;
   // }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Film other = (Film) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
