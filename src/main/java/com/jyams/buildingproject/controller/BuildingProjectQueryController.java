package com.jyams.buildingproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyams.buildingproject.manager.BuildingProjectManager;
import com.jyams.buildingproject.query.BuildingProjectQuery;
import com.jyams.project.model.BuildingProject;
import com.jyams.util.DataPage;

/**
 * @author zhanglong
 * 
 *         Nov 12, 2012 9:44:58 PM
 */
@Controller
public class BuildingProjectQueryController {

    @Autowired
    private BuildingProjectManager buildingProjectManager;

    @RequestMapping(value = "/buildingProject",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView list() {
        return new ModelAndView("buildingProject/list");
    }

    @RequestMapping(value = "/buildingProject",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<BuildingProject> list(
            BuildingProjectQuery buildingProjectQuery) {
        return buildingProjectManager.listBuildingProject(buildingProjectQuery);
    }

    @RequestMapping(value = "/project/status",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listStatus() {
        return new ModelAndView("project/listStatus");
    }

    @RequestMapping(value = "/project/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<BuildingProject> listStatus(
            BuildingProjectQuery buildingProjectQuery) {
        return buildingProjectManager.listBuildingProject(buildingProjectQuery);
    }

    @RequestMapping(value = "/buildingProject/hidden",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listHidden() {
        return new ModelAndView("buildingProject/listHidden");
    }

    @RequestMapping(value = "/buildingProject/hidden",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<BuildingProject> listHidden(
            BuildingProjectQuery buildingProjectQuery) {
        buildingProjectQuery.setStatus(BuildingProject.STATUS_HIDDEN);
        return buildingProjectManager.listBuildingProject(buildingProjectQuery);
    }

    @RequestMapping(value = "/buildingProject/{projectId}",
            method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("projectId") Long projectId) {
        BuildingProject bp = buildingProjectManager
                .getBuildingProject(projectId);

        ModelAndView mav = new ModelAndView("buildingProject/view");
        mav.addObject("buildingProject", bp);
        return mav;
    }
}
