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

    @GetMapping("/{id}")
    public ResponseEntity<Reader> findReader(@PathVariable int id) {
        Reader reader = readerService.findReader(id);

        if (reader != null) {
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public static ResponseEntity<Void> createReader(@RequestBody ReaderCreateData data) {
        boolean saved = ReaderService.createReader(data);

        if (saved) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public static ResponseEntity<Void> deleteReader(@PathVariable int id) {
        boolean deleted = ReaderService.deleteReader(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public static ResponseEntity<Void> updateReader(@PathVariable int id, @RequestBody ReaderCreateData data){
        boolean updated  = ReaderService.updateReader(id, data);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
