package com.jyams.project.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyams.buildingproject.model.BuildingProject;
import com.jyams.exception.BusinessException;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.model.Project;
import com.jyams.project.query.ProjectQuery;
import com.jyams.util.DataPage;
import com.jyams.util.SpringSecurityUtils;
import com.jyams.util.WebUtils;

/**
 * @author zhanglong
 * 
 *         Nov 21, 2012 9:38:53 PM
 */
@Controller
public class ProjectQueryController {

    protected static final String SEARCH_PROJECT_YEAR = "searchYear";

    @Autowired
    private ProjectManager projectManager;

    @RequestMapping(
            value = "/project", method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("project/list");
        mav.addObject("searchYear", getSearchYear());
        return mav;
    }

    @RequestMapping(
            value = "/project", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<Project> list(ProjectQuery projectQuery)
            throws BusinessException {
        long projectIdStart = getProjectIdStart();
        projectQuery.setProjectIdStart(projectIdStart);
        projectQuery.setProjectIdEnd(projectIdStart + 9999);
        return projectManager.listProjects(projectQuery);
    }

    @RequestMapping(
            value = "/project/mine", method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listMine() {
        return new ModelAndView("project/listMine");
    }

    @RequestMapping(
            value = "/project/mine", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<Project> listMine(ProjectQuery projectQuery) {
        projectQuery.setCompanyPrincipalId(SpringSecurityUtils
                .getCurrentUserId());
        return projectManager.listProjects(projectQuery);
    }

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("projectId") long projectId) {
        Project project = projectManager.getProject(projectId);
        ModelAndView mav = new ModelAndView("project/view");
        mav.addObject("project", project);
        return mav;
    }

    @RequestMapping(
            value = "/project/simple", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> listSimpleProject() {
        return projectManager.listProjectsSimple(
                BuildingProject.STATUS_BUILDING, null);
    }

    @RequestMapping(
            value = "/project/updateSearchYear", method = RequestMethod.PUT,
            params = "increYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateSearchYear(Integer increYear) {
        int searchYear = getSearchYear();
        if (increYear == null) {
            increYear = 0;
        }
        searchYear += increYear;
        WebUtils.getHttpSession().setAttribute(SEARCH_PROJECT_YEAR, searchYear);
    }

    @RequestMapping(
            value = "/project/basic", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<Project> listBasic(ProjectQuery projectQuery) {
        long projectIdStart = getProjectIdStart();
        projectQuery.setProjectIdStart(projectIdStart);
        projectQuery.setProjectIdEnd(projectIdStart + 9999);
        return projectManager.listBasicProject(projectQuery);
    }

    private int getSearchYear() {
        HttpSession session = WebUtils.getHttpSession();
        Object obj = session.getAttribute(SEARCH_PROJECT_YEAR);
        int sYear;
        if (obj == null) {
            sYear = Calendar.getInstance().get(Calendar.YEAR);
            session.setAttribute(SEARCH_PROJECT_YEAR, sYear);
        } else {
            sYear = Integer.parseInt(obj.toString());
        }
        return sYear;
    }

    private long getProjectIdStart() {
        return getSearchYear() % 100 * 10000;
    }
}
