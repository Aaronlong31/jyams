package com.jyams.dispatch.controller;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyams.cache.PersonCache;
import com.jyams.dispatch.manager.DispatchManager;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.dispatch.model.DispatchWork;
import com.jyams.dispatch.query.DispatchWorkQuery;
import com.jyams.hr.model.Person;
import com.jyams.security.SecurityUtils;
import com.jyams.util.DataPage;

/**
 * @author zhanglong 2012-11-25 下午8:07:49
 */
@Controller
public class DispatchController {

    @Autowired
    private DispatchManager dispatchManager;

    @Autowired
    private PersonCache personCache;

    @RequestMapping(value = "/dispatch",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String list() {
        return "dispatch/list";
    }

    @RequestMapping(value = "/dispatch",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<DispatchWork> list(DispatchWorkQuery dispatchWorkQuery) {
        return this.dispatchManager.listDispatchWorks(dispatchWorkQuery);
    }

    @RequestMapping(value = "/dispatch/{dispatchId}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String view(@PathVariable("dispatchId") long dispatchId, Model model) {
        Dispatch dispatch = this.dispatchManager.getDispatch(dispatchId);
        model.addAttribute("dispatch", dispatch);
        return "dispatch/view";
    }

    @RequestMapping(value = "/dispatch/monthly",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String listMonthly(@RequestParam(value = "personId", required = false) Long personId,
            Model model) {

        Calendar calendar = Calendar.getInstance();

        if (personId == null) {
            personId = SecurityUtils.getCurrentUserId();
        }

        Person person = this.personCache.get(personId);

        if (person == null) {
            model.addAttribute("message", "该员工不存在！");
            return "error";
        }
        model.addAttribute("beginYear", calendar.get(Calendar.YEAR) - 10);
        model.addAttribute("endYear", calendar.get(Calendar.YEAR));
        model.addAttribute("month", calendar.get(Calendar.MONTH) + 1);
        model.addAttribute("personId", personId);
        model.addAttribute("personName", person.getPersonName());
        return "dispatch/monthly";
    }

    @RequestMapping(value = "/dispatch/monthly",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<DispatchWork> listMonthly(@RequestParam("month") String month,
            @RequestParam("personId") Long personId) {
        if (StringUtils.isBlank(month)) {
            month = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM");
        }

        if (personId == null) {
            personId = SecurityUtils.getCurrentUserId();
        }

        int intMonth = Integer.parseInt(month.replace("-", ""));
        int dispatchDayStart = intMonth * 100;
        int dispatchDayEnd = dispatchDayStart + 31;
        DispatchWorkQuery dispatchWorkQuery = new DispatchWorkQuery();
        dispatchWorkQuery.setDispatchDayStart(dispatchDayStart);
        dispatchWorkQuery.setDispatchDayEnd(dispatchDayEnd);
        dispatchWorkQuery.setPersonId(personId);
        return this.dispatchManager.listDispatchWorks(dispatchWorkQuery);
    }
}
