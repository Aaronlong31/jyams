package com.jyams.project.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jyams.exception.BusinessException;
import com.jyams.project.dao.DispatchDao;
import com.jyams.project.dao.DispatchWorkDao;
import com.jyams.project.manager.BuildingProjectDetailManager;
import com.jyams.project.manager.BuildingProjectManager;
import com.jyams.project.manager.DispatchWorkQuery;
import com.jyams.project.model.Dispatch;
import com.jyams.project.model.DispatchWork;
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
public class DispatchManagerImpl implements
        com.jyams.project.manager.DispatchManager {

    @Autowired
    private DispatchDao dispatchDao;
    @Autowired
    private DispatchWorkDao dispatchWorkDao;
    @Autowired
    private BuildingProjectManager buildingProjectManager;
    @Autowired
    private BuildingProjectDetailManager buildingProjectDetailManager;

    @Override
    public long addDispatch(Dispatch dispatch) throws BusinessException {
        long dispatchId = IdUtil.nextLong();
        dispatch.setDispatchId(dispatchId);
        dispatch.setCreatedTimestamp(System.currentTimeMillis());
        dispatchDao.insert(dispatch);

        List<DispatchWork> dispatchWorks = dispatch.getDispatchWorks();

        List<String> duplicatePersons = Lists.newArrayList();
        for (DispatchWork dispatchWork : dispatchWorks) {
            if (dispatchWork.getPersonId() != null
                    && dispatchWorkDao.checkDuplicateTime(
                            dispatch.getDispatchDay(),
                            dispatchWork.getStartTime(),
                            dispatchWork.getEndTime(),
                            dispatchWork.getPersonId()) > 0)
                duplicatePersons.add(dispatchWork.getPersonName());
        }

        if (duplicatePersons.size() > 0) {
            throw new BusinessException("员工" + duplicatePersons.toString()
                    + "的派工时间与已有的时间重合，请重新派工！");
        }

        // 将每个派工工作与派工联系起来
        for (DispatchWork dispatchWork : dispatchWorks) {
            long dispatchWorkId = IdUtil.nextLong();
            dispatchWork.setDispatchWorkId(dispatchWorkId);
            dispatchWork.setDispatchId(dispatchId);
        }
        // 批量插入
        dispatchWorkDao.batchInsert(dispatchWorks);
        buildingProjectDetailManager.addBuildingProjectDetail(dispatch);
        return dispatchId;
    }

    @Override
    public Dispatch getDispatch(long dispatchId) {
        return dispatchDao.get(dispatchId);
    }

    @Override
    public DataPage<DispatchWork> listDispatchWorks(Long projectId,
            Long personId, String personName, Integer month, Integer day,
            Integer pageNo, Integer pageSize) {
        return dispatchWorkDao.listDispatchWorks(projectId, personId,
                personName, month, day, pageNo, pageSize);
    }

    @Override
    public List<Dispatch> listDailyDispatchs() {
        int day = DateTimeUtils.getDayFromTimestamp(System.currentTimeMillis());
        return dispatchDao.findBy("dispatchDay", day);
    }

    @Override
    public DataPage<DispatchWork> listDispatchWorks(
            DispatchWorkQuery dispatchWorkQuery) {
        return dispatchWorkDao.pageQuery(dispatchWorkQuery);
    }
}
