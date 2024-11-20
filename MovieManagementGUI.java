package finalProj;

import javax.swing.*;
import java.awt.*;

public class MovieManagementGUI extends JFrame {
    private MovieCollection movieCollection;
    public MovieManagementGUI() {
        movieCollection = new MovieCollection(100);         //example size

        setTitle("Movie Management System");            //main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLayout(new BorderLayout());

        JButton addMovieButton = new JButton("Add Movie");              //button for main features
        Font bigFont = addMovieButton.getFont().deriveFont((float)20);
        addMovieButton.setPreferredSize(new Dimension(300, 300));
        addMovieButton.setFont(bigFont);
        JButton searchMovieButton = new JButton("Search Movie"); 
        searchMovieButton.setFont(bigFont);
        JButton rateMovieButton = new JButton("Rate Movie");
        rateMovieButton.setFont(bigFont);
        JButton listMoviesButton = new JButton("List All Movies");
        listMoviesButton.setFont(bigFont);
        JButton removeMovieButton = new JButton("Remove Movie");
        removeMovieButton.setFont(bigFont);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(bigFont);

        JPanel buttonPanel = new JPanel();                                          //adding panel buttons
        buttonPanel.setLayout(new GridLayout(1, 6, 10, 10));
        buttonPanel.add(addMovieButton);
        buttonPanel.add(searchMovieButton);
        buttonPanel.add(rateMovieButton);
        buttonPanel.add(listMoviesButton);
        buttonPanel.add(removeMovieButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);                //adds panel

        JTextArea displayArea = new JTextArea();                //display area
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        addMovieButton.addActionListener(e -> showAddMovieDialog(displayArea));             //action listeners
        searchMovieButton.addActionListener(e -> showSearchMovieDialog(displayArea));
        rateMovieButton.addActionListener(e -> showRateMovieDialog(displayArea));
        listMoviesButton.addActionListener(e -> listAllMovies(displayArea));
        removeMovieButton.addActionListener(e -> showRemoveMovieDialog(displayArea));
        exitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }

    private void showAddMovieDialog(JTextArea displayArea) {
        JTextField titleField = new JTextField();
        JTextField directorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField genreField = new JTextField();

        Object[] message = {
            "Title:", titleField, "Director:", directorField, "Year:", yearField, "Genre:", genreField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Movie", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String director = directorField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            Movie movie = new Movie(title, director, year, genre);
            movieCollection.addMovie(movie);
            displayArea.append("Movie added successfully!\n");
        }
    }

    private void showSearchMovieDialog(JTextArea displayArea) {
        // Search options
        String[] searchOptions = {"Title", "Director", "Genre"};
        String selectedOption = (String) JOptionPane.showInputDialog(
            this, "Search by:", "Search Movie",
            JOptionPane.PLAIN_MESSAGE,
            null,
            searchOptions,
            searchOptions[0]
        );
    
        if (selectedOption != null) {
            String searchTerm = JOptionPane.showInputDialog(
                this,
                "Enter " + selectedOption + ":", "Search Input",
                JOptionPane.PLAIN_MESSAGE
            );
    
            if (searchTerm != null) {
                String result = "";
                switch (selectedOption) {
                    case "Title" -> result = movieCollection.searchByTitle(searchTerm);
                    case "Director" -> result = movieCollection.searchByDirector(searchTerm);
                    case "Genre" -> result = movieCollection.searchByGenre(searchTerm);
                }

                displayArea.setText(result);
            }
        }
    }    

    private void showRateMovieDialog(JTextArea displayArea) {
        String title = JOptionPane.showInputDialog(this, "Enter the title of the movie to rate:");
        if (title != null) {
            String ratingInput = JOptionPane.showInputDialog(this, "Enter your rating (1-5):");
            int rating = Integer.parseInt(ratingInput);
            movieCollection.rateMovie(title, rating);
            displayArea.append("Rated " + title + " with " + rating + " stars.\n");
        }
    }

    private void showRemoveMovieDialog(JTextArea displayArea) {
        String title = JOptionPane.showInputDialog(this, "Enter the title of the movie to remove:");
        if (title != null) {
            movieCollection.removeMovie(title);
            displayArea.append("Removed movie: " + title + "\n");
        }
    }

    private void listAllMovies(JTextArea displayArea) {
        displayArea.setText("");
        displayArea.append("All Movies in Collection:\n");
        Movie[] movies = movieCollection.getMovies();
        for (Movie movie : movies) {
            if (movie != null) {
                displayArea.append(movie.toString());
            }
        }
    }

    public static void main(String[] args) {
        new MovieManagementGUI();
    }
}
