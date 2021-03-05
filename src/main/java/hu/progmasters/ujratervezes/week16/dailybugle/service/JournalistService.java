package hu.progmasters.ujratervezes.week16.dailybugle.service;


import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.JournalistRepository;
import hu.progmasters.ujratervezes.week16.dailybugle.telephones.TelephonesOfJournalist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class JournalistService {

    private JournalistRepository journalistRepository;

    private TelephonesOfJournalist telephonesOfJournalist;

    public JournalistService(JournalistRepository journalistRepository) {
    }

    @Autowired
    public JournalistService(JournalistRepository journalistRepository, TelephonesOfJournalist telephonesOfJournalist) {
        this.journalistRepository = journalistRepository;
        this.telephonesOfJournalist = telephonesOfJournalist;
    }

    public List<Journalist> getJournalists() {
        List<Journalist> journalists = journalistRepository.getJournalists();

        return journalists;
    }

    public JournalistProfile findJournalist(int id) {
        List<ArticleLister> articleListers = journalistRepository.getArticleOfJournalist(id);

        return journalistRepository.findJournalist(id, articleListers);
    }

    public void saveTelephoneNumbers() {
        telephonesOfJournalist.writePhoneNumber();
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
