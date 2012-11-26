package com.jyams.secure.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyams.secure.dao.OperateLogDao;
import com.jyams.secure.manager.OperateLogManager;
import com.jyams.secure.manager.OperateLogQuery;
import com.jyams.secure.model.OperateLog;
import com.jyams.util.DataPage;

@Service
public class OperateLogManagerImpl implements OperateLogManager {

	@Autowired
	private OperateLogDao operateLogDao;

	@Override
	public DataPage<OperateLog> listOperateLog(Integer operateType,
			Integer operateModule, Integer operateModel, String modelId,
			Long operatorId, String operatorName, String startTime,
			String endTime, Integer pageNo, Integer pageSize) {
		return operateLogDao.listOperateLog(operateType, operateModule,
				operateModel, modelId, operatorId, operatorName, startTime,
				endTime, pageNo, pageSize);
	}

	@Override
	public List<OperateLog> listOperateLogForModel(int operateModel,
			String modelId, Integer limit) {
		return operateLogDao.listOperateLogForModel(operateModel, modelId,
				limit);
	}

	@Override
	public DataPage<OperateLog> listOperateLog(OperateLogQuery operateLogQuery) {
		return operateLogDao.pageQuery(operateLogQuery);
	}
}
