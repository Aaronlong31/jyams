package com.jyams.util.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.collect.Lists;

/**
 * 
 * @author zhanglong
 * 
 */

enum GroupOp {
    AND, OR
}

public class SearchFilter {

    private GroupOp groupOp;
    private List<SearchRule> rules = Lists.newArrayList();
    private HttpServletRequest request;

    public static SearchFilter build(HttpServletRequest request) {
        SearchFilter sf;
        String filters = request.getParameter("filters");
        try {
            ObjectMapper om = new ObjectMapper();
            sf = om.readValue(filters, SearchFilter.class);
        } catch (Exception e) {
            sf = new SearchFilter();
        }
        sf.setRequest(request);
        return sf;
    }

    public GroupOp getGroupOp() {
        return groupOp;
    }

    public List<SearchRule> getRules() {
        return rules;
    }

    public void setGroupOp(GroupOp groupOp) {
        this.groupOp = groupOp;
    }

    public void setRules(List<SearchRule> rules) {
        this.rules = rules;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

}
