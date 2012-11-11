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

import com.jyams.hr.manager.PersonManager;
import com.jyams.hr.model.Person;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.model.BuildingProject;
import com.jyams.project.model.Project;
import com.jyams.project.query.ProjectQuery;
import com.jyams.util.DataPage;
import com.jyams.util.SpringSecurityUtils;
import com.jyams.util.WebUtils;

/**
 * @author zhanglong
 * 
 *         Nov 10, 2012 9:08:01 PM
 */
@Controller
public class ProjectController {

    protected static final String SEARCH_PROJECT_YEAR = "searchYear";

    @Autowired
    private ProjectManager projectManager;

    @Autowired
    private PersonManager personManager;

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public void add(Project project, boolean isNewYear) {
        project.setCreatedTimestamp(SpringSecurityUtils.getCurrentUserId());
        project.setCreatorName(SpringSecurityUtils.getCurrentUserName());
        projectManager.addProject(project, isNewYear);
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public void update(Project project) {
        project.setLastModifierId(SpringSecurityUtils.getCurrentUserId());
        project.setLastModifierName(SpringSecurityUtils.getCurrentUserName());
        project.setLastModifiedTimestamp(System.currentTimeMillis());
        projectManager.modifyProject(project);
    }

    @RequestMapping(value = "/project/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        List<Person> persons = personManager.listActivitySimplePersons();
        ModelAndView mav = new ModelAndView("project/add");
        mav.addObject("persons", persons);
        return mav;
    }

    @RequestMapping(
            value = "/project/toEdit/{projectId}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable("projectId") long projectId) {
        Project project = projectManager.getProject(projectId);
        List<Person> persons = personManager.listActivitySimplePersons();
        ModelAndView mav = new ModelAndView("project/edit");
        mav.addObject("persons", persons);
        mav.addObject("project", project);
        return mav;
    }

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
    public DataPage<Project> list(ProjectQuery projectQuery) {
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
            value = "/project/updateSearchYear", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
    public DataPage<Project> listBasic(ProjectQuery projectQuery) {
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
