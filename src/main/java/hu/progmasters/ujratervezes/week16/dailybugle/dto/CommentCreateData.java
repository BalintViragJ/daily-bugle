package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;

import java.sql.Timestamp;
import java.util.Objects;

public class CommentCreateData {
    private String text;
    int readerId;
    int articleId;
    Timestamp created;
    Timestamp edited;
    boolean active;

    public CommentCreateData() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentCreateData that = (CommentCreateData) o;
        return readerId == that.readerId &&
                articleId == that.articleId &&
                active == that.active &&
                Objects.equals(text, that.text) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, readerId, articleId, created, edited, active);
    }
}
