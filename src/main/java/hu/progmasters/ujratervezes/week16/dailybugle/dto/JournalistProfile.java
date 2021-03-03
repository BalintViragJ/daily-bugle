package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import java.util.List;

public class JournalistProfile {

    private JournalistCreateData journalistProfile;
    private List<ArticleLister> articleListOfJournalist;

    public JournalistProfile() {
    }

    public JournalistCreateData getJournalistProfile() {
        return journalistProfile;
    }

    public void setJournalistProfile(JournalistCreateData journalistProfile) {
        this.journalistProfile = journalistProfile;
    }

    public List<ArticleLister> getArticleListOfJournalist() {
        return articleListOfJournalist;
    }

    public void setArticleListOfJournalist(List<ArticleLister> articleListOfJournalist) {
        this.articleListOfJournalist = articleListOfJournalist;
    }
}
