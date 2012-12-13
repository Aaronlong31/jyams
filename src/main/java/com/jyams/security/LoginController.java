package com.jyams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午11:30
 */
@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/toLogin")
    @NoAuth
    public String toLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", params = { "username", "password" })
    @NoAuth
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (this.securityService.login(username, password)) {
            return "redirect:/project";
        }

        model.addAttribute("message", "用户名或密码错误！");
        return "redirect:toLogin";
    }
}
