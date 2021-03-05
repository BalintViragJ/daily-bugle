package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.repository.JournalistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JournalistServiceTest {

    private JournalistService journalistService;

    @Mock
    private JournalistRepository journalistRepository;

    @BeforeEach
    public void init() {
        journalistService = new JournalistService(journalistRepository);
    }


    @Test
    void getJournalists() {
    }

    @Test
    void findJournalist() {
    }

    @Test
    void saveTelephoneNumbers() {
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
}