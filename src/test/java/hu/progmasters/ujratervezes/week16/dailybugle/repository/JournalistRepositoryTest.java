package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class JournalistRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JournalistRepository journalistRepository;

    @BeforeEach
    public void init(){
        journalistRepository = new JournalistRepository(jdbcTemplate);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS journalist (id INT AOUT_INCREMENT PRIMARY KEY, " +
                                                            "name VARCHAR(45), " +
                                                            "address VARCHAR(45), " +
                                                            "email VARCHAR(45), " +
                                                            "telephone_number VARCHAR(45), " +
                                                            "created TIMESTAMP, " +
                                                            "edited TIMESTAMP, " +
                                                            "active TINYINT);"
        );
    }

    @AfterEach
    public void destruct() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS journalist");
    }


    @Test
    public void getJournalists() {
    }

    @Test
    public void testFindJournalistNoJournalistExistsReturnNull(int id, List<ArticleLister> articleListers) {

        JournalistProfile journalistProfile = journalistRepository.findJournalist(1, articleListers);

        Assertions.assertNull(journalistProfile);
    }

    @Test
    void createJournalist() {
    }

    @Test
    void updateJournalist() {
    }

    @Test
    void deleteJournalist() {
    }

    @Test
    void listTelephoneNumbers() {
    }

    @Test
    void getArticleOfJournalist() {
    }
}