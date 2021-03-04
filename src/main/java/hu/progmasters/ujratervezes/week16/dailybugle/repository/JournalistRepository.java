package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
            journalist.setTelephoneNumber(resultSet.getString("telephone_number"));
            return journalist;
        });
    }

    public JournalistProfile findJournalist(int id, List<ArticleLister> articleListers) {
        try {
            String sql = "SELECT j.id, j.name, j.address, j.email, j.telephone_number " +
                    "FROM journalist j WHERE j.id = ?";
            JournalistCreateData journalistCreateData = new JournalistCreateData();
            journalistCreateData = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                JournalistCreateData journalistCreateData2 = new JournalistCreateData();

                journalistCreateData2.setName(resultSet.getString("name"));
                journalistCreateData2.setAddress(resultSet.getString("address"));
                journalistCreateData2.setEmail(resultSet.getString("email"));
                journalistCreateData2.setTelephoneNumber(resultSet.getString("telephone_number"));
                return journalistCreateData2;
            }));

            JournalistProfile journalistProfile = new JournalistProfile();
            journalistProfile.setJournalistData(journalistCreateData);
            journalistProfile.setArticleListOfJournalist(articleListers);
            return journalistProfile;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public boolean createJournalist(JournalistCreateData data) {

        String sql = "INSERT INTO journalist (name, address, email, telephone_number, created, active) VALUES (?, ?, ?, ?, now(), TRUE)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getName(), data.getAddress(), data.getEmail(), data.getTelephoneNumber());
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean updateJournalist(int id, JournalistCreateData data) {
        String sql;
        sql = "UPDATE journalist SET name = ?, address = ?, email = ?, telephone_number = ?, edited = now() WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    data.getName(), data.getAddress(), data.getEmail(), data.getTelephoneNumber(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteJournalist(int id) {
        String sql = "UPDATE journalist SET name = 'Névtelen szerző', address = NULL, email = NULL, " +
                "telephone_number = NULL, edited = now(), active = FALSE  WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public List<JournalistCreateData> listTelephoneNumbers() {
        String sql = "SELECT name, telephone_number FROM journalist";

        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            JournalistCreateData journalistCreateData = new JournalistCreateData();
            journalistCreateData.setName(resultSet.getString("name"));
            journalistCreateData.setTelephoneNumber(resultSet.getString("telephone_number"));

            return journalistCreateData;
        }));
    }


    public List<ArticleLister> getArticleOfJournalist(int id) {
        String sql = "SELECT a.id, a.journalist_id, a.title, a.synopsis, a.active, j.name FROM article a JOIN journalist j " +
                        "ON a.journalist_id = j.id WHERE j.id = ?";
        List<ArticleLister> articleListers = new ArrayList<>();

        articleListers.add(jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
            ArticleLister articleLister = new ArticleLister();
            articleLister.setActive(resultSet.getBoolean("active"));
            if (articleLister.isActive()) {
                articleLister.setId((resultSet.getInt("id")));
                articleLister.setJournalistName(resultSet.getString("name"));
                articleLister.setTitle(resultSet.getString("title"));
                articleLister.setSynopsis(resultSet.getString("synopsis"));
                return articleLister;
            }
            return null;
        })));
            return articleListers;
    }
}
