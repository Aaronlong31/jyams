package com.jyams.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhanglong
 * 
 *         Nov 20, 2012 9:47:16 PM
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
}
