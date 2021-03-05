package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class JournalistServiceIT {

    @Autowired
    private JournalistService journalistService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        JournalistProfile journalistProfile = journalistService.findJournalist(1);
        Assertions.assertNull(journalistProfile);
    }

}