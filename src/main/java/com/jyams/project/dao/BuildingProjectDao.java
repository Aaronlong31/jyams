package com.jyams.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.project.model.BuildingProject;
import com.jyams.project.model.ChangeStatusType;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class BuildingProjectDao extends IBatisEntityDao<BuildingProject> {

    /**
     * 查询在建项目
     */
    public DataPage<BuildingProject> listBuildingProject(Long projectId,
            String companyPrincipalName, String clientName,
            String clientPrincipalName, Integer status, String orderString,
            Integer pageNo, Integer pageSize, boolean hidden) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("companyPrincipalName", companyPrincipalName);
        map.put("clientName", clientName);
        map.put("clientPrincipalName", clientPrincipalName);
        map.put("orderString", orderString);
        map.put("status", status);
        map.put("hidden", hidden);
        return pagedQuery("BuildingProjectDao.listBuildingProject", map,
                pageNo, pageSize);
    }

    /**
     * 开票
     */
    public int invoice(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("invoiceTimestamp", System.currentTimeMillis());
        return getSqlMapClientTemplate().update("BuildingProjectDao.invoice",
                map);
    }

    /**
     * 完工
     */
    public int completeProject(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("completionTimestamp", System.currentTimeMillis());
        map.put("personId", personId);
        map.put("personName", personName);
        return getSqlMapClientTemplate().update(
                "BuildingProjectDao.completeProject", map);
    }

    /**
     * 修改在建项目状态
     */
    public int updateStatus(long projectId, long personId, String personName,
            int status, ChangeStatusType changeType) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", status);
        map.put("changeType", changeType);
        return getSqlMapClientTemplate().update(
                "BuildingProjectDao.updateStatus", map);
    }

    /**
     * 修改实际成本和状态
     */
    public void updateAcualCostAndStatus(long projectId, float actualCost,
            int status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("actualCost", actualCost);
        map.put("status", status);
        getSqlMapClientTemplate().update(
                "BuildingProjectDao.updateAcualCostAndStatus", map);
    }

    public List<Long> listProjectIds(Integer status) {
        return getSqlMapClientTemplate().queryForList(
                "BuildingProjectDao.listProjectIds", status);
    }

    /**
     * 检查延迟项目
     */
    public void checkDelayProject() {
        getSqlMapClientTemplate()
                .update("BuildingProjectDao.checkDelayProject");
    }

    /**
     * 收款
     */
    public int collection(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("timestamp", System.currentTimeMillis());
        try {
            return getSqlMapClientTemplate().update(
                    "BuildingProjectDao.collection", map);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public int clearInvoice(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("invoiceTimestamp", System.currentTimeMillis());
        return getSqlMapClientTemplate().update(
                "BuildingProjectDao.clearInvoice", map);
    }

    public int clearCollection(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("invoiceTimestamp", System.currentTimeMillis());
        return getSqlMapClientTemplate().update(
                "BuildingProjectDao.clearCollection", map);
    }
}
