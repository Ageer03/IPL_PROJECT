package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "team")
public class Team implements Comparable<Team> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "location")
    private String location;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "establishment_year")
    private int establishmentYear;

    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Cricketer> cricketers;

    public Team() {
    }

    public Team(int teamId, String teamName, String location, String ownerName, int establishmentYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.location = location;
        this.ownerName = ownerName;
        this.establishmentYear = establishmentYear;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public List<Cricketer> getCricketers() {
        return cricketers;
    }

    public void setCricketers(List<Cricketer> cricketers) {
        this.cricketers = cricketers;
    }

    @Override
    public int compareTo(Team o) {
        return this.teamName.compareToIgnoreCase(o.teamName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Team team = (Team) o;
        return teamId == team.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }
}