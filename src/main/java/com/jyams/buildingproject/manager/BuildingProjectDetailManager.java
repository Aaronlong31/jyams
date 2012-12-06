package com.jyams.buildingproject.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.buildingproject.query.BuildingProjectDetailQuery;
import com.jyams.exception.BusinessException;
import com.jyams.util.DataPage;

/**
 * @author zhanglong
 * 
 *         Nov 8, 2012 9:43:16 PM
 */
public interface BuildingProjectDetailManager {
    /*-------------------------------在建项目明细管理--------------------------------*/

    /**
     * 添加在建项目明细<br>
     * 注意：项目关闭时不能添加
     * 
     * @throws BusinessException
     *             项目关闭时抛出
     */
    long add(BuildingProjectDetail buildingProjectDetail) throws BusinessException;

    /**
     * 修改在建项目明细
     * 
     * @param buildingProjectDetail
     * @return
     */
    boolean update(BuildingProjectDetail buildingProjectDetail);

    /**
     * 获取在建项目明细
     * 
     * @param buildingProjectDetailId
     * @return
     */
    @Transactional(readOnly = true)
    BuildingProjectDetail get(int buildingProjectDetailId);

    /**
     * 查询某个项目的在建项目明细
     * 
     * @param projectId
     * @return
     */
    @Transactional(readOnly = true)
    List<BuildingProjectDetail> list(long projectId);

    @Transactional(readOnly = true)
    DataPage<BuildingProjectDetail> list(BuildingProjectDetailQuery buildingProjectDetailQuery);

    void deleteByReferId(long dispatchId, short costType);
}
