package com.chikage.framework.quartzframework.log;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ApiAnnotaion {
    String logMessage() default "";
}
