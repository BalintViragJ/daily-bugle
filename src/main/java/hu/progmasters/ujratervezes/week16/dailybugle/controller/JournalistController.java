package hu.progmasters.ujratervezes.week16.dailybugle.controller;


import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.JournalistProfile;
import hu.progmasters.ujratervezes.week16.dailybugle.service.JournalistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journalist")
public class JournalistController {

    private JournalistService journalistService;

    @Autowired
    public JournalistController(JournalistService journalistService) {
        this.journalistService = journalistService;
    }

    @GetMapping
    public ResponseEntity<List<Journalist>> getJournalists() {

        List<Journalist> journalists = journalistService.getJournalists();

        return new ResponseEntity<>(journalists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalistProfile> getJournalist(@PathVariable int id) {
        JournalistProfile journalistProfile = journalistService.findJournalist(id);

        if (journalistProfile != null) {
            return new ResponseEntity<>(journalistProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> saveJournalist(@RequestBody JournalistCreateData data) {
        boolean saveSuccessful = journalistService.createJournalist(data);

        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJournalist(@PathVariable int id, @RequestBody JournalistCreateData data) {
        boolean updateSuccessFul = journalistService.updateJournalist(id, data);

        if (updateSuccessFul) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteJournalist(@PathVariable int id) {
        boolean deleteSuccessful = journalistService.deleteJournalist(id);

        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
