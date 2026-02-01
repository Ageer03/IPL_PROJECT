
package com.edutech.progressive.dao;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Match;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAOImpl implements MatchDAO {
    @Override
    public int addMatch(Match match) throws SQLException {
        String sql = "INSERT INTO matches (first_team_id, second_team_id, match_date, venue, result, status, winner_team_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            java.util.Date matchDate = match.getMatchDate() == null ? new java.util.Date() : match.getMatchDate();
            String venue = match.getVenue() == null ? "" : match.getVenue();
            String result = match.getResult() == null ? "" : match.getResult();
            String status = match.getStatus() == null ? "" : match.getStatus();
            ps.setInt(1, match.getFirstTeamId());
            ps.setInt(2, match.getSecondTeamId());
            ps.setDate(3, new java.sql.Date(matchDate.getTime()));
            ps.setString(4, venue);
            ps.setString(5, result);
            ps.setString(6, status);
            ps.setInt(7, match.getWinnerTeamId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        String sql = "SELECT * FROM matches WHERE match_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matchId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return mapMatch(rs);
            }
        }
        return null;
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        String sql = "UPDATE matches SET first_team_id=?, second_team_id=?, match_date=?, venue=?, result=?, status=?, winner_team_id=? WHERE match_id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            java.util.Date matchDate = match.getMatchDate() == null ? new java.util.Date() : match.getMatchDate();
            String venue = match.getVenue() == null ? "" : match.getVenue();
            String result = match.getResult() == null ? "" : match.getResult();
            String status = match.getStatus() == null ? "" : match.getStatus();
            ps.setInt(1, match.getFirstTeamId());
            ps.setInt(2, match.getSecondTeamId());
            ps.setDate(3, new java.sql.Date(matchDate.getTime()));
            ps.setString(4, venue);
            ps.setString(5, result);
            ps.setString(6, status);
            ps.setInt(7, match.getWinnerTeamId());
            ps.setInt(8, match.getMatchId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        String sql = "DELETE FROM matches WHERE match_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matchId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        List<Match> list = new ArrayList<>();
        String sql = "SELECT * FROM matches";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapMatch(rs));
            }
        }
        return list;
    }

    private Match mapMatch(ResultSet rs) throws SQLException {
        Match m = new Match();
        m.setMatchId(rs.getInt("match_id"));
        m.setFirstTeamId(rs.getInt("first_team_id"));
        m.setSecondTeamId(rs.getInt("second_team_id"));
        m.setMatchDate(rs.getDate("match_date"));
        m.setVenue(rs.getString("venue"));
        m.setResult(rs.getString("result"));
        m.setStatus(rs.getString("status"));
        m.setWinnerTeamId(rs.getInt("winner_team_id"));
        return m;
    }
}
