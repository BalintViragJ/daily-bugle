package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import org.springframework.beans.factory.annotation.Autowired;
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
            journalist.setTelephoneNumber(resultSet.getInt("telephoneNumber"));
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
            journalist.setTelephoneNumber(resultSet.getInt("telephoneNumber"));
            return journalist;
        });
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean createJournalist(JournalistCreateData data) {

        return false;
    }

    public boolean updateJournalist(int id, JournalistCreateData data) {

        return false;
    }

    public boolean deleteJournalist(int id) {

        return false;
    }
}
