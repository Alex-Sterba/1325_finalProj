package finalProj;

public class MovieCollection {
	private Movie[] movies;
	public static final int DEF_SIZE = 10;
	
	//default constructor
	public MovieCollection() {
		init(DEF_SIZE);
	}

	//parameterized constructor
	public MovieCollection(int size) {
		init(size);
	}
	
	//initialize size of movie collection
	public void init(int size) {
		if(size >= 1) {
			movies = new Movie[size];
		}else {
			movies = new Movie[DEF_SIZE];
		}
	}
	
	public Movie[] getMovies() {
		return movies;
	}
	
	public void addMovie(Movie uM) {
		//making sure theres space AND that you're not adding a null object
		if(uM == null || movies[movies.length - 1] != null) {
			return;
		}

		for(int i = 0; i < movies.length; i++) {
			if(movies[i] != null && movies[i].equals(uM)) {
				return;
			}
			
			if(movies[i] == null) {
				movies[i] = uM;
				break;
			}
		}
	}
	
	public void removeMovie(String uT) {
		int index = -1;
		//searching through movies for the TITLE
		for (int i = 0; i < movies.length; i++) {
			if (movies[i] != null && movies[i].getTitle().equals(uT)) {
				index = i;
				break;
			}
		}
		
		//check if movie title was found in collection
		if (index == -1) {
			return;
		}
		
		for (int i = index; i < movies.length - 1; i++) {
			movies[i] = movies[i + 1];
		}
		
		//remove copy of the end of the string
		movies[movies.length - 1] = null;
	}
	
	public String searchByTitle(String uT) {
		StringBuilder result = new StringBuilder();
		for (Movie movie : movies) {
			if (movie != null && movie.getTitle().equalsIgnoreCase(uT)) {
				result.append(movie.toString()).append("\n");
			}
		}
		return result.length() > 0 ? result.toString() : "No movies found with title: " + uT;
	}
	
	public String searchByDirector(String uD) {
		StringBuilder result = new StringBuilder();
		for (Movie movie : movies) {
			if (movie != null && movie.getDirector().equalsIgnoreCase(uD)) {
				result.append(movie.toString()).append("\n");
			}
		}
		return result.length() > 0 ? result.toString() : "No movies found by director: " + uD;
	}
	
	public String searchByGenre(String uG) {
		StringBuilder result = new StringBuilder();
		for (Movie movie : movies) {
			if (movie != null && movie.getGenre().equalsIgnoreCase(uG)) {
				result.append(movie.toString()).append("\n");
			}
		}
		return result.length() > 0 ? result.toString() : "No movies found in genre: " + uG;
	}	
	
	public void rateMovie(String uT, int uR) {
		for(int i = 0; i < movies.length - 1; i++) {
			if(movies[i].getTitle().equals(uT)) {
				movies[i].setRating(uR);
				return;
			}
		}
	}
	
	public void listAllMovies() {
		for(int i = 0; i < movies.length - 1; i++) {
			if(movies[i] == null) {	
				//reached end of string and thus, end of search
				break;
			}
			
			System.out.println(movies[i].toString());
		}
	}
}
