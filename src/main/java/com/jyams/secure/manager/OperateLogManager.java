package com.jyams.secure.manager;

import java.util.List;

import com.jyams.secure.model.OperateLog;
import com.jyams.util.DataPage;

/**
 * 操作日志管理接口
 * 
 * @author zhanglong
 * 
 */
public interface OperateLogManager {

    /**
     * 查询操作日志
     * 
     * @param operateType
     *            操作类型
     * @param operateModule
     *            操作模块
     * @param operateModel
     *            操作的model类
     * @param modelId
     *            model类的标识
     * @param operatorId
     *            操作人标识
     * @param operatorName
     *            操作人姓名
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @param pageNo
     *            页码
     * @param pageSize
     *            页面大小
     * @return
     */
    DataPage<OperateLog> listOperateLog(Integer operateType,
            Integer operateModule, Integer operateModel, String modelId,
            Long operatorId, String operatorName, String startTime,
            String endTime, Integer pageNo, Integer pageSize);

    /**
     * 查询model类的最近操作日志
     * 
     * @param operateModel
     * @param modelId
     * @param limit
     *            条数
     * @return
     */
    List<OperateLog> listOperateLogForModel(int operateModel, String modelId,
            Integer limit);

    DataPage<OperateLog> listOperateLog(OperateLogQuery operateLogQuery);
}
