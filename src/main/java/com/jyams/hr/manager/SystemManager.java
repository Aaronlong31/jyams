package com.jyams.hr.manager;

import java.util.List;

import com.jyams.hr.model.MultiDay;

public interface SystemManager {

    void saveMultiDays(List<String> days, int times);

    MultiDay getMultiDay(String day);

    List<MultiDay> listMultiDay();
}
