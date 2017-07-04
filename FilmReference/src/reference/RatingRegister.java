/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;
import reference.Reference;

/**
 *
 * @author bgrec
 */
public class RatingRegister {

    private Map<Film, List<Rating>> filmRatingsMap;
    private Map<Person, Map<Film, Rating>> personsRatingsMap;

    public RatingRegister() {
        this.filmRatingsMap = new HashMap<Film, List<Rating>>();
        this.personsRatingsMap = new HashMap<Person, Map<Film, Rating>>();
    }

    public void addRating(Film film, Rating rating) {
        if (this.filmRatingsMap.containsKey(film)) {
            this.filmRatingsMap.get(film).add(rating);
        } else {
            this.filmRatingsMap.put(film, new ArrayList<Rating>());
            this.filmRatingsMap.get(film).add(rating);
        }
    }

    public List<Rating> getRatings(Film film) {
        return this.filmRatingsMap.get(film);
    }

    public Map<Film, List<Rating>> filmRatings() {
        return this.filmRatingsMap;
    }

    public void addRating(Person person, Film film, Rating rating) {
        if (this.personsRatingsMap.containsKey(person)) {
            if (!this.personsRatingsMap.get(person).containsKey(film)) {
                this.personsRatingsMap.get(person).put(film, rating);
                addRating(film, rating);
            }
        } else {
            this.personsRatingsMap.put(person, new HashMap<Film, Rating>());
            this.personsRatingsMap.get(person).put(film, rating);
            addRating(film, rating);
        }
    }

    public Rating getRating(Person person, Film film) {
        if (this.personsRatingsMap.containsKey(person)) {
            if (this.personsRatingsMap.get(person).containsKey(film)) {
                return this.personsRatingsMap.get(person).get(film);
            }
        }
        return Rating.NOT_WATCHED;
    }

    public Map<Film, Rating> getPersonalRatings(Person person) {
        if (this.personsRatingsMap.containsKey(person)) {
            return this.personsRatingsMap.get(person);
        }
        return new HashMap<Film, Rating>();
    }

    public List<Person> reviewers() {
        ArrayList<Person> persons = new ArrayList<Person>();
        for (Person p : this.personsRatingsMap.keySet()) {
            persons.add(p);
        }
        return persons;
    }
}
