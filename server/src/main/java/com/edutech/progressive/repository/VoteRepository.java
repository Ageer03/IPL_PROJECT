package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Long countByCategory(String category);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.team.teamId = ?1")
    void deleteByTeamId(int teamId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.cricketer.cricketerId = ?1")
    void deleteByCricketerId(int cricketerId);
}
