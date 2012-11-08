package com.jyams.hr.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.hr.dao.MultiDayDao;
import com.jyams.hr.manager.SystemManager;
import com.jyams.hr.model.MultiDay;

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemManagerImpl implements SystemManager {

	@Autowired
	private MultiDayDao multiDayDao;
	
	@Override
	public MultiDay getMultiDay(String day) {
		return multiDayDao.get(day);
	}

	@Override
	public List<MultiDay> listMultiDay() {
		return multiDayDao.getAll();
	}

	@Override
	public void saveMultiDays(List<String> days, int times) {
		if(days == null || days.size() == 0){
			return ;
		}
		multiDayDao.deleteAll();
		multiDayDao.insertAll(days, times);
	}

}
