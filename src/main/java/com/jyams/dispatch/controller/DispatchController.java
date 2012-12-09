package com.jyams.dispatch.controller;

import com.jyams.dispatch.manager.DispatchManager;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.dispatch.model.DispatchWork;
import com.jyams.dispatch.query.DispatchWorkQuery;
import com.jyams.util.DataPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhanglong
 *         <p/>
 *         2012-11-25 下午8:07:49
 */
@Controller
public class DispatchController {

    @Autowired
    private DispatchManager dispatchManager;

    @RequestMapping(
            value = "/dispatch", method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String list() {
        return "dispatch/list";
    }

    @RequestMapping(
            value = "/dispatch", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataPage<DispatchWork> list(DispatchWorkQuery dispatchWorkQuery) {
        return this.dispatchManager.listDispatchWorks(dispatchWorkQuery);
    }

    @RequestMapping(value = "/dispatch/{dispatchId}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String view(@PathVariable("dispatchId") long dispatchId, Model model) {
        Dispatch dispatch = this.dispatchManager.getDispatch(dispatchId);
        model.addAttribute("dispatch", dispatch);
        return "dispatch/view";
    }

    public void listMonth() {

    }
}
