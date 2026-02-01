package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cricketer")
public class Cricketer implements Comparable<Cricketer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cricketer_id")
    private int cricketerId;

    @Column(name = "team_id")
    private int teamId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team team;

    @Column(name = "cricketer_name")
    private String cricketerName;

    @Column(name = "age")
    private int age;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "experience")
    private int experience;

    @Column(name = "role")
    private String role;

    @Column(name = "total_runs")
    private int totalRuns;

    @Column(name = "total_wickets")
    private int totalWickets;

    public Cricketer() {
    }

    public Cricketer(int cricketerId, int teamId, String cricketerName, int age, String nationality, int experience,
            String role, int totalRuns, int totalWickets) {
        this.cricketerId = cricketerId;
        this.teamId = teamId;
        this.cricketerName = cricketerName;
        this.age = age;
        this.nationality = nationality;
        this.experience = experience;
        this.role = role;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
    }

    public int getCricketerId() {
        return cricketerId;
    }

    public void setCricketerId(int cricketerId) {
        this.cricketerId = cricketerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCricketerName() {
        return cricketerName;
    }

    public void setCricketerName(String cricketerName) {
        this.cricketerName = cricketerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    @Override
    public int compareTo(Cricketer o) {
        return Integer.compare(this.experience, o.experience);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cricketer that = (Cricketer) o;
        return cricketerId == that.cricketerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cricketerId);
    }
}