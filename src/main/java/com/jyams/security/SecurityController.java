package com.jyams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: zhanglong
 * Date: 12-12-13
 * Time: 下午2:47
 */
@Controller
public class SecurityController {

    @Autowired
    private SecurityHolder securityHolder;

    @RequestMapping(value = "/security/loginUsers")
    public String getAllLoginUsers(Model model){
        model.addAttribute("loginUsers", securityHolder.getLoginUsers().keySet());
        return "security/loginUser.jsp";
    }

    @RequestMapping(value = "/security/reload")
    public Boolean reloadPermission(Model model){
        securityHolder.loadUsers();
        return true;
    }
}
