package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchServiceImplJpa matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        try {
            return ResponseEntity.ok(matchService.getAllMatches());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable int matchId) {
        try {
            Match m = matchService.getMatchById(matchId);
            return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addMatch(@RequestBody Match match) {
        try {
            Integer id = matchService.addMatch(match);
            return ResponseEntity.status(201).body(id);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<Void> updateMatch(@PathVariable int matchId, @RequestBody Match match) {
        try {
            match.setMatchId(matchId);
            matchService.updateMatch(match);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchId) {
        try {
            matchService.deleteMatch(matchId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAllMatchesByStatus(@PathVariable String status) {
        try {
            return ResponseEntity.ok(matchService.getAllMatchesByStatus(status));
        } catch (NoMatchesFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
