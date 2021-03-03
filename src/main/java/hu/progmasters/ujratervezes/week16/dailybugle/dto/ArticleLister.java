package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;

public class ArticleLister {

    private int id;
    private String journalistName;
    private String title;
    private String synopsis;

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
}
