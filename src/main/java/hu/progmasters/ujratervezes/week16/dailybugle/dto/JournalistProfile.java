package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import java.util.List;

public class JournalistProfile {

    private JournalistCreateData journalistData;
    private List<ArticleLister> articleListOfJournalist;

    public JournalistProfile() {
    }

    public JournalistCreateData getJournalistData() {
        return journalistData;
    }

    public void setJournalistData(JournalistCreateData journalistData) {
        this.journalistData = journalistData;
    }

    public List<ArticleLister> getArticleListOfJournalist() {
        return articleListOfJournalist;
    }

    public void setArticleListOfJournalist(List<ArticleLister> articleListOfJournalist) {
        this.articleListOfJournalist = articleListOfJournalist;
    }
}
