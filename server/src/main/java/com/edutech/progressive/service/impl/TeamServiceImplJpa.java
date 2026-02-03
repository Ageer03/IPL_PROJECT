package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamServiceImplJpa implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired(required = false)
    private CricketerRepository cricketerRepository;
    @Autowired(required = false)
    private MatchRepository matchRepository;
    @Autowired(required = false)
    private VoteRepository voteRepository;
    @Autowired(required = false)
    private TicketBookingRepository ticketBookingRepository;

    public TeamServiceImplJpa() {
    }

    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamRepository.findAll();
    }

    @Override
    public int addTeam(Team team) throws SQLException {
        Team existing = teamRepository.findByTeamName(team.getTeamName());
        if (existing != null) {
            throw new TeamAlreadyExistsException("Team already exists");
        }
        Team saved = teamRepository.save(team);
        return saved.getTeamId();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        List<Team> teams = teamRepository.findAll();
        teams.sort(Comparator.comparing(Team::getTeamName));
        return teams;
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        Team team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            throw new TeamDoesNotExistException("Team does not exist");
        }
        return team;
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        Team existing = teamRepository.findByTeamName(team.getTeamName());
        if (existing != null && existing.getTeamId() != team.getTeamId()) {
            throw new TeamAlreadyExistsException("Team already exists");
        }
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void deleteTeam(int teamId) throws SQLException {
        // if (ticketBookingRepository != null) {
        //     ticketBookingRepository.deleteByTeamId(teamId);
        // }
        // if (matchRepository != null) {
        //     matchRepository.deleteByTeamId(teamId);
        // }
        // if (cricketerRepository != null) {
        //     cricketerRepository.deleteByTeamId(teamId);
        // }
        // if (voteRepository != null) {
        //     voteRepository.deleteByTeamId(teamId);
        // }
        teamRepository.deleteById(teamId);
    }
}