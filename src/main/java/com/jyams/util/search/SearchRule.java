package com.jyams.util.search;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * {"groupOp":"AND","rules": [ {"field":"purchaseCode","op":"bw","data":"11"},
 * {"field":"applierName","op":"eq","data":"王伟文"},
 * {"field":"applyTimestamp","op":"bw","data":"2011-07-12"},
 * {"field":"arrivalDate","op":"bw","data":"2011-08-10"},
 * {"field":"deliveryAddress","op":"bw","data":"sdfgh"},
 * {"field":"consigneeName","op":"eq","data":"朱金南"} ] }
 * 
 * @author zhanglong
 * 
 */

enum Operate {
    bw, eq
}

public class SearchRule {

    private String field;
    private Operate op;
    private String data;

    public String getField() {
        return field;
    }

    public Operate getOp() {
        return op;
    }

    public String getData() {
        return data;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setOp(Operate op) {
        this.op = op;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
