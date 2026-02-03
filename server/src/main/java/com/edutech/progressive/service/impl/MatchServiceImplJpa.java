package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class MatchServiceImplJpa implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired(required = false)
    private TicketBookingRepository ticketBookingRepository;

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
        // if(match.getFirstTeamId()==0 && match.getFirstTeam()!=null){
        //     match.setFirstTeamId(match.getFirstTeam().getTeamId());
        // }
        // if(match.getSecondTeamId()==0 && match.getSecondTeam()!=null){
        //     match.setSecondTeamId(match.getSecondTeam().getTeamId());
        // }
        // if(match.getWinnerTeamId()==0 && match.getWinnerTeam()!=null){
        //     match.setWinnerTeamId(match.getWinnerTeam().getTeamId());
        // }
        Match saved = matchRepository.save(match);
        return saved.getMatchId();
        
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        Match existing=matchRepository.findById(match.getMatchId()).orElseThrow(()->new SQLException("Match not found"));
        existing.setMatchDate(match.getMatchDate());
        existing.setVenue(match.getVenue());
        existing.setStatus(match.getStatus());
        // if (match.getFirstTeamId() == 0) {
            existing.setFirstTeamId(match.getFirstTeamId());
        //}
        //if (match.getSecondTeamId() == 0) {
            existing.setSecondTeamId(match.getSecondTeamId());
        //}
        //if (match.getWinnerTeamId() == 0) {
            existing.setWinnerTeamId(match.getWinnerTeamId());
        //}
        matchRepository.save(existing);
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        // if (ticketBookingRepository != null) {
        //     ticketBookingRepository.deleteByMatchId(matchId);
        // }/
        if(!matchRepository.existsById(matchId)){
              throw new SQLException("Match not found");
        }
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