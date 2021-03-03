package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RatingLister {

    private int rating;
    private int articleId;
    @JsonIgnore
    private int time;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
