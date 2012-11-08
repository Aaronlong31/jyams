package com.jyams.secure.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.common.collect.Maps;

/**
 * 操作日志
 * @author zhanglong
 *
 */
public class OperateLog {

	private static Logger logger = LoggerFactory.getLogger("JDBC");
	public static final Map<Integer, String> TYPE_STRING_MAP = Maps.newHashMap();
	public static final Map<Integer, String> MODULE_STRING_MAP = Maps.newHashMap();
	public static final Map<Integer, String> MODEL_STRING_MAP = Maps.newHashMap();

	/** 操作类型-新增 **/
	public static final int TYPE_INSERT = 1;
	/** 操作类型-修改 **/
	public static final int TYPE_UPDATE = 2;
	/** 操作类型-删除 **/
	public static final int TYPE_DELETE = 3;
	/** 操作类型-登录 **/
	public static final int TYPE_LOGIN = 4;

	/** 模块-人力资源 **/
	public static final int MODULE_HR = 1;
	/** 模块-项目 **/
	public static final int MODULE_PROJECT = 2;
	/** 模块-采购 **/
	public static final int MODULE_PURCHASE = 3;
	/** 模块-安全 **/
	public static final int MODULE_SECURE = 4;

	public static final int MODEL_CLIENT = 1;
	public static final int MODEL_CLIENTPRINCIPAL = 2;
	public static final int MODEL_DEPARTMENT = 3;
	public static final int MODEL_JOB = 4;
	public static final int MODEL_PERSON = 5;
	public static final int MODEL_BUILDINGPROJECT = 6;
	public static final int MODEL_BUILDINGPROJECTDETAIL = 7;
	public static final int MODEL_DISPATCH = 8;
	public static final int MODEL_DISPATCHWORK = 9;
	public static final int MODEL_PROJECT = 10;
	public static final int MODEL_QUOTE = 11;
	public static final int MODEL_QUOTEITEM = 12;
	public static final int MODEL_PURCHASE = 13;
	public static final int MODEL_PURCHASEITEM = 14;
	public static final int MODEL_AUTHORITY = 15;
	public static final int MODEL_ROLE = 16;
	public static final int MODEL_USER = 17;

	static {
		// initial type map
		TYPE_STRING_MAP.put(TYPE_INSERT, "新增");
		TYPE_STRING_MAP.put(TYPE_UPDATE, "修改");
		TYPE_STRING_MAP.put(TYPE_DELETE, "删除");
		TYPE_STRING_MAP.put(TYPE_LOGIN, "登录");
		
		MODULE_STRING_MAP.put(MODULE_HR, "人力资源");
		MODULE_STRING_MAP.put(MODULE_PROJECT, "施工流程");
		MODULE_STRING_MAP.put(MODULE_PURCHASE, "采购");
		MODULE_STRING_MAP.put(MODULE_SECURE, "安全");
		
		MODEL_STRING_MAP.put(MODEL_CLIENT, "客户");
		MODEL_STRING_MAP.put(MODEL_CLIENTPRINCIPAL, "客户负责人");
		MODEL_STRING_MAP.put(MODEL_DEPARTMENT, "部门");
		MODEL_STRING_MAP.put(MODEL_JOB, "员工角色");
		MODEL_STRING_MAP.put(MODEL_PERSON, "员工");
		MODEL_STRING_MAP.put(MODEL_BUILDINGPROJECT, "在建施工流程");
		MODEL_STRING_MAP.put(MODEL_BUILDINGPROJECTDETAIL, "在建施工流程明细");
		MODEL_STRING_MAP.put(MODEL_DISPATCH, "派工");
		MODEL_STRING_MAP.put(MODEL_DISPATCHWORK, "派工工作");
		MODEL_STRING_MAP.put(MODEL_PROJECT, "施工流程");
		MODEL_STRING_MAP.put(MODEL_QUOTE, "报价单");
		MODEL_STRING_MAP.put(MODEL_QUOTEITEM, "报价单项");
		MODEL_STRING_MAP.put(MODEL_PURCHASE, "采购单");
		MODEL_STRING_MAP.put(MODEL_PURCHASEITEM, "采购单项");
		MODEL_STRING_MAP.put(MODEL_AUTHORITY, "权限");
		MODEL_STRING_MAP.put(MODEL_ROLE, "操作员角色");
		MODEL_STRING_MAP.put(MODEL_USER, "操作员");
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	
	private long operateLogId; // 操作日志标识
	private long operatorId; // 操作人标识
	private String operatorName; // 操作人姓名
	private String operateTimestamp; // 操作时间
	private int operateType; // 操作类型
	private String operateInfo; // 操作信息
	private int operateModule; // 操作模块
	private String modelId; // 被操作对象的标识
	private Integer operateModel;
	private String ip;

	public OperateLog() {
	}

	public void log() {
		log(operatorId, operatorName, operateType, operateInfo, operateModule, modelId,
				operateModel, ip);
	}

	public static void log(long operatorId, String operatorName, int operateType,
			String operateInfo, int operateModule, String modelId, Integer operateModel, String ip) {
		MDC.put("operatorId", operatorId + "");
		MDC.put("operatorName", operatorName);
		MDC.put("operateType", operateType + "");
		MDC.put("operateModule", operateModule + "");
		MDC.put("operateModel", operateModel + "");
		
		MDC.put("operateTimestamp", sdf.format(new Date()));
		MDC.put("modelId", (modelId == null || modelId.trim().length() == 0) ? "" : modelId);
		MDC.put("ip", ip);
		logger.info(operateInfo);
	}

	public long getOperateLogId() {
		return operateLogId;
	}

	public void setOperateLogId(long operateLogId) {
		this.operateLogId = operateLogId;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateTimestamp() {
		return operateTimestamp;
	}

	public void setOperateTimestamp(String operateTimestamp) {
		this.operateTimestamp = operateTimestamp;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

	public int getOperateModule() {
		return operateModule;
	}

	public void setOperateModule(int operateModule) {
		this.operateModule = operateModule;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Integer getOperateModel() {
		return operateModel;
	}

	public void setOperateModel(Integer operateModel) {
		this.operateModel = operateModel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperateTypeString() {
		return TYPE_STRING_MAP.get(operateType);
	}

	public String getOperateModuleString() {
		return MODULE_STRING_MAP.get(operateModule);
	}

	public String getOperateModelString() {
		return MODEL_STRING_MAP.get(operateModel);
	}

}
