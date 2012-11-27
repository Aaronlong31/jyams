package com.jyams.dispatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyams.dispatch.DispatchCache;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.hr.manager.DepartmentManager;
import com.jyams.hr.model.Person;

/**
 * @author zhanglong
 * 
 *         2012-11-26 下午11:20:17
 */
@Controller
public class AddDispatchWorkController {

    @Autowired
    private DispatchCache dispatchCache;

    @Autowired
    private DepartmentManager departmentManager;

    @RequestMapping(value = "/dispatch/add", method = RequestMethod.GET)
    public String toAddDispatch(@CookieValue("JSESSIONID") String sessionId,
            Model model) {
        model.addAttribute("dispatch", dispatchCache.get(sessionId));
        model.addAttribute("departments",
                departmentManager.listWithPersons(Person.SALARY_TYPE_DAILY));
        return "dispatch/add";
    }

    @RequestMapping(
            value = "/dispatch",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addDispatch() {

    }

    @RequestMapping(
            value = "/dispatch/session",
            method = RequestMethod.PUT,
            params = { "projectId", "projectName" })
    @ResponseBody
    public boolean updateProjectInfo(
            @CookieValue("JSESSIONID") String sessionId, long projectId,
            String projectName) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setProjectId(projectId);
        dispatch.setProjectName(projectName);
        dispatchCache.put(sessionId, dispatch);
        return true;
    }

    @RequestMapping(
            value = "/dispatch/session",
            method = RequestMethod.PUT,
            params = { "dispatchType" })
    @ResponseBody
    public boolean updateProjectInfo(
            @CookieValue("JSESSIONID") String sessionId, short dispatchType) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setDispatchType(dispatchType);
        dispatchCache.put(sessionId, dispatch);
        return true;
    }

    @RequestMapping(
            value = "/dispatch/session",
            method = RequestMethod.PUT,
            params = { "dispatchDayString" })
    @ResponseBody
    public boolean updateProjectInfo(
            @CookieValue("JSESSIONID") String sessionId,
            String dispatchDayString) {
        Dispatch dispatch = dispatchCache.get(sessionId);
        dispatch.setDispatchDayString(dispatchDayString);
        dispatchCache.put(sessionId, dispatch);
        return true;
    }
}
