package com.jyams.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jyams.util.DateTimeUtils;

/**
 * @author zhanglong
 * 
 *         2012-12-13 下午8:12:40
 */
public class DateToStringJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        if (value instanceof Long) {
            jgen.writeString(DateTimeUtils.convertLongToString((Long) value));
        }

        if (value instanceof Integer) {
            jgen.writeString(DateTimeUtils.convertIntegerDayToString((Integer) value));
        }
    }

}
