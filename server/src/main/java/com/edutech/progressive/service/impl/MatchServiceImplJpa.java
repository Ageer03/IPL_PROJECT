package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class MatchServiceImplJpa implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public MatchServiceImplJpa() {
    }

    public MatchServiceImplJpa(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        return matchRepository.findByMatchId(matchId);
    }

    @Override
    public Integer addMatch(Match match) throws SQLException {
        if (match.getFirstTeamId() == 0) {
            Team t = match.getFirstTeam();
            if (t != null)
                match.setFirstTeamId(t.getTeamId());
        }
        if (match.getSecondTeamId() == 0) {
            Team t = match.getSecondTeam();
            if (t != null)
                match.setSecondTeamId(t.getTeamId());
        }
        if (match.getWinnerTeamId() == 0) {
            Team t = match.getWinnerTeam();
            if (t != null)
                match.setWinnerTeamId(t.getTeamId());
        }
        Match saved = matchRepository.save(match);
        return saved.getMatchId();
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        if (match.getFirstTeamId() == 0) {
            Team t = match.getFirstTeam();
            if (t != null)
                match.setFirstTeamId(t.getTeamId());
        }
        if (match.getSecondTeamId() == 0) {
            Team t = match.getSecondTeam();
            if (t != null)
                match.setSecondTeamId(t.getTeamId());
        }
        if (match.getWinnerTeamId() == 0) {
            Team t = match.getWinnerTeam();
            if (t != null)
                match.setWinnerTeamId(t.getTeamId());
        }
        matchRepository.save(match);
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        matchRepository.deleteById(matchId);
    }

    @Override
    public List<Match> getAllMatchesByStatus(String status) throws SQLException {
        List<Match> list = matchRepository.findAllByStatus(status);
        if (list == null || list.isEmpty()) {
            throw new NoMatchesFoundException("No matches found for status: " + status);
        }
        return list;
    }
}