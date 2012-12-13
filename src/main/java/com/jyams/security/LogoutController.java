package com.jyams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午11:32
 */
@Controller
public class LogoutController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/logout")
    public String logout() {
        securityService.logout();
        return "redirect:toLogin"; //TODO
    }
}
