package com.jyams.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 
 * @author zhanglong
 * 
 *         Nov 8, 2012 11:03:35 PM
 */
@Component
@Aspect
public class HandleValidErrorAspect {

    private Validator validator;

    @Before("execution(* com.jyams..*Controller.*(..,@com.jyams.validator.PreValidated (*),..))")
    public void catchError(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Method invocationMethod = ((MethodSignature) joinPoint.getSignature())
                .getMethod();
        handlerError(invocationMethod, args);
    }

    private void handlerError(Method method, Object[] args) {
        Map<Integer, PreValidated> annotationAndIndexs = this
                .findObjectToValidate(method);
        if (annotationAndIndexs.isEmpty()) {
            return;
        }

        List<String> errorMessages = new ArrayList<String>();

        for (Entry<Integer, PreValidated> annotationAndIndex : annotationAndIndexs
                .entrySet()) {
            errorMessages.addAll(validate(annotationAndIndex.getValue(),
                    args[annotationAndIndex.getKey()]));
        }

        if (errorMessages.isEmpty()) {
            return;
        }

        // throw new http(errorMessages.toString());
    }

    private List<String> validate(PreValidated annotation,
            Object objectToValidate) {
        List<String> errorMessages = new ArrayList<String>();
        if (objectToValidate == null) {
            return errorMessages;
        }

        Set<ConstraintViolation<Object>> validateResult = validator.validate(
                objectToValidate, annotation.value());

        if (validateResult.isEmpty()) {
            return errorMessages;
        }

        for (ConstraintViolation<Object> constraintViolation : validateResult) {
            errorMessages.add(constraintViolation.getMessage());
        }
        return errorMessages;
    }

    private Map<Integer, PreValidated> findObjectToValidate(Method method) {
        Map<Integer, PreValidated> objectAndIndexs = Maps.newHashMap();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof PreValidated) {
                    PreValidated preValidated = (PreValidated) annotation;
                    objectAndIndexs.put(i, preValidated);
                }
            }
        }
        return objectAndIndexs;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

}
