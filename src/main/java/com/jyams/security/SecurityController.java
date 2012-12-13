package com.jyams.security;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jyams.security.model.LoggedUser;
import com.jyams.util.DataPage;

/**
 * User: zhanglong Date: 12-12-13 Time: 下午2:47
 */
@Controller
public class SecurityController {

    @Autowired
    private SecurityHolder securityHolder;

    @RequestMapping(value = "/security/loggedUser", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllLoginUsers(Model model) {

        return "security/loggedUser";
    }

    @RequestMapping(value = "/security/loggedUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<LoggedUser> getAllLoginUsers_ajax() {

        List<LoggedUser> loggedUsers = Lists.newArrayList();
        loggedUsers.addAll(securityHolder.getLoginUsers().values());
        Collections.sort(loggedUsers, new Comparator<LoggedUser>() {
            @Override
            public int compare(LoggedUser l1, LoggedUser l2) {
                return l1.getUsername().compareTo(l2.getUsername());
            }
        });

        return new DataPage<LoggedUser>(0, loggedUsers.size(), loggedUsers.size(), loggedUsers);
    }

    @RequestMapping(value = "/security/reload")
    public Boolean reloadPermission(Model model) {
        securityHolder.loadUsers();
        return true;
    }
}
