package com.jyams.secure.manager;

import com.jyams.secure.model.OperateLog;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;
import com.jyams.util.search.SqlOrder;

/**
 * 操作日志查询类
 * 
 * @author zhanglong
 * 
 */
public class OperateLogQuery extends Query<OperateLog> {

    private String operatorName; // 操作人姓名
    private Long operateTimestampStart;
    private Long operateTimestampEnd;
    private Integer operateType; // 操作类型
    private String operateInfo; // 操作信息
    private Integer operateModule; // 操作模块
    private String modelId; // 被操作对象的标识
    private Integer operateModel;
    private String ip;

    public OperateLogQuery() {
        super();
        init();
    }

    public OperateLogQuery(int pageNo, int pageSize) {
        super(pageNo, pageSize);
        init();
    }

    public OperateLogQuery(SearchFilter filter) {
        super(filter);
        init();
    }

    private void init() {
        this.addOrder(new SqlOrder("operateTimestamp", false));
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public String getOperateInfo() {
        return operateInfo;
    }

    public Integer getOperateModule() {
        return operateModule;
    }

    public String getModelId() {
        return modelId;
    }

    public Integer getOperateModel() {
        return operateModel;
    }

    public String getIp() {
        return ip;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * 设置查询时间段
     * 
     * @param operateTimestamp
     */
    public void setOperateTimestamp(String operateTimestamp) {
        operateTimestampStart = DateTimeUtils
                .getDayStartTimestamp(operateTimestamp);
        operateTimestampEnd = DateTimeUtils
                .getDayEndTimestamp(operateTimestamp);
    }

    public Long getOperateTimestampStart() {
        return operateTimestampStart;
    }

    public Long getOperateTimestampEnd() {
        return operateTimestampEnd;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public void setOperateInfo(String operateInfo) {
        this.operateInfo = operateInfo;
    }

    public void setOperateModule(Integer operateModule) {
        this.operateModule = operateModule;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setOperateModel(Integer operateModel) {
        this.operateModel = operateModel;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
