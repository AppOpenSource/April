package com.point.base.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListType {
    Class list() default LinkedList.class;
    Class value();
}
