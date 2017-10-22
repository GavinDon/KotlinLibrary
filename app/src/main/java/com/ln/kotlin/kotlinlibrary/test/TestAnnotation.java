package com.ln.kotlin.kotlinlibrary.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * description:ttest
 * Created by liNan on 2017/6/29 10:15
 */
@Target({TYPE,FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface TestAnnotation {


}
