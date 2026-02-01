package com.edutech.progressive.entity;

// import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;

    @Column(name = "first_team_id")
    private int firstTeamId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "first_team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team firstTeam;

    @Column(name = "second_team_id")
    private int secondTeamId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "second_team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team secondTeam;

    @Column(name = "match_date")
    @Temporal(TemporalType.DATE)
    private Date matchDate;

    @Column(name = "venue")
    private String venue;

    @Column(name = "result")
    private String result;

    @Column(name = "status")
    private String status;

    @Column(name = "winner_team_id")
    private int winnerTeamId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "winner_team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team winnerTeam;

    public Match() {
    }

    public Match(int matchId, int firstTeamId, int secondTeamId, Date matchDate, String venue, String result,
            String status, int winnerTeamId) {
        this.matchId = matchId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeamId = winnerTeamId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(int firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public int getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(int secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Match match = (Match) o;
        return matchId == match.matchId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId);
    }
}