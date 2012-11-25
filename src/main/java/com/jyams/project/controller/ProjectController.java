package com.jyams.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ModelAttribute("persons")
    public void getPersons(Model model) {
        List<Person> persons = personManager.listActivitySimplePersons();
        model.addAttribute("persons", persons);
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("addProjectForm", new AddProjectForm());
        return "project/add";
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public String add(@Valid AddProjectForm addProjectForm,
            BindingResult result, RedirectAttributes redirectAttrs, Model model) {

        if (result.hasErrors()) {
            return "project/add";
        }

        Project project = new Project();
        BeanUtils.copyProperties(addProjectForm, project);
        long projectId = projectManager.addProject(project,
                addProjectForm.isNewYear());

        redirectAttrs.addFlashAttribute("message", "新建施工流程成功！");
        return "redirect:/project/" + projectId;
    }

    @RequestMapping(
            value = "/project/toEdit/{projectId}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectManager.getProject(projectId);
        model.addAttribute("editProjectForm", project);
        return "project/edit";
    }

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.PUT)
    public String edit(@PathVariable("projectId") long projectId,
            @Valid EditProjectForm editProjectForm, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {

        if (result.hasErrors()) {
            return "project/toEdit";
        }

        Project project = new Project();
        BeanUtils.copyProperties(editProjectForm, project);
        project.setProjectId(projectId);
        project.setLastModifierId(SpringSecurityUtils.getCurrentUserId());
        project.setLastModifierName(SpringSecurityUtils.getCurrentUserName());
        project.setLastModifiedTimestamp(System.currentTimeMillis());
        projectManager.modifyProject(project);
        return "redirect:/project/" + project.getProjectId();
    }

}
