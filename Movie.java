package finalProj;

public class Movie {
	private String title;
	private String director;
	private int year;
	private String genre;
	private int rating;
	
	//default constructor
	public Movie() {
		this.title = "none";
		this.director = "anonymous";
		//current year
		this.year = 2024;
		this.genre = "none";
		this.rating = 0;
	}
	
	//parameterized constructor
	public Movie(String uT, String uD, int uY, String uG) {
		setTitle(uT);
		setDirector(uD);
		setYear(uY);
		setGenre(uG);
		//default as 0 until given rating by user
		this.rating = 0;
	}
	
	//accessors
	public String getTitle() {
		return this.title;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	//mutator
	public void setTitle(String uT) {
		if(uT != null && !(uT.isEmpty())) {
			this.title = uT;
		}else {
			//false -> default ("none")
			this.title = "none";
		}
	}
	
	public void setDirector(String uD) {
		if(uD != null && !(uD.isEmpty())) {
			this.director = uD;
		}else {
			//false -> default ("anonymous")
			this.director = "anonymous";
		}
	}
	
	public void setYear(int uY) {
		//somewhat realistic range (1888 was when the first movie was made)
		if(uY >= 1888 && uY <= 3000) {
			this.year = uY;
		}else {
			//false -> default (2024)
			this.year = 2024;
		}
	}
	
	public void setGenre(String uG) {
		if(uG != null && !(uG.isEmpty())) {
			this.genre = uG;
		} else {
			//false -> default ("none")
			this.genre = "none";
		}
	}
	
	public void setRating(int uR) {
		if(uR >= 1 || uR <= 5) {
			this.rating = uR;
		}else {
			//false -> default (0)
			this.rating = 0;
		}
	}
	
	/*	format:
	 * 	<<title>>. Directed by <<director>>, <<year>>. 
	 * 	Genre: <<genre>> 
	 * 	Rating: <<rating>>
	 * 
	 * 	example:
	 * 	Avatar. Directed by James Cameron, 2009. 
	 * 	Genre: Sci-Fi 
	 * 	Rating: 4.6
	 */
	public String toString() {
		String line1 = this.title + ". Directed by " + this.director + ", " + this.year + "\n";
		String line2 = "Genre: " + this.genre + "\n";
		String line3 = "Rating: " + this.rating + "\n\n";
		return line1 + line2 + line3;
	}

	public boolean equals(Movie uM) {
		return this.title.equals(uM.title) && this.director.equals(uM.director) && this.year == uM.year && this.genre.equals(uM.genre); 
	}
}
