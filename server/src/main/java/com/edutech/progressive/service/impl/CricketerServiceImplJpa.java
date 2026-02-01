package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamCricketerLimitExceededException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.CricketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class CricketerServiceImplJpa implements CricketerService {

    @Autowired
    private CricketerRepository cricketerRepository;
    @Autowired(required = false)
    private VoteRepository voteRepository;

    public CricketerServiceImplJpa() {
    }

    public CricketerServiceImplJpa(CricketerRepository cricketerRepository) {
        this.cricketerRepository = cricketerRepository;
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        return cricketerRepository.findAll();
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws SQLException {
        if (cricketer.getTeamId() == 0) {
            Team team = cricketer.getTeam();
            if (team != null) {
                cricketer.setTeamId(team.getTeamId());
            }
        }
        long count = cricketerRepository.countByTeam_TeamId(cricketer.getTeamId());
        if (count >= 11) {
            throw new TeamCricketerLimitExceededException("Team already has 11 cricketers");
        }
        Cricketer saved = cricketerRepository.save(cricketer);
        return saved.getCricketerId();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        List<Cricketer> list = cricketerRepository.findAll();
        list.sort(Comparator.comparing(Cricketer::getExperience));
        return list;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        cricketerRepository.save(cricketer);
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        if (voteRepository != null) {
            voteRepository.deleteByCricketerId(cricketerId);
        }
        cricketerRepository.deleteById(cricketerId);
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        return cricketerRepository.findByCricketerId(cricketerId);
    }

    @Override
    public List<Cricketer> getCricketersByTeam(int teamId) throws SQLException {
        return cricketerRepository.findByTeam_TeamId(teamId);
    }
}