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

    public List<JournalistProfile> findJournalist(int id) {
        String queryForArticles = "SELECT a.id, a.journalist_id, a.title, a.synopsis, j.name FROM article a JOIN journalist j ON a.journalist_id = j.id WHERE a.journalist_id = ?;";


        try {
            String sql = "SELECT j.id, j.name, j.address, j.email, j.telephone_number, a.id AS article_id, a.title, a.synopsis " +
                    "FROM journalist j JOIN article a ON j.id = a.journalist_id WHERE j.id = ?";
            return jdbcTemplate.query(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                //példányosítás
                JournalistCreateData journalistCreateData = new JournalistCreateData();
                JournalistProfile journalistProfile = new JournalistProfile();
                List<ArticleLister> articleListers = new ArrayList<>();
                ArticleLister articleLister = new ArticleLister();

                //journalist adatainak beállítása
                journalistCreateData.setName(resultSet.getString("name"));
                journalistCreateData.setAddress(resultSet.getString("address"));
                journalistCreateData.setEmail(resultSet.getString("email"));
                journalistCreateData.setTelephoneNumber(resultSet.getString("telephone_number"));

                //cikk adatainak beállítása
                articleLister.setId(resultSet.getInt("article_id"));
                articleLister.setJournalistName("name");
                articleLister.setTitle(resultSet.getString("title"));
                articleLister.setSynopsis(resultSet.getString("synopsis"));
                articleListers.add(articleLister);

                //journalistProfile adatainak beállítása
                journalistProfile.setJournalistData(journalistCreateData);
                journalistProfile.setArticleListOfJournalist(articleListers);

                return journalistProfile;
            }));
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
}
