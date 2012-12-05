package com.jyams.dispatch;

import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.dispatch.model.Dispatch;

/**
 * @author zhanglong
 * 
 *         2012-12-5 下午11:16:27
 */
public class DispatchToBuildingProjectDetailConvertor {

    public static BuildingProjectDetail convert(Dispatch dispatch) {
        BuildingProjectDetail bpd = new BuildingProjectDetail();
        bpd.setCreatedTimestamp(System.currentTimeMillis());
        bpd.setCreatorId(dispatch.getPrincipalId());
        bpd.setCreatorName(dispatch.getPrincipalName());

        bpd.setPersonId(bpd.getCreatorId());
        bpd.setPersonName(bpd.getCreatorName());
        bpd.setProjectId(dispatch.getProjectId());

        // 设置在建项目明细花费类型为人工花费
        bpd.setCostType(BuildingProjectDetail.COSTTYPE_LABOR);
        bpd.setCost(dispatch.getCost());
        bpd.setReferId(dispatch.getDispatchId() + "");
        return bpd;
    }
}
