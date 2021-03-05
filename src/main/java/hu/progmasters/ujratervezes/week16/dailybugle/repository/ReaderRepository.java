package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Reader;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ReaderCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReaderRepository {
private final JdbcTemplate jdbcTemplate;

@Autowired
public ReaderRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

public List<Reader> getReaders(){
    String sql = "SELECT * FROM reader";
    return jdbcTemplate.query(sql,(resultSet,rowNumber) -> {
        Reader reader = new Reader();
        reader.setId(resultSet.getInt("id"));
        reader.setUsername(resultSet.getString("username"));
        reader.setEmail(resultSet.getString("email"));
        reader.setCreated(resultSet.getString("created"));
        reader.setEdited(resultSet.getString("edited"));
        reader.setActive(resultSet.getBoolean("active"));

        return reader;});
    }

    public Reader findReaders(int id) {
        try {
            String sql = "SELECT * FROM reader WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNumber) -> {
                Reader reader = new Reader();
                reader.setId(resultSet.getInt("id"));
                reader.setUsername(resultSet.getString("username"));
                reader.setEmail(resultSet.getString("email"));
                reader.setCreated(resultSet.getString("created"));
                reader.setEdited( resultSet.getString("edited"));
                reader.setActive(resultSet.getBoolean("active"));

                return reader;
            });
        } catch (EmptyResultDataAccessException e){
            return null;
                }
            }
            public boolean createReader(ReaderCreateData data){
        String sql = "INSERT INTO reader (email, username, created) VALUES (?, ?, NOW())";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getEmail(), data.getUsername(), data.getCreated());
        return true;
        } catch (DataAccessException e){

            return false;}
    }

    public boolean updateReader(int id, ReaderCreateData data) {
        String sql = "UPDATE reader SET email = ?, name = ?, edited = NOW(); WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    data.getEmail(), data.getUsername(), data.getEdited(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteReader(int id) {
        String sql = "DELETE FROM reader WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}



