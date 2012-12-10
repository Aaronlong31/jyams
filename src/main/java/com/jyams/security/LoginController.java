package com.jyams.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:30
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", params = {"username", "password"})
    public String login(@RequestParam String username, @RequestParam String password){
        return null;
    }
}
