package hu.progmasters.ujratervezes.week16.dailybugle.service;


import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.JournalistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class JournalistService {

    private JournalistRepository journalistRepository;

    @Autowired
    public JournalistService(JournalistRepository journalistRepository) {
        this.journalistRepository = journalistRepository;
    }

    public List<Journalist> getJournalists() {
        List<Journalist> journalists = journalistRepository.getJournalists();

        return journalists;
    }

    public Journalist findJournalist(int id) {
        Journalist journalist = journalistRepository.findJournalist(id);

        return journalist;
    }

    public boolean createJournalist(JournalistCreateData data) {
        boolean saveSuccessful = journalistRepository.createJournalist(data);

        return saveSuccessful;
    }

    public boolean updateJournalist(int id, JournalistCreateData data) {
        boolean updateSuccessful = journalistRepository.updateJournalist(id, data);

        return updateSuccessful;
    }

    public boolean deleteJournalist(int id) {
        boolean deleteSuccessful = journalistRepository.deleteJournalist(id);


        return deleteSuccessful;
    }
}
