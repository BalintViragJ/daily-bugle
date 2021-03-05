package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Comment;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.CommentCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.List;

import static java.time.LocalDateTime.now;

@Repository
public class CommentRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> getComments() {
        String sql = "SELECT * FROM comment ";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setText(resultSet.getString("text"));
            comment.setReaderId(resultSet.getInt("reader_id"));
            comment.setArticleId(resultSet.getInt("article_id"));
            comment.setCreated(resultSet.getTimestamp("created"));
            comment.setEdited(resultSet.getTimestamp("edited"));
            comment.setActive(resultSet.getBoolean("active"));
            return comment;
        }));
    }

    public Comment findCommentByCommentId(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setText(resultSet.getString("text"));
                comment.setReaderId(resultSet.getInt("reader_id"));
                comment.setArticleId(resultSet.getInt("article_id"));
                comment.setCreated(resultSet.getTimestamp("created"));
                comment.setEdited(resultSet.getTimestamp("edited"));
                comment.setActive(resultSet.getBoolean("active"));
                return comment;
            }));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Comment findCommentByArticleId(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE article_id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setText(resultSet.getString("text"));
                comment.setReaderId(resultSet.getInt("reader_id"));
                comment.setArticleId(resultSet.getInt("article_id"));
                comment.setCreated(resultSet.getTimestamp("created"));
                comment.setEdited(resultSet.getTimestamp("edited"));
                comment.setActive(resultSet.getBoolean("active"));
                return comment;
            }));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Comment findCommentByReaderId(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE reader_id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setText(resultSet.getString("text"));
                comment.setReaderId(resultSet.getInt("reader_id"));
                comment.setArticleId(resultSet.getInt("article_id"));
                comment.setCreated(resultSet.getTimestamp("created"));
                comment.setEdited(resultSet.getTimestamp("edited"));
                comment.setActive(resultSet.getBoolean("active"));
                return comment;
            }));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean saveComment(CommentCreateData data) {
        String sql = "INSERT INTO comment (text, reader_id, article_id, created, edited, active) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getText(), data.getReaderId(), data.getArticleId(),
                    now(), now(), data.isActive());
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteComment(int id) {

        String sql = "UPDATE comment SET edited = ?, active = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, now(), 0, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
