import java.util.*;
import java.io.*;

/**
 * Database that stores objects which contain the title of a movie
 * and a list of that movie's cast.
 *
 */

public class MovieDB {
/**
 * Constructs an empty database that can hold objects
 */
	
	private ArrayList<Movie> movieDB = new ArrayList<Movie>();
	

/**
 * Add movie with title t to the end of the database.
 * Return if movie t already in database.
 * 
 * @param t title of movie
 * @param movieDB 
 */
	public void addMovie(String t){
		//make sure parameter is not null and has correct capitalization
		t = validateString(t);
		//exception thrown if database is empty
		//can skip checking if movie in database if it is empty
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//check if movie already in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//return if movie already in database
				if (tempTitle.equals(t)){
					return;
				}
			}
		} catch (NullPointerException ex){}
		//movie is not in database and needs to be added
		Movie movie = new Movie(t);
		movieDB.add(movie);
	}
	
/**
 * Add actor with name n to movie with title t. If n already in cast for movie
 * with title t return. Throw java.lang.IllegalArgumentException if movie with
 * title t is not in database.
 * 
 * @param n name of actor
 * 
 * @param t title of move
 */
	public void addActor(String n, String t){
		//make sure parameter is not null and has correct capitalization
		n = validateString(n);
		t = validateString(t);
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//look for movie with title t in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//add actor if movie in database
				if (tempTitle.equals(t)){
					List<String> cast = tempMovie.getCast();
					//check if actor n already in movie with title t
					try{
						Iterator<String> itr2 = cast.iterator();
						//check if actor already in database
						while (itr2.hasNext()){
							//get name of next actor
							String tempActor = itr2.next();
							//return if actor n already in movie with title t
							if (tempActor.equals(n)){
								return;
							}
						}
					} catch (NullPointerException ex){}
					cast.add(n);
					return;
				}
			}
		} catch (NullPointerException ex){}
		//movie with title t not found in database, throw exception
		throw new IllegalArgumentException();
	}
	
/**
 * Remove movie with title t from database. Return false if movie not in database
 * otherwise return true.
 * 
 * @param t title of movie
 * 
 * @return false if movie is not in database, true if movie removed from database
 */
	public boolean removeMovie(String t){
		//make sure parameter is not null and has correct capitalization
		t = validateString(t);
		//exception thrown if database is empty
		//movie will not be in database
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//check if movie in database
			int i = 0;
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//remove movie if it is in database
				if (tempTitle.equals(t)){
					movieDB.remove(i);
					return true;
				}
				i++;
			}
		} catch (NullPointerException ex){}
		return false;
	}
	
/**
 * Return true iff a movie with title t is in database.
 * 
 * @param t title of movie
 * 
 * @return true iff movie with title t in database
 */
	public boolean containsMovie(String t){
		//make sure parameter is not null and has correct capitalization
		t = validateString(t);
		//exception thrown if database is empty
		//movie will not be in database if exception thrown
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//check if movie in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//return true if movie in database
				if (tempTitle.equals(t)){
					return true;
				}
			}
		} catch (NullPointerException ex){}
		return false; //movie not in database
	}
	
/**
 * Returns true iff actor with name n appears in at least one movie in database
 * 
 * @param n name of actor
 * 
 * @return true iff actor with name n appears in at least one movie in database
 */
	public boolean containsActor(String n){
		//make sure parameter is not null and has correct capitalization
		n = validateString(n);
		//exception thrown if database is empty
		//actor not in database if exception thrown
				try{
					Iterator<Movie> itr = movieDB.iterator();
					//search movies in database for actor n
					while (itr.hasNext()){
						//get cast of next movie in database
						Movie tempMovie = itr.next();
						List<String> cast = tempMovie.getCast();
						//check if actor n in cast
						try{
							Iterator<String> itr2 = cast.iterator();
							while (itr2.hasNext()){
								//get name of next actor
								String tempActor = itr2.next();
								//actor n in cast
								if (tempActor.equals(n)){
									return true;
								}
							}
						} catch (NullPointerException ex){}
						//actor n not in database
						return false;
					}
				} catch (NullPointerException ex){}
				//no movies in database
				return false;
			}
	
/**
 * Returns true iff actor n is in movie with title t. If movie with title t not
 * in database returns false.
 * 
 * @param n name of actor
 * 
 * @param t title of movie
 * 
 * @return true iff actor n is in movie with title t. False if movie with title
 * t not in database
 */
	public boolean isCast(String n, String t){
		//make sure parameter is not null and has correct capitalization
		n = validateString(n);
		t = validateString(t);
		//exception thrown if database is empty
		//actor not in cast if exception thrown
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//look for movie with title t in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//if movie in database check for actor
				if (tempTitle.equals(t)){
					List<String> cast = tempMovie.getCast();
					//check if actor n in movie with title t
					try{
						Iterator<String> itr2 = cast.iterator();
						while (itr2.hasNext()){
							//get name of next actor
							String tempActor = itr2.next();
							//actor n in movie with title t
							if (tempActor.equals(n)){
								return true;
							}
						}
					} catch (NullPointerException ex){}
					//actor n not found in movie with title t
					return false;
				}
			}
		} catch (NullPointerException ex){}
		//movie with title t not found in database
		return false;
	}

	
