package hu.progmasters.ujratervezes.week16.dailybugle.controller;

import hu.progmasters.ujratervezes.week16.dailybugle.repository.ReaderRepository;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ReaderCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.domain.Reader;
import hu.progmasters.ujratervezes.week16.dailybugle.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/reader")
public class ReaderController {

    public ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService){
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getReaders(){

        List<Reader> readers = readerService.getReaders();

        return new ResponseEntity<>(readers, HttpStatus.OK);

    }
}
