package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    Match findByMatchId(int matchId);

    List<Match> findAllByStatus(String status);

    @Modifying
    @Transactional
    @Query("DELETE FROM Match m WHERE m.firstTeam.teamId = ?1 OR m.secondTeam.teamId = ?1")
    void deleteByTeamId(int teamId);
}
