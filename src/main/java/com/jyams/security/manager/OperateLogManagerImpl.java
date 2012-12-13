package com.jyams.security.manager;

import java.util.List;

import com.jyams.security.OperateLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyams.security.dao.OperateLogDao;
import com.jyams.security.model.OperateLog;
import com.jyams.util.DataPage;

@Service
public class OperateLogManagerImpl implements OperateLogManager {

	@Autowired
	private OperateLogDao operateLogDao;

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
