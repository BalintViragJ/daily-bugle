package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;

import java.sql.Timestamp;

public class ArticleCreateData {


    private int journalistId;
    private String title;
    private String synopsis;
    private String text;
    private double rating;

    public int getJournalistId() {
        return journalistId;
    }

    public void setJournalistId(int journalistId) {
        this.journalistId = journalistId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
