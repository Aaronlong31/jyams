package com.jyams.secure.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.secure.model.OperateLog;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class OperateLogDao extends IBatisEntityDao<OperateLog> {

    public DataPage<OperateLog> listOperateLog(Integer operateType,
            Integer operateModule, Integer operateModel, String modelId,
            Long operatorId, String operatorName, String startTime,
            String endTime, Integer pageNo, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("operateType", operateType);
        map.put("operateModule", operateModule);
        map.put("operateModel", operateModel);
        map.put("modelId", modelId);
        map.put("operatorId", operatorId);
        map.put("operatorName", operatorName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return pagedQuery("com.jyams.secure.dao.OperateLogDao.listOperateLog",
                map, pageNo, pageSize);
    }

    public List<OperateLog> listOperateLogForModel(int operateModel,
            String modelId, Integer limit) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("operateModel", operateModel);
        map.put("modelId", modelId);
        map.put("limit", limit);
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.secure.dao.OperateLogDao.listOperateLogForModel",
                map);
    }

}
