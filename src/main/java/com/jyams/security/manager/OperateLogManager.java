package com.jyams.security.manager;

import java.util.List;

import com.jyams.security.OperateLogQuery;
import com.jyams.security.model.OperateLog;
import com.jyams.util.DataPage;

/**
 * 操作日志管理接口
 * 
 * @author zhanglong
 * 
 */
public interface OperateLogManager {

    /**
     * 查询model类的最近操作日志
     */
    List<OperateLog> listOperateLogForModel(int operateModel, String modelId,
            Integer limit);

    DataPage<OperateLog> listOperateLog(OperateLogQuery operateLogQuery);
}
