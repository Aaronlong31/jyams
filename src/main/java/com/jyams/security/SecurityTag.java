package com.jyams.security;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

/**
 * @author zhanglong
 * 
 *         2012-12-21 下午7:52:36
 */
public class SecurityTag extends TagSupport {

    private static final String PERMISSION_DELIMITER = "\\s*,\\s*";

    private String one;

    private String any;

    private String all;

    @Override
    public int doStartTag() throws JspException {

        if (StringUtils.hasText(one)) {
            return SecurityUtils.hasPermission(one) ? Tag.EVAL_BODY_INCLUDE : Tag.SKIP_BODY;
        }

        if (StringUtils.hasText(any)) {
            String[] permissions = StringUtils.tokenizeToStringArray(any, PERMISSION_DELIMITER);
            return SecurityUtils.hasAnyPermissions(permissions) ? Tag.EVAL_BODY_INCLUDE
                    : Tag.SKIP_BODY;
        }

        if (StringUtils.hasText(all)) {
            String[] permissions = StringUtils.tokenizeToStringArray(any, PERMISSION_DELIMITER);
            return SecurityUtils.hasAllPermissions(permissions) ? Tag.EVAL_BODY_INCLUDE
                    : Tag.SKIP_BODY;
        }

        return Tag.SKIP_BODY;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

}
