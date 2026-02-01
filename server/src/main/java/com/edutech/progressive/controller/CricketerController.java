package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.exception.TeamCricketerLimitExceededException;
import com.edutech.progressive.service.impl.CricketerServiceImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cricketer")
public class CricketerController {

    @Autowired
    private CricketerServiceImplJpa cricketerService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello IPL Progressive Project";
    }

    @GetMapping
    public ResponseEntity<List<Cricketer>> getAllCricketers() {
        try {
            return ResponseEntity.ok(cricketerService.getAllCricketers());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{cricketerId}")
    public ResponseEntity<Cricketer> getCricketerById(@PathVariable int cricketerId) {
        try {
            Cricketer c = cricketerService.getCricketerById(cricketerId);
            return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addCricketer(@RequestBody Cricketer cricketer) {
        try {
            Integer id = cricketerService.addCricketer(cricketer);
            return ResponseEntity.status(201).body(id);
        } catch (TeamCricketerLimitExceededException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{cricketerId}")
    public ResponseEntity<Void> updateCricketer(@PathVariable int cricketerId, @RequestBody Cricketer cricketer) {
        try {
            cricketer.setCricketerId(cricketerId);
            cricketerService.updateCricketer(cricketer);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{cricketerId}")
    public ResponseEntity<Void> deleteCricketer(@PathVariable int cricketerId) {
        try {
            cricketerService.deleteCricketer(cricketerId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cricketer/team/{teamId}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeam(@PathVariable int teamId) {
        try {
            return ResponseEntity.ok(cricketerService.getCricketersByTeam(teamId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}