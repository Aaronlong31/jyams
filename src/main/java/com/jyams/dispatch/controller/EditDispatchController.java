package com.jyams.dispatch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyams.cache.PersonCache;
import com.jyams.dispatch.DispatchCache;
import com.jyams.dispatch.manager.DispatchManager;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.dispatch.model.DispatchWork;
import com.jyams.hr.manager.DepartmentManager;
import com.jyams.hr.model.Department;
import com.jyams.hr.model.Person;

/**
 * @author zhanglong
 * 
 *         2012-11-26 下午11:20:17
 */
@Controller
public class EditDispatchController {

    @Autowired
    private DispatchCache dispatchCache;

    @Autowired
    private PersonCache personCache;

    @Autowired
    private DispatchManager dispatchManager;

    @Autowired
    private DepartmentManager departmentManager;

    @RequestMapping(value = "/dispatch/add", method = RequestMethod.GET)
    public String toAddDispatch(@CookieValue("JSESSIONID") String sessionId, Model model) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        setDispatch(model, dispatch);
        return "dispatch/edit";
    }

    private void setDispatch(Model model, Dispatch dispatch) {
        model.addAttribute("dispatch", dispatch);

        List<Person> dispatchedPersons = Lists.newArrayList();
        List<DispatchWork> dispatchWorks = dispatch.getDispatchWorks();

        Map<DispatchWork, Department> dispatchWorkWithDepartments = Maps.newLinkedHashMap();
        for (DispatchWork dispatchWork : dispatchWorks) {
            Person person = personCache.get(dispatchWork.getPersonId());
            dispatchedPersons.add(person);
            Department department = new Department();
            department.setDepartmentId(person.getDepartmentId());
            department.setDepartmentName(person.getDepartmentName());
            dispatchWorkWithDepartments.put(dispatchWork, department);
        }

        List<Department> departments = departmentManager.listWithPersons(Person.SALARY_TYPE_DAILY);

        for (Department department : departments) {
            department.getPersons().removeAll(dispatchedPersons);
        }

        List<Department> departmentsWithUnDispatchPersons = departmentManager
                .listWithPersons(Person.SALARY_TYPE_DAILY);

        for (Department department : departmentsWithUnDispatchPersons) {
            department.getPersons().retainAll(dispatchedPersons);
        }

        model.addAttribute("dp1", departments);
        model.addAttribute("dispatchWorks", dispatchWorkWithDepartments);
    }

    @RequestMapping(value = "/dispatch/{dispatchId}/edit", method = RequestMethod.GET)
    public String toEditDispatch(@CookieValue("JSESSIONID") String sessionId,
            @PathVariable("dispatchId") long dispatchId, Model model) {
        Dispatch dispatch = this.dispatchManager.getDispatch(dispatchId);
        this.dispatchCache.put(sessionId, dispatch);
        setDispatch(model, dispatch);
        return "dispatch/edit";
    }

    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    @ResponseBody
    public void add(@CookieValue("JSESSIONID") String sessionId) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        this.dispatchManager.addDispatch(dispatch);
        this.dispatchCache.delete(sessionId);
    }

    @RequestMapping(value = "/dispatch/{dispatchId}", method = RequestMethod.PUT)
    @ResponseBody
    public void save(@CookieValue("JSESSIONID") String sessionId) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        this.dispatchManager.editDispatch(dispatch);
        this.dispatchCache.delete(sessionId);
    }

    @RequestMapping(value = "/dispatch/session", method = RequestMethod.PUT, params = {
            "projectId", "projectName" })
    @ResponseBody
    public void updateProjectInfo(@CookieValue("JSESSIONID") String sessionId, long projectId,
            String projectName) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setProjectId(projectId);
        dispatch.setProjectName(projectName);
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session",
            method = RequestMethod.PUT,
            params = { "dispatchType" })
    @ResponseBody
    public void updateDispatchType(@CookieValue("JSESSIONID") String sessionId, short dispatchType) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setDispatchType(dispatchType);
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session",
            method = RequestMethod.PUT,
            params = { "dispatchDayString" })
    @ResponseBody
    public void updateDispatchDay(@CookieValue("JSESSIONID") String sessionId,
            String dispatchDayString) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setDispatchDayString(dispatchDayString);
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session", method = RequestMethod.PUT, params = {
            "_service=addDispatchWork", "personId", "startTime", "endTime" })
    @ResponseBody
    public void addDispatchWork(@CookieValue("JSESSIONID") String sessionId, long personId,
            String startTime, String endTime) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        DispatchWork dw = new DispatchWork();
        dw.setPersonId(personId);
        dw.setPersonName(personCache.get(personId).getPersonName());
        dw.setStartTimeString(startTime);
        dw.setEndTimeString(endTime);
        dispatch.getDispatchWorks().add(dw);
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session", method = RequestMethod.PUT, params = {
            "_service=deleteDispatchWork", "personId" })
    @ResponseBody
    public void deleteDispatchWork(@CookieValue("JSESSIONID") String sessionId, long personId,
            String startTime, String endTime) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        for (DispatchWork dw : dispatch.getDispatchWorks()) {
            if (dw.getPersonId() == personId) {
                dispatch.getDispatchWorks().remove(dw);
                break;
            }
        }
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session", method = RequestMethod.PUT, params = {
            "_service=changeStartTime", "personId", "startTime" })
    @ResponseBody
    public void changeStartTime(@CookieValue("JSESSIONID") String sessionId, long personId,
            String startTime) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        for (DispatchWork dw : dispatch.getDispatchWorks()) {
            if (dw.getPersonId() == personId) {
                dw.setStartTimeString(startTime);
                break;
            }
        }
        dispatchCache.put(sessionId, dispatch);
    }

    @RequestMapping(value = "/dispatch/session", method = RequestMethod.PUT, params = {
            "_service=changeEndTime", "personId", "endTime" })
    @ResponseBody
    public void changeEndTime(@CookieValue("JSESSIONID") String sessionId, long personId,
            String endTime) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        for (DispatchWork dw : dispatch.getDispatchWorks()) {
            if (dw.getPersonId() == personId) {
                dw.setEndTimeString(endTime);
                break;
            }
        }
        dispatchCache.put(sessionId, dispatch);
    }

}
