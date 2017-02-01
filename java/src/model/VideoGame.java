package model;

import java.util.Arrays;

/**
 * Created by Lucas Estrella on 2/1/2017.
 */
public class VideoGame {
    private String game, platform;
    private double rating;
    private String[] genre;

    public String getLabel() {
        return  "<b>Game: </b>" + game + "\n"+
                "<b>Platform: </b>" + platform + "\n" +
                "<b>Rating: </b>" + rating + "\n" +
                "<b>Genres: </b>" + Arrays.toString(genre).replace("[","").replace("]","");
    }

    public VideoGame(String game, String platform, double rating, String[] genre) {
        this.game = game;
        this.platform = platform;
        this.rating = rating;
        this.genre = genre;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }
}
