import java.util.*;

public class Movie {
	 private String title;         // the movie title
	    private List<String> cast;  // the list of actors in the movie
	    
	    /**
	     * Constructs a movie with the given title and an empty cast.
	     * 
	     * @param title the title of this movie
	     */
	    public Movie(String title) {
	        this.title = title;
	        cast = new ArrayList<String>();
	    }
	    
	    /**
	     * Returns the title of this movie.
	     * 
	     * @return the title of this movie
	     */
	    public String getTitle() {
	        return title;
	    }
	    
	    /**
	     * Returns the cast of this movie (i.e., the list of actors in this movie).
	     * 
	     * @return the cast for this movie
	     */
	    public List<String> getCast() {
	        return cast;
	    }
}
