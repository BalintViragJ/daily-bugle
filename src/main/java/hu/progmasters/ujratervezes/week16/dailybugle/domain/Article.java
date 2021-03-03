package hu.progmasters.ujratervezes.week16.dailybugle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Timestamp;

public class Article {

        private int id;
        @JsonIgnore
        private Journalist journalist;
        private String journalistName;
        private String title;
        private String synopsis;
        private String text;
        private Timestamp created;
        private Timestamp edited;
        private boolean active;

    public Article() {

    }


    public Article(int id, String journalistName, String title, String synopsis, String text, Timestamp created, Timestamp edited, boolean active) {
        this.id = id;
        this.journalistName = journalistName;
        this.title = title;
        this.synopsis = synopsis;
        this.text = text;
        this.created = created;
        this.edited = edited;
        this.active = active;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Journalist getJournalist() {
        return journalist;
    }

    public void setJournalist(Journalist journalist) {
        this.journalist = journalist;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
