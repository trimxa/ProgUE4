package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.observer.Observable;
import at.ac.fhcampuswien.fhmdb.observer.ObservableMessages;
import at.ac.fhcampuswien.fhmdb.observer.Observer;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class WatchlistRepository implements Observable {
    private static WatchlistRepository instance;

    Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    // Methode zur Rückgabe der Singleton-Instanz der Klasse
    public static WatchlistRepository getInstance() throws DataBaseException {
        if(instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }

    public int removeFromWatchlist(String apiId) throws DataBaseException {
        try {
            return dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    // checks if movie is in watchlist
    public boolean isOnWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            return dao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException("Error while checking if movie is on watchlist");
        }
    }

    public void addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // adds movie ONLY if not there already
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                dao.create(movie);
                // Aktualisiere den Observer und sende eine hinzugefügt-Nachricht
                updateObserver(ObservableMessages.ADDED);
            } else {
                updateObserver(ObservableMessages.ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    // implement observer pattern (adding observer)
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    // refreshes observer with message
    @Override
    public void updateObserver(ObservableMessages message) {
        for(Observer observer : this.observers){
            observer.update(message);
        }
    }
}
