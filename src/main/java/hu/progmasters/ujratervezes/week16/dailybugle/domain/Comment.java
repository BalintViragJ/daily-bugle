package hu.progmasters.ujratervezes.week16.dailybugle.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {
    private int id;
    private String text;
    private int readerId;
    private int articleId;
    private Timestamp created;
    private Timestamp edited;
    private boolean active;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Comment comment = (Comment) o;
        return id == comment.id &&
                readerId == comment.readerId &&
                articleId == comment.articleId &&
                active == comment.active &&
                Objects.equals(text, comment.text) &&
                Objects.equals(created, comment.created) &&
                Objects.equals(edited, comment.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, readerId, articleId, created, edited, active);
    }
}
