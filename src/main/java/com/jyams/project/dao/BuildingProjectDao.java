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
     * 
     * @param projectId
     * @param companyPrincipalName
     * @param clientName
     * @param clientPrincipalName
     * @param status
     * @param orderString
     * @param pageNo
     * @param pageSize
     * @return
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
        return pagedQuery(
                "com.jyams.project.dao.BuildingProjectDao.listBuildingProject",
                map, pageNo, pageSize);
    }

    /**
     * 开票
     * 
     * @param projectId
     * @return
     */
    public int invoice(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("invoiceTimestamp", System.currentTimeMillis());
        return getSqlMapClientTemplate().update(
                "com.jyams.project.dao.BuildingProjectDao.invoice", map);
    }

    /**
     * 完工
     * 
     * @param projectId
     */
    public int completeProject(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("completionTimestamp", System.currentTimeMillis());
        map.put("personId", personId);
        map.put("personName", personName);
        return getSqlMapClientTemplate()
                .update("com.jyams.project.dao.BuildingProjectDao.completeProject",
                        map);
    }

    /**
     * 修改在建项目状态
     * 
     * @param projectId
     *            项目标识
     * @param personId
     *            修改人标识
     * @param personName
     *            修改人姓名
     * @param status
     *            状态
     * @param changeType
     *            修改类型
     * @return
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
                "com.jyams.project.dao.BuildingProjectDao.updateStatus", map);
    }

    /**
     * 修改实际成本和状态
     * 
     * @param projectId
     * @param actualCost
     * @param status
     */
    public void updateAcualCostAndStatus(long projectId, float actualCost,
            int status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("actualCost", actualCost);
        map.put("status", status);
        getSqlMapClientTemplate()
                .update("com.jyams.project.dao.BuildingProjectDao.updateAcualCostAndStatus",
                        map);
    }

    public List<Long> listProjectIds(Integer status) {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.project.dao.BuildingProjectDao.listProjectIds",
                status);
    }

    /**
     * 检查延迟项目
     */
    public void checkDelayProject() {
        getSqlMapClientTemplate().update(
                "com.jyams.project.dao.BuildingProjectDao.checkDelayProject");
    }

    /**
     * 收款
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    public int collection(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("timestamp", System.currentTimeMillis());
        try {
            return getSqlMapClientTemplate().update(
                    "com.jyams.project.dao.BuildingProjectDao.collection", map);
        } catch (DataAccessException e) {
            e.printStackTrace();
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
                "com.jyams.project.dao.BuildingProjectDao.clearInvoice", map);
    }

    public int clearCollection(long projectId, long personId, String personName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        map.put("invoiceTimestamp", System.currentTimeMillis());
        return getSqlMapClientTemplate()
                .update("com.jyams.project.dao.BuildingProjectDao.clearCollection",
                        map);
    }
}
