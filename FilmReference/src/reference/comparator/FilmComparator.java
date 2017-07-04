/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference.comparator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import reference.domain.Film;
import reference.domain.Rating;

/**
 *
 * @author bgrec
 */
public class FilmComparator implements Comparator<Film> {

    private Map<Film, List<Rating>> ratings;
    
    public FilmComparator(Map<Film, List<Rating>> ratings) {
        this.ratings = ratings;
    }
    private int average(Film film) {
        int sum;
        sum = 0;
        List<Rating> ratings = this.ratings.get(film);
        for (Rating r : ratings) {
            sum += r.getValue();
        }
        return sum/ratings.size();
    }
     
    
    @Override
    public int compare(Film o1, Film o2) {
         if (average(o2) - average(o1) == 0) {
             return o2.getName().compareTo(o1.getName());
         }
        return average(o2) - average(o1);
     }
    
}
