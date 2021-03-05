package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;

import java.text.DecimalFormat;

public class ArticleLister {

    private int id;
    private String journalistName;
    private String title;
    private String synopsis;
    private double rating;
    @JsonIgnore
    private boolean active;

    //TODO két tizedesjegyre alakítani a rating-et




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;


    }
}
