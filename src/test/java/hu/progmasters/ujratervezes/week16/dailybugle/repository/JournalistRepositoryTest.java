package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JournalistRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JournalistRepository journalistRepository;


    @BeforeEach
    public void init(){
        journalistRepository = new JournalistRepository(jdbcTemplate);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS JOURNALIST (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                    "name VARCHAR(45), " +
                                    "email VARCHAR(45), " +
                                    "telephone_number(9), "+
                                    "created TIMESAMP, "+
                                    "edited TIMESAMP, "+
                                    "active TINYINT" +
                                    "(;");
    }

    @AfterEach
    public void destruct() {jdbcTemplate.execute("DROP TABLE IF EXISTS JOURNALIST");}


}