/**
 * Return cast for movie with title t. Returns null if movie with title t not
 * in database.
 * 
 * @param t title of movie
 * 
 * @return cast for movie with title t. Null if movie with title t not in database
 */
	public List<String> getCast(String t){
		//make sure parameter is not null and has correct capitalization
		t = validateString(t);
		//exception thrown if database is empty
		//movie not in database if exception thrown
		try{
			Iterator<Movie> itr = movieDB.iterator();
			//look for movie with title t in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String tempTitle = tempMovie.getTitle();
				//if movie in database return list of actors
				if (tempTitle.equals(t)){
					List<String> cast = tempMovie.getCast();
					return cast;
				}
			}
		} catch (NullPointerException ex){}
		//movie with title t not found in database
		return null;	
	}
	
/**
 * Returns list of movies in which actor with name n is cast. Returns null if
 * actor with name n is not in database
 * 
 * @param n name of actor
 * 
 * @return list of movies in which actor with name n is cast. Null if actor
 * with name n not in database	
 */
	public List<String> getMovies(String n){
		//make sure parameter is not null and has correct capitalization
		n = validateString(n);
		try{
			Iterator<Movie> itr = movieDB.iterator();
			List<String> movies = new ArrayList<String>();
			//look for movie with title t in database
			while (itr.hasNext()){
				//get title of next movie in database
				Movie tempMovie = itr.next();
				String movieTitle = tempMovie.getTitle();
				//get cast of movie
				List<String> cast = tempMovie.getCast();
					//check if actor n in movie
					try{
						Iterator<String> itr2 = cast.iterator();
						while (itr2.hasNext()){
							//get name of next actor
							String tempActor = itr2.next();
							//actor n in movie with title t
							if (tempActor.equals(n)){
								movies.add(movieTitle);
							}
						}
					} catch (NullPointerException ex){}
			}
			return movies;
		} catch (NullPointerException ex){}
		//actor with name n not in database
		return null;
	}
	
/**
 * Returns iterator over the Movie objects in database. Movies returned in
 * order they were added to the database.
 * 
 * @return iterator over the Movie objects in database
 */
	public Iterator<Movie> iterator(){
		return movieDB.iterator();
	}
	
/**
 * Return the number of movies in database
 * 
 * @return number of movies in database
 */
	public int size(){
		return movieDB.size();
	}
	
/**
 * Remove actor with name n from every movie in which they are cast. Return
 * false if actor with name n not in database, otherwise return true.
 * 
 * @param n name of actor
 * 
 * @return false if actor with name n not in database, otherwise true
 */
	public boolean removeActor(String n){
		//make sure parameter is not null and has correct capitalization
		n = validateString(n);
		//exception thrown if database is empty
		//actor not in database if exception thrown
		boolean flag = false;		
		try{
					Iterator<Movie> itr = movieDB.iterator();
					//search movies in database for actor n
					while (itr.hasNext()){
						//get cast of next movie in database
						Movie tempMovie = itr.next();
						List<String> cast = tempMovie.getCast();
						//check if actor n in cast
						try{
							Iterator<String> itr2 = cast.iterator();
							int i = 0;
							while (itr2.hasNext()){
								//get name of next actor
								String tempActor = itr2.next();
								//actor n in cast
								if (tempActor.equals(n)){
									cast.remove(i);//remove actor n from movie t
									itr2 = cast.iterator();//need to get new iterator
									flag = true;
								}
								i++;
							}
						} catch (NullPointerException ex){}
					}
				} catch (NullPointerException ex){}
				return flag;
	}
	
	private String validateString(String string){
		//throw exception if null is passed
		if (string == ""){
			throw new IllegalArgumentException();
		}
		//change all characters of string to lower case and make character array
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;//keeps track of when white space is found
		//capitalize the first letter and each letter after space
		for (int i = 0; i < chars.length; i++){
			if (!found && Character.isLetter(chars[i])){
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			}
			else if(Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == ','){
				found = false;
			}
		}
		//convert character array back to a string
		String s = new String(chars);
		return s;
	}
	public void readFile(Scanner thefile) {
		while(thefile.hasNextLine()){
		     String input = thefile.nextLine();
		     String[] tokens = input.split("[,]+");
		     String actor = tokens[0].trim();
		     for(int i = 1; i < tokens.length; i++){
			     String movie = tokens[i].trim();
		    	 addMovie(movie);
			     addActor(actor, movie); 
			     //System.out.println(actor + " " + movie + " ");
		     }
		 }
	}
	public PrintWriter writeOutputFile(PrintWriter output){
		movie = 
		Iterator <String> itr = movie.iterator();
		return output;
	}
}
