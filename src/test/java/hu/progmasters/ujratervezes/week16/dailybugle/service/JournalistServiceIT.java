package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@AutoConfigureTestDatabase
public class JournalistServiceIT {

    @Autowired
    private JournalistService journalistService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        Journalist journalist = journalistService.findJournalist(1);
        Assertions.assertNull(journalist);
    }

}
