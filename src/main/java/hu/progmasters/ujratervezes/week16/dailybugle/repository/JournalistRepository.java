package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalistRepository {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JournalistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Journalist> getJournalists() {
        String sql = "SELECT * FROM journalist";
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            Journalist journalist = new Journalist();
            journalist.setName(resultSet.getString("name"));
            journalist.setAddress(resultSet.getString("address"));
            journalist.setEmail(resultSet.getString("email"));
            journalist.setTelephoneNumber(resultSet.getInt("telephone_number"));
            return journalist;
        });
    }

    public Journalist findJournalist(int id) {
        try {
            String sql = "SELECT * FROM journalist WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNumber) -> {
                Journalist journalist = new Journalist();
                journalist.setName(resultSet.getString("name"));
                journalist.setAddress(resultSet.getString("address"));
                journalist.setEmail(resultSet.getString("email"));
                journalist.setTelephoneNumber(resultSet.getInt("telephone_number"));
                return journalist;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean createJournalist(JournalistCreateData data) {

        String sql = "INSERT INTO journalist (name, address, email, telephone_number, created, active) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getName(), data.getAddress(), data.getEmail(), data.getTelephoneNumber(), data.getCreated(), data.isActive());
            //Létrehozás+ utolsó módosítás
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean updateJournalist(int id, JournalistCreateData data) {
        String sql;
        sql = "UPDATE journalist SET name = ?, address = ?, email = ?, telephone_number = ?, edited = ?, active = ?  WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    data.getName(), data.getAddress(), data.getEmail(), data.getTelephoneNumber(), data.getEdited(), data.isActive(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteJournalist(int id, JournalistCreateData data) {
        String sql = "UPDATE journalist SET name = ?, address = ?, email = ?, telephone_number = ?, edited = ?, active = ?  WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    data.getName(), null, null, null, data.getEdited(), data.isActive(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
