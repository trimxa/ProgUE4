package at.ac.fhcampuswien.fhmdb.statePattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;

public class MovieSort {

    private State state;

    public MovieSort(){
        this.state = new UnsortedState();
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

    public void sortMovies(ObservableList<Movie> movieList){
        state.sort(movieList);
    }
}
