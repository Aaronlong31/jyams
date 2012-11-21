package com.jyams;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.jyams.project.controller.AddProjectForm;
import com.jyams.project.model.Project;

/**
 * @author zhanglong
 * 
 *         Nov 21, 2012 9:47:38 PM
 */
public class CopyBeanPropertiesTest {

    @Test
    public void testCopyProperties() {

        AddProjectForm apf = new AddProjectForm();

        apf.setCanDelayDay(1);
        apf.setClientName("clientname-zhangshan");
        apf.setClientPrincipalName("clientPrincipalName - zhangshan");
        apf.setCompanyPrincipalId(1999L);
        apf.setOrderContent("orderContent --- content");
        apf.setOrderDateString("2012-1010");
        apf.setOrderId("OX09988");
        apf.setProjectName("Test Project");
        apf.setQuoteId("O-1234545");
        apf.setRequiredCompletionDateString("2012-12-12");

        Project project = new Project();
        BeanUtils.copyProperties(apf, project);
        System.out.println(project);
    }
}
