package com.jyams.project.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.exception.BusinessException;
import com.jyams.project.model.Dispatch;
import com.jyams.project.model.DispatchWork;
import com.jyams.project.query.DispatchWorkQuery;
import com.jyams.util.DataPage;

/**
 * 派工管理接口
 * 
 * @author zhanglong
 * 
 */
@Transactional(rollbackFor = Exception.class)
public interface DispatchManager {
    /**
     * 增加派工信息<br>
     * 如果没有员工标识，则需要身份证号码 增加成功后，添加一条在建项目明细记录
     * 
     * @throws BusinessException
     *             项目关闭时抛出
     */
    long addDispatch(Dispatch dispatch) throws BusinessException;

    /**
     * 查看派工，会包含本次派工的所有派工工作
     * 
     * @param dispatchId
     * @return
     */
    @Transactional(readOnly = true)
    Dispatch getDispatch(long dispatchId);

    /**
     * 查看每日派工
     * 
     * @return
     */
    @Transactional(readOnly = true)
    List<Dispatch> listDailyDispatchs();

    @Transactional(readOnly = true)
    DataPage<DispatchWork> listDispatchWorks(Long projectId, Long personId,
            String personName, Integer month, Integer day, Integer pageNo,
            Integer pageSize);

    @Transactional(readOnly = true)
    DataPage<DispatchWork> listDispatchWorks(DispatchWorkQuery dispatchWorkQuery);
}
