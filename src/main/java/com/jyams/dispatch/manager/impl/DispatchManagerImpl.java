package com.jyams.dispatch.manager.impl;

import java.util.List;

import com.jyams.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jyams.buildingproject.manager.BuildingProjectDetailManager;
import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.cache.PersonCache;
import com.jyams.cache.ProjectNameCache;
import com.jyams.dispatch.DispatchToBuildingProjectDetailConvertor;
import com.jyams.dispatch.dao.DispatchDao;
import com.jyams.dispatch.dao.DispatchWorkDao;
import com.jyams.dispatch.manager.DispatchManager;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.dispatch.model.DispatchWork;
import com.jyams.dispatch.query.DispatchWorkQuery;
import com.jyams.exception.BusinessException;
import com.jyams.hr.model.Person;
import com.jyams.util.DataPage;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.IdUtil;

/**
 * 
 * @author zhanglong
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DispatchManagerImpl implements DispatchManager {

    @Autowired
    private DispatchDao dispatchDao;

    @Autowired
    private DispatchWorkDao dispatchWorkDao;

    @Autowired
    private BuildingProjectDetailManager buildingProjectDetailManager;

    @Autowired
    private ProjectNameCache projectNameCache;

    @Autowired
    private PersonCache personCache;

    @Override
    public long addDispatch(Dispatch dispatch) {
        long dispatchId = IdUtil.nextLong();
        dispatch.setDispatchId(dispatchId);
        dispatch.setCreatedTimestamp(System.currentTimeMillis());
        dispatch.setPrincipalId(SecurityUtils.getCurrentUserId());
        dispatch.setPrincipalName(SecurityUtils.getCurrentUsername());
        dispatch.setProjectName(projectNameCache.get(dispatch.getProjectId()));

        doAddDispatch(dispatch);
        return dispatchId;
    }

    private void doAddDispatch(Dispatch dispatch) {

        long dispatchId = dispatch.getDispatchId();
        List<DispatchWork> dispatchWorks = dispatch.getDispatchWorks();
        checkDuplicate(dispatch, dispatchWorks);

        // 将每个派工工作与派工联系起来
        for (DispatchWork dispatchWork : dispatchWorks) {
            long dispatchWorkId = IdUtil.nextLong();
            dispatchWork.setDispatchWorkId(dispatchWorkId);
            dispatchWork.setDispatchId(dispatchId);

            Person person = personCache.get(dispatchWork.getPersonId());
            dispatchWork.setSalary(person.getSalary());
            dispatchWork.calculateCost();
        }
        dispatchDao.insert(dispatch);

        // 批量插入
        dispatchWorkDao.batchInsert(dispatchWorks);
        buildingProjectDetailManager
                .add(DispatchToBuildingProjectDetailConvertor.convert(dispatch));
    }

    private void checkDuplicate(Dispatch dispatch, List<DispatchWork> dispatchWorks) {
        List<String> duplicatePersons = Lists.newArrayList();
        for (DispatchWork dispatchWork : dispatchWorks) {
            if (dispatchWork.getPersonId() != null
                    && dispatchWorkDao.checkDuplicateTime(dispatch.getDispatchDay(),
                            dispatchWork.getStartTime(), dispatchWork.getEndTime(),
                            dispatchWork.getPersonId()) > 0) {
                duplicatePersons.add(dispatchWork.getPersonName());
            }
        }

        if (duplicatePersons.size() > 0) {
            throw new BusinessException("员工" + duplicatePersons.toString() + "的派工时间与已有的时间重合，请重新派工！");
        }
    }

    @Override
    public void deleteDispatch(long dispatchId) {
        this.dispatchWorkDao.removeByDispatchId(dispatchId);
        this.dispatchDao.removeById(dispatchId);
        this.buildingProjectDetailManager.deleteByReferId(dispatchId,
                BuildingProjectDetail.COSTTYPE_LABOR);
    }

    @Override
    public void editDispatch(Dispatch dispatch) {
        long dispatchId = dispatch.getDispatchId();
        deleteDispatch(dispatchId);

        dispatch.setPrincipalId(SecurityUtils.getCurrentUserId());
        dispatch.setPrincipalName(SecurityUtils.getCurrentUsername());
        dispatch.setProjectName(projectNameCache.get(dispatch.getProjectId()));
        doAddDispatch(dispatch);
    }

    @Override
    public Dispatch getDispatch(long dispatchId) {
        return dispatchDao.get(dispatchId);
    }

    @Override
    public DataPage<DispatchWork> listDispatchWorks(Long projectId, Long personId,
            String personName, Integer month, Integer day, Integer pageNo, Integer pageSize) {
        return dispatchWorkDao.listDispatchWorks(projectId, personId, personName, month, day,
                pageNo, pageSize);
    }

    @Override
    public List<Dispatch> listDailyDispatchs() {
        int day = DateTimeUtils.getDayFromTimestamp(System.currentTimeMillis());
        return dispatchDao.findBy("dispatchDay", day);
    }

    @Override
    public DataPage<DispatchWork> listDispatchWorks(DispatchWorkQuery dispatchWorkQuery) {
        return dispatchWorkDao.pageQuery(dispatchWorkQuery);
    }
}
