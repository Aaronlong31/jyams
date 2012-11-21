package com.jyams.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jyams.hr.manager.PersonManager;
import com.jyams.hr.model.Person;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.model.Project;
import com.jyams.util.SpringSecurityUtils;

/**
 * @author zhanglong
 * 
 *         Nov 10, 2012 9:08:01 PM
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectManager projectManager;

    @Autowired
    private PersonManager personManager;

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public ModelAndView add() {
        List<Person> persons = personManager.listActivitySimplePersons();
        ModelAndView mav = new ModelAndView("project/add");
        mav.addObject("persons", persons);
        return mav;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ModelAndView add(
            @Valid @ModelAttribute AddProjectForm addProjectForm,
            BindingResult result, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("project/add");
            mav.addObject("project", addProjectForm);
            return mav;
        }

        Project project = new Project();
        BeanUtils.copyProperties(addProjectForm, project);
        long projectId = projectManager.addProject(project,
                addProjectForm.isNewYear());

        redirectAttrs.addFlashAttribute("message", "新建施工流程成功！");
        return new ModelAndView("redirect:/project/" + projectId);
    }

    @RequestMapping(value = "/project/toEdit/{projectId}",
            method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable("projectId") long projectId) {
        Project project = projectManager.getProject(projectId);
        List<Person> persons = personManager.listActivitySimplePersons();
        ModelAndView mav = new ModelAndView("project/edit");
        mav.addObject("persons", persons);
        mav.addObject("project", project);
        return mav;
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public void update(Project project) {
        project.setLastModifierId(SpringSecurityUtils.getCurrentUserId());
        project.setLastModifierName(SpringSecurityUtils.getCurrentUserName());
        project.setLastModifiedTimestamp(System.currentTimeMillis());
        projectManager.modifyProject(project);
    }

}
