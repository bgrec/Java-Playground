/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import reference.comparator.FilmComparator;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;

/**
 *
 * @author bgrec
 */
public class Reference {

    private RatingRegister register;

    public Reference(RatingRegister register) {
        this.register = register;
    }

    public int similarityCalc(Person a, Person b) {
        Map<Film, Rating> aRatings = register.getPersonalRatings(a);
        Map<Film, Rating> bRatings = register.getPersonalRatings(b);
        int similarity;
        similarity = 0;
        for (Film f : aRatings.keySet()) {
            if (bRatings.containsKey(f)) {
                similarity += aRatings.get(f).getValue() * bRatings.get(f).getValue();
            }
        }
        return similarity;
    }

    public Person similarTo(Person person) {
        Person ret;
        List<Person> temp = register.reviewers();
        if (temp.contains(person) && temp.size() >= 2) {
            temp.remove(person);
        } else {
            return null;
        }

        int similarity = similarityCalc(person, temp.get(0));
        ret = temp.get(0);
        for (Person p : temp) {
            if (similarityCalc(person, p) > similarity) {
                similarity = similarityCalc(person, p);
                ret = p;
            }
        }
        return ret;
    }

    public Film recommendFilm(Person person) {
        if (!register.reviewers().contains(person)) {
            Map<Film, List<Rating>> filmRatings = register.filmRatings();
            List<Film> films = new ArrayList<Film>();
            for (Film f : filmRatings.keySet()) {
                films.add(f);
            }
            films.sort(new FilmComparator(filmRatings));
            return films.get(0);
        } else {
            if (register.getPersonalRatings(person).isEmpty()) {
                return null;
            }
            if (similarTo(person) != null) {
                Person similar = similarTo(person);
                Film retFilm = null;
                for (Film f : register.getPersonalRatings(similar).keySet()) {
                    if (!register.getPersonalRatings(person).containsKey(f)) {
                        if (retFilm == null) {
                            retFilm = f;
                        }
                        if (register.getPersonalRatings(similar).get(f).getValue()
                                > register.getPersonalRatings(similar).get(retFilm).getValue()) {
                            retFilm = f;
                        }
                    }
                }
                return retFilm;
            }
        }
        return null;
    }
}
