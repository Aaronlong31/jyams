package com.jyams.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:32
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout")
    public String logou(){
        return null; //TODO
    }
}
