package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.CricketerDAO;
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CricketerServiceImplJdbc implements CricketerService {
    private CricketerDAO cricketerDAO;

    public CricketerServiceImplJdbc(CricketerDAO cricketerDAO) {
        this.cricketerDAO = cricketerDAO;
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        return cricketerDAO.getAllCricketers();
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws SQLException {
        int id = cricketerDAO.addCricketer(cricketer);
        if (id > 0) {
            cricketer.setCricketerId(id);
        }
        return id;
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        List<Cricketer> list = cricketerDAO.getAllCricketers();
        Collections.sort(list);
        return list;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        cricketerDAO.updateCricketer(cricketer);
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        cricketerDAO.deleteCricketer(cricketerId);
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        return cricketerDAO.getCricketerById(cricketerId);
    }
}