package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Reader;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ReaderCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

        @Service
        public class ReaderService {
            private static ReaderRepository readerRepository;

            @Autowired
        public ReaderService(ReaderRepository readerRepository){this.readerRepository =readerRepository;}

        public List<Reader> getReaders(){
                List<Reader> readers = readerRepository.getReaders();
                return readers;
        }

        public Reader findReader(int id){
            Reader reader = readerRepository.findReaders(id);
            return reader;
        }
        public static boolean createReader(ReaderCreateData data){
            boolean saved = readerRepository.createReader(data);
            return  saved;
        }
            public static boolean updateReader(int id, ReaderCreateData data) {
                boolean updated = readerRepository.updateReader(id, data);


                return updated;
            }

            public static boolean deleteReader(int id) {
                boolean deleteSuccessful = readerRepository.deleteReader(id);


                return deleteSuccessful;
            }
        }