package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.TeamDAO;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TeamServiceImplJdbc implements TeamService {
    private TeamDAO teamDAO;

    public TeamServiceImplJdbc(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }

    @Override
    public int addTeam(Team team) throws SQLException {
        int id = teamDAO.addTeam(team);
        if (id > 0) {
            team.setTeamId(id);
        }
        return id;
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        List<Team> list = teamDAO.getAllTeams();
        Collections.sort(list);
        return list;
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        return teamDAO.getTeamById(teamId);
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        teamDAO.updateTeam(team);
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        teamDAO.deleteTeam(teamId);
    }
}