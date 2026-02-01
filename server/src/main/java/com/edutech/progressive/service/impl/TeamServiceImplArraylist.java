package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamServiceImplArraylist implements TeamService {
    private List<Team> teamList = new ArrayList<>();

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teamList);
    }

    @Override
    public int addTeam(Team team) {
        teamList.add(team);
        return teamList.size();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() {
        List<Team> sorted = new ArrayList<>(teamList);
        Collections.sort(sorted);
        return sorted;
    }

    @Override
    public void emptyArrayList() {
        teamList = new ArrayList<>();
    }
}