
package com.edutech.progressive.dao;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Cricketer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CricketerDAOImpl implements CricketerDAO {
    @Override
    public int addCricketer(Cricketer cricketer) throws SQLException {
        String sql = "INSERT INTO cricketer (team_id, cricketer_name, age, nationality, experience, role, total_runs, total_wickets) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            String nationality = cricketer.getNationality() == null ? "" : cricketer.getNationality();
            String role = cricketer.getRole() == null ? "" : cricketer.getRole();
            ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, nationality);
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, role);
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        String sql = "SELECT * FROM cricketer WHERE cricketer_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cricketerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCricketer(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        String sql = "UPDATE cricketer SET team_id=?, cricketer_name=?, age=?, nationality=?, experience=?, role=?, total_runs=?, total_wickets=? WHERE cricketer_id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            String nationality = cricketer.getNationality() == null ? "" : cricketer.getNationality();
            String role = cricketer.getRole() == null ? "" : cricketer.getRole();
            ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, nationality);
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, role);
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            ps.setInt(9, cricketer.getCricketerId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        String sql = "DELETE FROM cricketer WHERE cricketer_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cricketerId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        List<Cricketer> list = new ArrayList<>();
        String sql = "SELECT * FROM cricketer";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapCricketer(rs));
            }
        }
        return list;
    }

    private Cricketer mapCricketer(ResultSet rs) throws SQLException {
        Cricketer c = new Cricketer();
        c.setCricketerId(rs.getInt("cricketer_id"));
        c.setTeamId(rs.getInt("team_id"));
        c.setCricketerName(rs.getString("cricketer_name"));
        c.setAge(rs.getInt("age"));
        c.setNationality(rs.getString("nationality"));
        c.setExperience(rs.getInt("experience"));
        c.setRole(rs.getString("role"));
        c.setTotalRuns(rs.getInt("total_runs"));
        c.setTotalWickets(rs.getInt("total_wickets"));
        return c;
    }
}
