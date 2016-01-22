import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MovieBDMain {
	 public static void main(String[] args) throws FileNotFoundException{

	        // *** Add code for steps 1 - 3 of the main method ***
		 	MovieDB myMovieDB = new MovieDB();
		 	Iterator itr = myMovieDB.iterator();
		 
		 	if(args.length != 1){
		 		System.out.print( "Usage: java MovieDBMain FileName");
		 		System.exit(0);
		 	}
		 	//the scanner that will read the input file
			Scanner inputFile = null;
			//try block to make sure the File exists
		 	try{
				//get the scanner object of the file
				inputFile = new Scanner (new File(args[0]));
			} catch (FileNotFoundException exception){
				//if the file does not
				System.out.print( "Error: cannot access input file");
				System.exit(-1);
			}
			//step 3 calls method to get all the information from the file
		 	myMovieDB.readFile(inputFile);
			
	        Scanner stdin = new Scanner(System.in);  // for reading console input
	        boolean done = false;
	        while (!done) {
	            System.out.print("Enter option ( cdprswx ): ");
	            String input = stdin.nextLine();

	            // only do something if the user enters at least one character
	            if (input.length() > 0) {
	                char choice = input.charAt(0);  // strip off option character
	                String remainder = "";  // used to hold the remainder of input
	                if (input.length() > 1) { // if there is an argument
	                    // trim off any leading or trailing spaces
	                    remainder = input.substring(1).trim(); 

		                switch (choice) { // the commands that have arguments
		
		                case 'c':
		                	if(!myMovieDB.containsMovie(remainder)){
		                		System.out.print("movie not found");
		                	}
		                	else{
		                		if(!myMovieDB.isCast(remainder)){//**checking if there is no cast to movie**
		                			System.out.print("none");
		                		}
		                		else{
		                			System.out.print(myMovieDB.getCast(remainder));
		                		}
		                	}
		                    break;
		
		                case 'p':
		                	if(!myMovieDB.containsActor(remainder)){
		                		System.out.print("actor not found");
		                	}
		                	else{
		                		//print movies the actor is in
		                		System.out.print(myMovieDB.getMovies(remainder));
		                	}
		                    break;
		
		                case 'r':
		                	if(!myMovieDB.containsMovie(remainder)){
		                		System.out.print("movie not found");
		                	}
		                	else{
		                	myMovieDB.removeMovie(remainder);
		                	System.out.print("movie removed");
		                	}
		                	break;
		
		                case 's':
		                    // The following code reads in a comma-separated sequence 
		                    // of strings.  If there are exactly two strings in the 
		                    // sequence, the strings are assigned to name1 and name2.
		                    // Otherwise, an error message is printed.
		                    String[] tokens = remainder.split("[,]+");
		                    if (tokens.length != 2) {
		                        System.out.println("need to provide exactly two names");
		                    }
		                    else {
		                        String name1 = tokens[0].trim();
		                        String name2 = tokens[1].trim();
		                        
		                        //display none if one or both actors not in database
		                        if(!myMovieDB.containsActor(name1) || !myMovieDB.containsActor(name2)){
		                        	System.out.println("none");
		                        }
		                        else{
			                        //get list of movies in which first actor is cast
			                        List<String> name1Movies = myMovieDB.getMovies(name1);
			                        ArrayList<String> sharedMovies = new ArrayList<String>();
			                        //check if second actor is also cast in movie
			                        Iterator<String> itr1 = name1Movies.iterator();
			                        while(itr1.hasNext()){
			                        	String tempMovie = itr1.next();
			                        	if(myMovieDB.isCast(name2, tempMovie)){
			                        		sharedMovies.add(tempMovie);
			                        	}//end if
			                        }//end while loop
			                        //print out list of shared movies
			                        if (sharedMovies.isEmpty()){
			                        	System.out.println("none");
			                        }
			                        else{
			                        	for (int i = 0; i < sharedMovies.size(); i++){
			                        		if (i < sharedMovies.size()-1){
			                        			System.out.print(sharedMovies.get(i) + ", " );
			                        		}
			                        		else{
			                        			System.out.print(sharedMovies.get(i));
			                        		}
			                        	}//end for loop
			                        	System.out.println();
			                        }//end else
		                        }//end else
		                    }
		                    break;
		
		                case 'w':
		                	if(!myMovieDB.containsActor(remainder)){//see it actor is in database
		                		System.out.print("actor not found");
		                	}
		                	else{
		                		myMovieDB.removeActor(remainder);
		                		System.out.print(remainder + "withdrawn from all movies");
		                	}
		                	break;
		
		                default: // ignore any unknown commands
	                    	System.out.println("Incorrect command.");
		                	break;
		                
		                } // end switch
	                } // end if
	                else { //if there is no argument
	                	switch (choice) { // the commands without arguments
	                	
	                	case 'd': 
	                		//find number of unique movies and actors
	                		int uniqueMovies = myMovieDB.size();
	                		int uniqueActors;
	                		//get list of movies
	                		Iterator<Movie> itr0 = myMovieDB.iterator();
	                		ArrayList<String> uniqueMovieList = new ArrayList<String>();
	                		while (itr0.hasNext()){
	                			uniqueMovieList.add((itr0.next()).getTitle());
	                		}
	                		ArrayList<String> uniqueActorList = new ArrayList<String>();
	                		Iterator<String> itr1 = uniqueMovieList.iterator();
	                		while(itr1.hasNext()){
	                			//get cast for movie
	                			List<String> castList = myMovieDB.getCast(itr1.next());
	                			//compare to actors already in database
	                			Iterator<String> itr2 = castList.iterator();
	                			while (itr2.hasNext()){
	                				String tempActor = itr2.next();
	                				//add actor if not already in list
	                				if (!uniqueActorList.contains(tempActor)){
	                					uniqueActorList.add(tempActor);
	                				}
	                			}//end while itr2
	                		}//end while itr1
	                		uniqueActors = uniqueActorList.size();
	                		//print out number of movies and actors
	                		System.out.println("Movies: " + uniqueMovies + ", Actors: " + uniqueActors);
	                		
	                		//find actors/movie
	                		ArrayList<Integer> actorsPerMovie = new ArrayList<Integer>();
	                		Iterator<String> itr3 = uniqueMovieList.iterator();
	                		while (itr3.hasNext()){//get number of actors in each movie
	                			actorsPerMovie.add((myMovieDB.getCast(itr3.next())).size());
	                		}
	                		int mostActors = 0, leastActors, sumActors = 0;
	                		Iterator<Integer> itr4 = actorsPerMovie.iterator();
	                		//find least actors
	                		leastActors = itr4.next();
	                		while (itr4.hasNext()){
	                			int tempNumber = itr4.next();
	                			if (tempNumber < leastActors){
	                				leastActors = tempNumber;
	                			}
	                		}//end itr4 while
	                		//find most actors
	                		Iterator<Integer> itr5 = actorsPerMovie.iterator();
	                		while(itr5.hasNext()){
	                			int tempNumber = itr5.next();
	                			sumActors += tempNumber;
	                			if (tempNumber > mostActors){
	                				mostActors = tempNumber;
	                			}
	                		}//end while itr5
	                		//calculate average number of actors and print
	                		double averageActors = sumActors / (double)actorsPerMovie.size();
	                		System.out.println("# of actors/movie: most " + mostActors + ", least " 
	                						+ leastActors + ", average " + (int)Math.round(averageActors));
	                		
	                		//find movies/actor
	                		ArrayList<Integer> moviesPerActor = new ArrayList<Integer>();
	                		Iterator<String> itr6 = uniqueActorList.iterator();
	                		while (itr6.hasNext()){//get number of movies for each actor
	                			moviesPerActor.add((myMovieDB.getMovies(itr6.next())).size());
	                		}
	                		int mostMovies = 0, leastMovies, sumMovies = 0;
	                		Iterator<Integer> itr7 = moviesPerActor.iterator();
	                		//find least movies
	                		leastMovies = itr7.next();
	                		while (itr7.hasNext()){
	                			int tempNumber = itr7.next();
	                			if (tempNumber < leastMovies){
	                				leastMovies = tempNumber;
	                			}
	                		}//end itr7 while
	                		//find most movies
	                		Iterator<Integer> itr8 = moviesPerActor.iterator();
	                		while(itr8.hasNext()){
	                			int tempNumber = itr8.next();
	                			sumMovies += tempNumber;
	                			if (tempNumber > mostMovies){
	                				mostMovies = tempNumber;
	                			}
	                		}//end while itr8
	                		//calculate average number of movies and print
	                		double averageMovies = sumMovies / (double)moviesPerActor.size();
	                		System.out.println("# of movies/actor: most " + mostMovies + ", least " 
	                						+ leastMovies + ", average " + (int)Math.round(averageMovies));
	                		
	                		//find  most popular movie
	                		ArrayList<String> popularMovies = new ArrayList<String>();
	                		Iterator<String> itr9 = uniqueMovieList.iterator();
	                		//check if actors in movie is equal to most actors in a movie
	                		while (itr9.hasNext()){
	                			String temptitle = itr9.next();
	                			//add to popular list if most actors
	                			if ((myMovieDB.getCast(temptitle)).size() == mostActors){
	                				popularMovies.add(temptitle);
	                			}
	                		}//end itr9 while
	                		//print out list of popular movies
	                		System.out.print("Most popular: ");
	                		Iterator<String> itr10 = popularMovies.iterator();
	                		System.out.print(itr10.next());
	                		while (itr10.hasNext()){
	                			System.out.print(", " + itr10.next());
	                		}
	                		System.out.println(" [" + mostActors + "]");
	                		
	                		//find least popular movie
	                		ArrayList<String> leastPopularMovies = new ArrayList<String>();
	                		Iterator<String> itr11 = uniqueMovieList.iterator();
	                		//check if actors in movie is equal to most actors in a movie
	                		while (itr11.hasNext()){
	                			String temptitle = itr11.next();
	                			//add to popular list if most actors
	                			if ((myMovieDB.getCast(temptitle)).size() == leastActors){
	                				leastPopularMovies.add(temptitle);
	                			}
	                		}//end itr11 while
	                		//print out list of popular movies
	                		System.out.print("Least popular: ");
	                		Iterator<String> itr12 = leastPopularMovies.iterator();
	                		System.out.print(itr12.next());
	                		while (itr12.hasNext()){
	                			System.out.print(", " + itr12.next());
	                		}
	                		System.out.println(" [" + leastActors + "]");
		                    break;
		                    
	                	case 'x':
		                    done = true;
		                    System.out.println("exit");
		                    break;
		                    
	                	default:  // a command with no argument
	                		System.out.println("Incorrect command.");
		                    break;
	                	} // end switch
	                } // end else  
	           } // end if
	        } // end while
	    } // end main
}
