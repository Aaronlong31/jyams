package com.jyams.buildingproject.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyams.buildingproject.manager.BuildingProjectDetailManager;
import com.jyams.buildingproject.query.BuildingProjectDetailQuery;
import com.jyams.exception.BusinessException;
import com.jyams.project.model.BuildingProjectDetail;
import com.jyams.util.DataPage;

/**
 * @author zhanglong
 * 
 *         Nov 25, 2012 1:00:22 AM
 */
@Controller
public class BuildingProjectDetailController {

    @Autowired
    private BuildingProjectDetailManager buildingProjectDetailManager;

    @RequestMapping(value = "/buildingProject/{projectId}/detail",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<BuildingProjectDetail> list(
            @PathVariable("projectId") long projectId,
            @Valid BuildingProjectDetailQuery buildingProjectDetailQuery) {
        buildingProjectDetailQuery.setProjectId(projectId);
        return buildingProjectDetailManager.list(buildingProjectDetailQuery);
    }

    @RequestMapping(value = "/buildingProject/{projectId}/detail",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object add(@PathVariable("projectId") long projectId,
            @Valid AddDetailForm addDetailForm, BindingResult result)
            throws BusinessException {

        if (result.hasErrors()) {
            return result.getAllErrors();
        }

        BuildingProjectDetail buildingProjectDetail = new BuildingProjectDetail();

        BeanUtils.copyProperties(addDetailForm, buildingProjectDetail);
        buildingProjectDetail.setProjectId(projectId);
        return buildingProjectDetailManager.add(buildingProjectDetail) > 0;
    }
}
