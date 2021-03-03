package hu.progmasters.ujratervezes.week16.dailybugle.telephones;

import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.JournalistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class TelephonesOfJournalist {

    String path = "files/phonenumbers/phonenumbers.csv";

        private JournalistRepository journalistRepository;

    @Autowired
    public TelephonesOfJournalist(JournalistRepository journalistRepository) {
        this.journalistRepository = journalistRepository;
    }

    public void writePhoneNumber(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/phonenumbers/phonenumbers.csv"))) {
            List<JournalistCreateData> journalistPhoneNumbers = journalistRepository.listTelephoneNumbers();

            for (JournalistCreateData journalistPhoneNumber : journalistPhoneNumbers) {
                String line = journalistPhoneNumber.getName() + "; " + journalistPhoneNumber.getTelephoneNumber();
                writer.write(line);
                writer.newLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
