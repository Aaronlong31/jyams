package com.jyams.buildingproject.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jyams.util.json.DateToStringJsonSerializer;
import com.jyams.util.json.LongToStringJsonSerializer;

/**
 * 在建项目明细
 * 
 * @author zhanglong
 * 
 */
public class BuildingProjectDetail {

    /** 花费类型 - 材料花费 **/
    public static final short COSTTYPE_MATERIAL = 1;
    /** 花费类型 - 订单花费 **/
    public static final short COSTTYPE_ORDER = 2;
    /** 花费类型 - 人工花费 **/
    public static final short COSTTYPE_LABOR = 3;
    /** 花费类型 - 其他花费 **/
    public static final short COSTTYPE_OTHER = 4;

    private Long detailId; // 明细编号
    private long projectId; // 项目标识
    private long personId; // 经办人标识
    private String personName; // 经办人姓名
    private long creatorId; // 创建人标识
    private String creatorName; // 创建人姓名
    private long createdTimestamp; // 创建时戳
    private short costType; // 花费类型
    private float cost; // 花费额
    private String referId; // 花费关联纪录标识
    private String description; // 花费描述

    private BuildingProject buildingProject;

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getDetailId() {
        return detailId;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getProjectId() {
        return projectId;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public short getCostType() {
        return costType;
    }

    public void setCostType(short costType) {
        this.costType = costType;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public BuildingProject getBuildingProject() {
        return buildingProject;
    }

    public void setBuildingProject(BuildingProject buildingProject) {
        this.buildingProject = buildingProject;
    }

    /**
     * 获得材料花费
     * 
     * @return 当{@link #costType}={@link #COSTTYPE_MATERIAL}时，返回{@link #cost}
     *         ，否则返回0.
     */
    public float getMaterialCost() {
        return costType == COSTTYPE_MATERIAL ? cost : 0;
    }

    /**
     * 获得订单花费
     * 
     * @return
     */
    public float getOrderCost() {
        return costType == COSTTYPE_ORDER ? cost : 0;
    }

    /**
     * 获得人工花费
     * 
     * @return
     */
    public float getLaborCost() {
        return costType == COSTTYPE_LABOR ? cost : 0;
    }

    /**
     * 获得其他花费
     * 
     * @return
     */
    public float getOtherCost() {
        return costType == COSTTYPE_OTHER ? cost : 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
