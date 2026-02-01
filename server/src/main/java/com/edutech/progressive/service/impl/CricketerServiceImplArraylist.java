package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CricketerServiceImplArraylist implements CricketerService {
    private List<Cricketer> cricketerList = new ArrayList<>();

    @Override
    public List<Cricketer> getAllCricketers() {
        return new ArrayList<>(cricketerList);
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) {
        cricketerList.add(cricketer);
        return cricketerList.size();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
        List<Cricketer> sorted = new ArrayList<>(cricketerList);
        Collections.sort(sorted);
        return sorted;
    }

    @Override
    public void emptyArrayList() {
        cricketerList = new ArrayList<>();
    }
}